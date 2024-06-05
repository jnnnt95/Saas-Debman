<?php
use PHPMailer\PHPMailer\PHPMailer;
require_once "Connection.php";
require_once "db_operations/generic.php";

class Generic
{
    private static $modelError = "Error de operación genérica";
    private static $timeToExpiredSessionOnOperation = 2; #hours
    private static $timeToExpiredSessionOnLogin = 1; #days
    private static $sessionError = "N/A";

    public static function getVersionKey($data) {
        $connection = Connection::connect();
        $version = $data["version"];
        $versionKey = selectVersionKey($version, $connection);
        if ($versionKey != null) {
            return hash("sha256", $versionKey[0]["token"]);
        } else {
            return null;
        }
    }
    
    public static function verifyUpdated($data)
    {
        $connection = Connection::connect();
        $versionsTokens = selectVersionsTokens($connection);
        $key = $data["key"];
        $comparable = hash("sha256", end($versionsTokens)["token"]);
        
        if(strcmp($key, $comparable) == 0) {
            $response = new stdClass();
            $response->error = false;
            $response->verified = true;
            return $response;
        }
        else {
            return new ModelError(self::$modelError, "No se pudo verificar la versión del software");
        }
    }

    public static function changePassword()
    {
        $connection = Connection::connect();
        session_start();
        date_default_timezone_set("America/Bogota");
        $userId = $_SESSION["srUserId"];
        $newPass = hash("sha256", $_POST["newPass"]);

        $requestTime = DateTime::createFromFormat('Y-m-d H:i:s', $_SESSION["srDate"])->getTimestamp();
        $diff = abs(time() - $requestTime);
        if ($diff <= $_SESSION["timeToExpiration"] * 60 * 60) {
            $email = $_POST["email"];
            $token = $_SESSION["srToken"];
            $emailVerified = selectEmailVerification($email, $token, $connection)[0]["count"] > 0;
            if ($emailVerified) {
                $passwordUpdated = updateUserPassword($userId, $newPass, $connection);
                if (boolval($passwordUpdated)) {
                    $token = $_SESSION["srToken"];
                    updateRequestExecuted($token, $connection);
                }
                echo boolval($passwordUpdated);
            } else {
                echo "El correo introducido no corresponde con el enviado para la solicitud actual";
            }
        } else {
            echo "Solicitud vencida";
            session_destroy();
        }
    }

    public static function getTokenInfo($token)
    {
        $connection = Connection::connect();
        $tokenInfo = selectTokenInfo($token, $connection);
        if ($tokenInfo != null) {
            if ($tokenInfo !== false) {
                return $tokenInfo[0];
            } else {
                return null;
            }
        } else {
            return $tokenInfo;
        }
    }

    public static function verifyToken($data)
    {
        $connection = Connection::connect();
        $token = $data["token"];

        $tokenRepeated = selectTokenRepeated($token, $connection);

        $response = new stdClass();
        $response->error = false;
        if (count($tokenRepeated) > 0) {
            $response->verifiedToken = false;
        } else {
            $response->verifiedToken = true;
        }
        return $response;
    }

    public static function requestPasswordReset($data)
    {
        $connection = Connection::connect();

        $email = $data["email"];
        $time = date("Y-m-d H:i:s", time());
        $userId = getUserIdFromEmail($email, $connection);
        if ($userId != null) {
            $userId = $userId[0]["id"];
            $token = $data["token"];
            insertPasswordResetRequest(1, $userId, $token, $time, $connection);
            self::sendResetPasswordEmail($email, $token, $time);
            $success = new stdClass();
            $success->error = false;
            return $success;
        } else {
            return new ModelError(self::$modelError, "No se pudo consultar usuario por correo electrónico");
        }
    }

    private static function sendResetPasswordEmail($email, $token, $time)
    {
        $from = "Sistema Debman <sistema@debman.xyz>";
        $to = $email;
        $subject = "Restablecimiento de contraseña | Debtor Manager";
        $message = "[" . $time . "]" . " Para continuar con el proceso de reestablecimiento de contraseña, por favor ingrese al siguiente enlace: \n\n";
        #srt stands for system request token
        #real: "https://www.debman.xyz/app/system_request.php?srt=".$token;
        $message .= "https://www.debman.xyz/app/systemRequest.php?srt=".$token." \n\nNo responda a esta dirección de correo.";
        $headers = "From:".$from;
        mail($to, $subject, $message, $headers);
    }

    public static function registerSession($userId, $sessionKey)
    {
        $connection = Connection::connect();

        erasePreviousSessions($userId, $connection);
        $time = date_format(new DateTime(), 'Y-m-d H:i:s');
        insertNewSession($userId, $sessionKey, $time, $connection);
    }

    public static function parseDate($time)
    {
        return date_format($time, 'Y-m-d H:i:s');
    }

    public static function throwSessionError()
    {
        $error = new ModelError(self::$modelError, self::$sessionError);
        $error->sessionError = true;
        return $error;
    }

    public static function newOperationProtocolCorrect($data)
    {
        if (self::verifySession($data)) {
            $connection = Connection::connect();
            $userId = $data["userId"];
            $time = date('Y-m-d H:i:s', time());
            updateOperationExecutionCorrect($userId, $time, $connection);
            return true;
        } else {
            return false;
        }
    }

