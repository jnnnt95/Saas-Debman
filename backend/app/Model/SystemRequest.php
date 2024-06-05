<?php

class SystemRequest
{
    private static $denyText = "Servicio no disponible";
    private static $timeToExpiredRequest = 2; #hours

    public static function startSystemRequestOperation($tokenInfo)
    {
        session_start();
        if ($tokenInfo != null) {
            self::start($tokenInfo);
        } else {
            self::deny("No se encontró información de la solicitud");
        }
    }

    private static function start($tokenInfo)
    {
        #SR or sr stands for System Request
        $srId = $tokenInfo["srId"];
        $srTypeId = $tokenInfo["srTypeId"];
        $srTypeName = $tokenInfo["srTypeName"];
        $srUserId = $tokenInfo["srUserId"];
        $srToken = $tokenInfo["srToken"];
        $srDate = $tokenInfo["srDate"];
        #means that this specific request was already solved
        $srExecuted = $tokenInfo["srExecuted"];

        $void = self::requestExpired($srDate);
        if (!$void) {
            $void = boolval($srExecuted);
            if (!boolval($void)) {
                $_SESSION["srId"] = $srId;
                $_SESSION["srTypeId"] = $srTypeId;
                $_SESSION["srTypeName"] = $srTypeName;
                $_SESSION["srUserId"] = $srUserId;
                $_SESSION["srToken"] = $srToken;
                $_SESSION["srDate"] = $srDate;
                $_SESSION["srExecuted"] = $srExecuted;
                $_SESSION["timeToExpiration"] = self::$timeToExpiredRequest;
                switch ($srTypeName) {
                    case "password_reset":
                        self::passwordReset();
                        break;
                    case "get_version_key":
                        break;
                }
            } else {
                self::deny("La solicitud ya ha sido atendida, gracias por utilizar nuestros servicios.");
            }
        } else {
            if (boolval($srExecuted)) {
                self::deny("La solicitud ya ha sido atendida, gracias por utilizar nuestros servicios.");
            } else {
                self::deny("La solicitud se encuentra vencida, recuerde que las solicitudes se vencen " . self::$timeToExpiredRequest . " horas después de su emisión. Por favor, inténtelo de nuevo");
            }
        }
    }

    #returns true if request expired
    private static function requestExpired($date)
    {
        $requestTime = DateTime::createFromFormat('Y-m-d H:i:s', $date)->getTimestamp();
        $diff = abs(time() - $requestTime);
        return $diff > self::$timeToExpiredRequest * 60 * 60;
    }

    private static function passwordReset()
    {
        $filename = "View/resetPassword.html";
        echo file_get_contents($filename, false, null);
    }

    private static function deny($reason)
    {
        echo self::$denyText . ": " . $reason;
        session_destroy();
    }
}
