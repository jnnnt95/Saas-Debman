<?php
require_once "Connection.php";
require_once "db_operations/userOperations.php";
require_once "Generic.php";

class UserOperations
{
    private static $ModelErrorName = "Error de operación de usuario";

    public static function modifyGeneralInfo($data) {
        if(!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $connection = Connection::connect();

        $preferredInterestRate = $data["preferredInterestRate"];
        $preferredInterestRateOnDefault = $data["preferredInterestRateOnDefault"];
        $daysToDefault = $data["daysToDefault"];
        $userId = $data["userId"];

        $generalInfoUpdated = updateGeneralInfo(
            $preferredInterestRate,
            $preferredInterestRateOnDefault,
            $daysToDefault,
            $userId,
            $connection
        );

        if ($generalInfoUpdated) {
            $response = new stdClass();
            $response->error = false;
            return $response;
        } else {
            return new ModelError(self::$ModelErrorName, "No se pudo completar la operación de cambiar la información general: " . implode("| ", $connection->errorInfo()));
        }
    }

    public static function enableSubuser($data) {
        if(!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $connection = Connection::connect();

        $subuserId = $data["subuserId"];

        $subuserEnabled = updateEnableSubuser(
            $subuserId,
            $connection
        );

        if ($subuserEnabled) {
            $response = new stdClass();
            $response->error = false;
            return $response;
        } else {
            return new ModelError(self::$ModelErrorName, "No se pudo completar la operación de habilitar subusario: " . implode("| ", $connection->errorInfo()));
        }
    }

    public static function disableSubuser($data) {
        if(!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $connection = Connection::connect();

        $subuserId = $data["subuserId"];

        $subuserDisabled = updateDisableSubuser(
            $subuserId,
            $connection
        );

        if ($subuserDisabled) {
            $response = new stdClass();
            $response->error = false;
            return $response;
        } else {
            return new ModelError(self::$ModelErrorName, "No se pudo completar la operación de deshabilitar subusario: " . implode("| ", $connection->errorInfo()));
        }
    }

    public static function createSubuser($data)
    {
        if(!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $connection = Connection::connect();

        $names = $data["names"];
        $surnames = $data["surnames"];
        $username = $data["username"];
        $password = $data["password"];
        $phone = $data["phone"];
        $email = $data["email"];
        $type = $data["type"];
        $parentServicesUser = $data["parentServicesUser"];
        $cretionDate = $data["creationDate"];

        $lastInsertId = insertNewSubuser(
            $names,
            $surnames,
            $username,
            $password,
            $phone,
            $email,
            $type,
            $parentServicesUser,
            $cretionDate,
            $connection
        );
        if ($lastInsertId != null) {
            $response = new stdClass();
            $response->id = $lastInsertId[0]["insert_id"];
            $response->error = false;
            return $response;
        } else {
            $errorMessage = "No pudo completarse la operación de crear subusuario: " . implode("| ", $connection->errorInfo());
            return new ModelError(self::$ModelErrorName, $errorMessage);
        }
    }

    public static function verifyUsernameExistence($data)
    {
        if(!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $connection = Connection::connect();

        $username = $data["username"];

        $existenceVerification = selectUsernameExistance(
            $username,
            $connection
        );

        if (count($existenceVerification) > 0) {
            $response = new stdClass();
            $response->error = false;
            $response->usernameExists = true;
            return $response;
        } else {
            $response = new stdClass();
            $response->error = false;
            $response->usernameExists = false;
            return $response;
        }
    }

    public static function verifyPhoneExistence($data)
    {
        if(!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $connection = Connection::connect();

        $phone = $data["phone"];

        $existenceVerification = selectPhoneExistance(
            $phone,
            $connection
        );

        if (count($existenceVerification) > 0) {
            $response = new stdClass();
            $response->error = false;
            $response->phoneExists = true;
            return $response;
        } else {
            $response = new stdClass();
            $response->error = false;
            $response->phoneExists = false;
            return $response;
        }
    }

    public static function verifyEmailExistence($data)
    {
        if(!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $connection = Connection::connect();

        $email = $data["email"];

        $existenceVerification = selectEmailExistance(
            $email,
            $connection
        );

        if (count($existenceVerification) > 0) {
            $response = new stdClass();
            $response->error = false;
            $response->emailExists = true;
            return $response;
        } else {
            $response = new stdClass();
            $response->error = false;
            $response->emailExists = false;
            return $response;
        }
    }

    public static function changePassword($data)
    {
        if(!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $connection = Connection::connect();

        $password = $data["password"];
        $userId = $data["userId"];

        $passwordUpdated = updatePassword(
            $password,
            $userId,
            $connection
        );

        if ($passwordUpdated) {
            $response = new stdClass();
            $response->error = false;
            return $response;
        } else {
            return new ModelError(self::$ModelErrorName, "No se pudo completar la operación de cambiar contraseña: " . implode("| ", $connection->errorInfo()));
        }
    }

    public static function verifyPassword($data)
    {
        if(!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $connection = Connection::connect();

        $password = $data["password"];
        $userId = $data["userId"];

        $passwordVerification = passwordVerified(
            $password,
            $userId,
            $connection
        );

        if ($passwordVerification != null) {
            $verification = new stdClass();
            $verification->error = false;
            $verification->passwordVerified = boolval($passwordVerification[0]["auth"]);
            return $verification;
        } else {
            return new ModelError(self::$ModelErrorName, "No se pudo completar la operación de verificación de contraseña: " . implode("| ", $connection->errorInfo()));
        }
    }

    public static function modifyUser($data)
    {
        if(!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $connection = Connection::connect();

        $names = $data["names"];
        $surnames = $data["surnames"];
        $phone = $data["phone"];
        $email = $data["email"];
        $userId = $data["userId"];

        $userUpdated = updateUser(
            $names,
            $surnames,
            $phone,
            $email,
            $userId,
            $connection
        );

        if ($userUpdated) {
            $succeeded = new stdClass();
            $succeeded->error = false;
            return $succeeded;
        } else {
            $errorMessage = "No pudo completarse la operación de modificar usuario. Verifique su número de teléfono. Puede ser que este se encuentre registrado en nuestra base de datos.";
            return new ModelError(self::$ModelErrorName, $errorMessage);
        }
    }
}
