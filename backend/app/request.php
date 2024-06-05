<?php

require_once "Model/Login.php";
require_once "Model/ClientOperations.php";
require_once "Model/Generic.php";
require_once "Model/UserOperations.php";
require_once "Model/ModelError.php";

date_default_timezone_set("America/Bogota");

#header('Content-Type: application/json');
#$_POST = array("request" => "login", "username" => "", "password" => "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855");
#error_reporting(0);

$request = $_POST["request"];

switch ($request) {
    default:
        $errorInstance = new ModelError("Error de ejecución de solicitud", "La solicitud recibida no es válida");
        echo json_encode($errorInstance);
        break;
    case "login":
        echo json_encode(Login::authenticate($_POST));
        break;
    case "getClients":
        echo json_encode(ClientOperations::getClients($_POST));
        break;
    case "getSubusersAllowed":
        echo json_encode(Generic::getSubusersAllowed($_POST));
        break;
    case "getSubusersCreated":
        echo json_encode(Generic::getSubusersCreated($_POST));
        break;
    case "createEmptyClient":
        echo json_encode(ClientOperations::createEmptyClient($_POST));
        break;
    case "modifyClientForCreation":
        echo json_encode(ClientOperations::modifyClientForCretion($_POST));
        break;
    case "createEmptyDebt":
        echo json_encode(ClientOperations::createEmptyDebt($_POST));
        break;
    case "modifyDebtForCreation":
        echo json_encode(ClientOperations::modifyDebtForCreation($_POST));
        break;
    case "recordDeposit":
        echo json_encode(ClientOperations::recordDeposit($_POST));
        break;
    case "modifyDebt":
        echo json_encode(ClientOperations::modifyDebt($_POST));
        break;
    case "modifyUser":
        echo json_encode(UserOperations::modifyUser($_POST));
        break;
    case "verifyPassword":
        echo json_encode(UserOperations::verifyPassword($_POST));
        break;
    case "changePassword":
        echo json_encode(UserOperations::changePassword($_POST));
        break;
    case "verifyUsernameExistence":
        echo json_encode(UserOperations::verifyUsernameExistence($_POST));
        break;
    case "verifyPhoneExistence":
        echo json_encode(UserOperations::verifyPhoneExistence($_POST));
        break;
    case "verifyEmailExistence":
        echo json_encode(UserOperations::verifyEmailExistence($_POST));
        break;
    case "addSubuser":
        echo json_encode(UserOperations::createSubuser($_POST));
        break;
    case "disableSubuser":
        echo json_encode(UserOperations::disableSubuser($_POST));
        break;
    case "enableSubuser":
        echo json_encode(UserOperations::enableSubuser($_POST));
        break;
    case "modifyGeneralInfo":
        echo json_encode(UserOperations::modifyGeneralInfo($_POST));
        break;
    case "getCashInRegister":
        echo json_encode(Generic::getCashInRegister($_POST));
        break;
    case "getDepositClients":
        echo json_encode(Generic::getDepositClients($_POST));
        break;
    case "getUserInfo":
        echo json_encode(Generic::getUserInfo($_POST));
        break;
    case "getAssociatedUsers":
        echo json_encode(Generic::getAssociatedUsers($_POST));
        break;
    case "disableClient":
        echo json_encode(ClientOperations::disableClient($_POST));
        break;
    case "getClientDebts":
        echo json_encode(ClientOperations::getClientDebts($_POST));
        break;
    case "getClientDeposits":
        echo json_encode(ClientOperations::getClientDeposits($_POST));
        break;
    case "voidDeposit":
        echo json_encode(ClientOperations::voidDeposit($_POST));
        break;
    case "getDebtTotalLinkedDeposits":
        echo json_encode(ClientOperations::getDebtTotalLinkedDeposits($_POST));
        break;
    case "voidDebt":
        echo json_encode(ClientOperations::voidDebt($_POST));
        break;
    case "requestPasswordReset":
        echo json_encode(Generic::requestPasswordReset($_POST));
        break;
    case "verifyToken":
        echo json_encode(Generic::verifyToken($_POST));
        break;
    case "verifyUpdated":
        echo json_encode(Generic::verifyUpdated($_POST));
        break;
    case "getVersionKey":
        echo Generic::getVersionKey($_POST);
        break;
}