    private static function verifySession($data)
    {
        $connection = Connection::connect();

        $userId = $data["userId"];
        $sessionKey = $data["sessionKey"];
        $sessionData = selectSessionData($userId, $connection);
        if ($sessionData != null) {
            if (count($sessionData) == 1) {
                if (self::sessionValidForHours($sessionData)) {
                    if (self::sessionValidForDays($sessionData)) {
                        if (self::sessionValidForToday($sessionData)) {
                            $sessionKeyValid = !boolval(strcmp($sessionKey, $sessionData[0]["session_key"]));
                            if ($sessionKeyValid) {
                                return true;
                            } else {
                                self::$sessionError = "Se detectó un problema con la sesión actual";
                                return false;
                            }
                        } else {
                            self::$sessionError = "La fecha cambió desde su último inicio de sesión";
                            return false;
                        }
                    } else {
                        self::$sessionError = "El tiempo desde su último inicio de sesión es mayor al permitido";
                        return false;
                    }
                } else {
                    self::$sessionError = "Tiempo de sesión en espera terminado, pasaron más de " . self::$timeToExpiredSessionOnOperation . " horas desde la última operación";
                    return false;
                }
            } else {
                self::$sessionError = "Se encontraron inconsistencias de inicio de sesión";
                return false;
            }
        } else {
            self::$sessionError = "Error inesperado de sesión";
            return false;
        }
    }

    private static function sessionValidForToday($sessionData)
    {
        $loginDate = $sessionData[0]["login_date"];
        $loginTime = DateTime::createFromFormat('Y-m-d H:i:s', $loginDate)->getTimestamp();
        return !(date('Ymd', $loginTime) == date('Ymd', strtotime('yesterday')));
    }

    private static function sessionValidForDays($sessionData)
    {
        $loginDate = $sessionData[0]["login_date"];
        $loginTime = DateTime::createFromFormat('Y-m-d H:i:s', $loginDate)->getTimestamp();
        $diff = abs(time() - $loginTime);
        return $diff <= self::$timeToExpiredSessionOnLogin * 60 * 60 * 24;
    }

    private static function sessionValidForHours($sessionData)
    {
        $lastOperationTime = $sessionData[0]["last_operation_date"];
        $lastOperationTime = DateTime::createFromFormat('Y-m-d H:i:s', $lastOperationTime)->getTimestamp();
        $diff = abs(time() - $lastOperationTime);
        return $diff <= self::$timeToExpiredSessionOnOperation * 60 * 60;
    }

    public static function getAssociatedUsers($data)
    {
        if (!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $connection = Connection::connect();
        $servicesUserId = $data["servicesUserId"];

        $associatedUsers = selectAssociatedUsers($servicesUserId, $connection);

        if ($associatedUsers != null) {
            $associatedUsersList = array();
            foreach ($associatedUsers as $associatedUser) {
                array_push(
                    $associatedUsersList,
                    array(
                        "name" => $associatedUser["name"],
                        "phone" => $associatedUser["phone"],
                        "email" => $associatedUser["email"],
                        "type" => $associatedUser["type"]
                    )
                );
            }
            $response = new stdClass();
            $response->error = false;
            $response->associatedUsers = $associatedUsersList;

            return $response;
        } else {
            return new ModelError(self::$modelError, "No se pudo consultar usuarios asociados");
        }
    }

    public static function getUserInfo($data)
    {
        if (!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $connection = Connection::connect();
        $userId = $data["userId"];

        $userInfo = selectUserInfo($userId, $connection);

        if ($userInfo != null) {
            $userInfo = $userInfo[0];
            $response = new stdClass();
            $response->error = false;
            $response->names = $userInfo["names"];
            $response->surnames = $userInfo["surnames"];
            $response->username = $userInfo["username"];
            $response->phone = $userInfo["phone"];
            $response->email = $userInfo["email"];
            $response->creationDate = $userInfo["creation_date"];
            $response->userType = $userInfo["user_type"];

            return $response;
        } else {
            return new ModelError(self::$modelError, "No se pudo consultar el total en caja");
        }
    }

    public static function getDepositClients($data)
    {
        if (!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $connection = Connection::connect();

        $servicesUserId = $data["servicesUserId"];
        $date = $data["date"];

        $depositClients = selectDepositClients($servicesUserId, $date, $connection);

        if (!($depositClients === null)) {
            $depositClientList = array();
            foreach ($depositClients as $depositClient) {
                $id = $depositClient["id"];
                $name = $depositClient["name"];
                $nick = $depositClient["nick"];
                $totalDepositOnDate = $depositClient["total_deposit_on_date"];
                array_push(
                    $depositClientList,
                    array(
                        "id" => $id,
                        "name" => $name,
                        "nick" => $nick,
                        "totalDepositOnDate" => $totalDepositOnDate
                    )
                );
            }

            $response = new stdClass();
            $response->error = false;
            $response->depositClients = $depositClientList;

            return $response;
        } else {
            return new ModelError(self::$modelError, "No se pudo consultar el total en caja por fecha: " . implode(" | ", $connection->errorInfo()));
        }
    }

    public static function getCashInRegister($data)
    {
        if (!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $connection = Connection::connect();

        $servicesUserId = $data["servicesUserId"];
        $date = $data["date"];

        $cashInRegister = selectCashInRegister($servicesUserId, $date, $connection);

        if ($cashInRegister != null) {
            $response = new stdClass();
            $response->cashInRegister = $cashInRegister[0]["cash_in_register"];
            $response->error = false;
            return $response;
        } else {
            return new ModelError(self::$modelError, "No se pudo consultar el total en caja");
        }
    }

    public static function getSubusersAllowed($data)
    {
        if (!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $userId = $data["userId"];
        $subusersAllowed = new stdClass();
        $subusersAllowed->subusersAllowed = getSubusersAllowed($userId, Connection::connect())[0]["allowed_subusers"];
        return $subusersAllowed;
    }

    public static function getSubusersCreated($data)
    {
        if (!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $userId = $data["userId"];
        $subusersCreated = new stdClass();
        $subusersCreated->subusersCreated = getSubusersCreated($userId, Connection::connect())[0]["subusers_created"];
        return $subusersCreated;
    }
}
