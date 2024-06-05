<?php

# Other Classes
require_once "User.php";
require_once "License.php";
require_once "Connection.php";
require_once "ModelError.php";
require_once "Generic.php";

# Relevant db code
require "db_operations/login.php";

class Login
{
    private static $modelErrorName = "Error de inicio de sesión";

    public static function authenticate($data)
    {
        $connection = Connection::connect();
        
        $username = $data["username"];
        $password = $data["password"];
        
        $rawUser = getRawUser($username, $password, $connection);

        $servicesUserId = 0;

        if (count($rawUser) > 0) {
            $rawUser = $rawUser[0];

            $preferredInterestRate = 0;
            $preferredInterestRateOnDefault = 0;
            $daysToDefault = 0;
            $subusers = null;

            switch ($rawUser["user_type"]) {
                case "services_subuser":
                    if ($rawUser["disabled_subuser"]) {
                        return new ModelError(self::$modelErrorName, "Su usuario se encuentra deshabilitado, póngase en contacto con el usuario administrador de esta cuenta y active su subusuario antes de poder utilizar los servicios de Debtor Manager.");
                    } else {
                        $rawParentUser = getRawParentUser($rawUser["parent_services_user"], $connection);
                        $rawParentUser = $rawParentUser[0];
                        $preferredInterestRate = $rawParentUser["preferred_interest_rate"];
                        $preferredInterestRateOnDefault = $rawParentUser["preferred_interest_rate_on_default"];
                        $daysToDefault = $rawParentUser["days_to_default"];
                        $servicesUserId = $rawUser["parent_services_user"];
                    }
                    break;
                case "services_user":
                    $preferredInterestRate = $rawUser["preferred_interest_rate"];
                    $preferredInterestRateOnDefault = $rawUser["preferred_interest_rate_on_default"];
                    $daysToDefault = $rawUser["days_to_default"];
                    $servicesUserId = $rawUser["id"];
                    $subusers = self::getSubusers($rawUser["id"]);
                    break;
                default:
                    return new ModelError(self::$modelErrorName, "Su usuario no puede iniciar sesión en esta versión de Debtor Manager.");
                    break;
            }

            $license = self::getLicense($servicesUserId);
            if ($license != null) {
                if (!($license->expiredLicense)) {
                    Generic::registerSession($rawUser["id"], $data["sessionKey"]);
                    return new User(
                        $rawUser["id"],
                        $servicesUserId,
                        $rawUser["username"],
                        $rawUser["phone"],
                        $rawUser["user_type"],
                        $rawUser["email"],
                        $rawUser["creation_date"],
                        $rawUser["names"],
                        $rawUser["surnames"],
                        $preferredInterestRate,
                        $preferredInterestRateOnDefault,
                        $daysToDefault,
                        $license,
                        $subusers
                    );
                } else {
                    return new ModelError(self::$modelErrorName, "La licencia asociada a esta cuenta se encuentra vencida (" . $license->expirationDate . "), asegúrese de renovarla para continuar");
                }
            } else {
                return new ModelError(self::$modelErrorName, "No se encontró una licencia asociada a su cuenta.");
            }
        } else {
            return new ModelError(self::$modelErrorName, "No se encontró un usuario con una contraseña coincidente. Por favor inténtelo de nuevo asegurándose de que los datos sean introducidos correctamente.");
        }
    }

    private static function getLicense($servicesUserId)
    {
        $connection = Connection::connect();
        $rawLicense = getRawLicense($servicesUserId, $connection);
        if (count($rawLicense) > 0) {
            $rawLicense = $rawLicense[0];
            return new License(
                $rawLicense["id"],
                $rawLicense["creation_date"],
                $rawLicense["expiration_date"],
                $rawLicense["license_expired"],
                $rawLicense["days_to_expiration"]
            );
        } else {
            return null;
        }
    }

    private static function getSubusers($userId)
    {
        $connection = Connection :: connect();
        $rawSubusers = getRawSubusers($userId, $connection);

        $subusers = array();

        foreach ($rawSubusers as $currentRawSubuser) {
            $currentSubuser = new User(
                $currentRawSubuser["id"],
                $userId,
                $currentRawSubuser["username"],
                $currentRawSubuser["phone"],
                "services_subuser",
                $currentRawSubuser["email"],
                $currentRawSubuser["creation_date"],
                $currentRawSubuser["names"],
                $currentRawSubuser["surnames"],
                $currentRawSubuser["preferred_interest_rate"],
                $currentRawSubuser["preferred_interest_rate_on_default"],
                $currentRawSubuser["days_to_default"],
                null,
                null
            );
            $currentSubuser->setSubuserAbility(boolval($currentRawSubuser["disabled_subuser"]));
            array_push($subusers, $currentSubuser);
        }

        return $subusers;
    }
}
