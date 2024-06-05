<?php
require_once "db_operations/clientOperations.php";
require_once "Client.php";
require_once "Debt.php";
require_once "Deposit.php";
require_once "ModelError.php";
require_once "Connection.php";

class ClientOperations
{
    private static $ModelErrorName = "Error de operación de cliente";

    public static function voidDebt($data) {
        if(!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $connection = Connection::connect();

        $debtId = $data["debtId"];

        $voidedDebt = updateVoidDebt($debtId, $connection);

        if ($voidedDebt) {
            $succeeded = new stdClass();
            $succeeded->error = false;
            return $succeeded;
        } else {
            $errorMessage = "No pudo completarse la operación de anular deuda: " . implode("| ", $connection->errorInfo());
            return new ModelError(self::$ModelErrorName, $errorMessage);
        }
    }

    public static function getDebtTotalLinkedDeposits($data) {
        if(!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $connection = Connection::connect();

        $debtId = $data["debtId"];

        $voidedDebt = selectDebtTotalLinkedDeposits($debtId, $connection);

        if ($voidedDebt != null) {
            $succeeded = new stdClass();
            $succeeded->error = false;
            $succeeded->totalLinkedDeposits = $voidedDebt[0]["total_linked_deposits"];
            return $succeeded;
        } else {
            $errorMessage = "No pudo completarse la operación de obtener el total de depósitos de la deuda a anular: " . implode("| ", $connection->errorInfo());
            return new ModelError(self::$ModelErrorName, $errorMessage);
        }
    }

    public static function voidDeposit($data) {
        if(!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $connection = Connection::connect();

        $depositId = $data["depositId"];

        $voidedDeposit = updateVoidDeposit($depositId, $connection);

        if ($voidedDeposit) {
            $succeeded = new stdClass();
            $succeeded->error = false;
            return $succeeded;
        } else {
            $errorMessage = "No pudo completarse la operación de anular depósito: " . implode("| ", $connection->errorInfo());
            return new ModelError(self::$ModelErrorName, $errorMessage);
        }
    }

    public static function disableClient($data) {
        if(!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $connection = Connection::connect();
        
        $clientId = $data["clientId"];

        $clientDisabled = updateDisableClient($clientId, $connection);

        if ($clientDisabled) {
            $succeeded = new stdClass();
            $succeeded->error = false;
            return $succeeded;
        } else {
            $errorMessage = "No pudo completarse la operación de deshabilitar cliente: " . implode("| ", $connection->errorInfo());
            return new ModelError(self::$ModelErrorName, $errorMessage);
        }
    }

    public static function modifyDebt($data) {
        if(!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $connection = Connection::connect();
        
        $deposit = $data["deposit"];
        $isPaid = $data["isPaid"];
        $paidDate = $data["paidDate"];
        $depositId = $data["depositId"];
        $debtId = $data["debtId"];
        $paidAmount = $data["paidAmount"];

        if (boolval($isPaid)) {
            $debtUpdated = updateDebtOnPaid(
                $deposit,
                $paidDate,
                $depositId,
                $debtId,
                $connection
            );
        } else {
            $debtUpdated = updateDebt(
                $deposit,
                $debtId,
                $connection
            );
        }

        insertTransaction(
            $debtId,
            $depositId,
            $paidAmount,
            $connection
        );

        if ($debtUpdated) {
            $succeeded = new stdClass();
            $succeeded->error = false;
            return $succeeded;
        } else {
            $errorMessage = "No pudo completarse la operación de modificar deuda: " . implode("| ", $connection->errorInfo());
            return new ModelError(self::$ModelErrorName, $errorMessage);
        }
    }

    public static function recordDeposit($data) {
        if(!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $connection = Connection::connect();

        $clientId = $data["clientId"];
        $userId = $data["userId"];
        $amount = $data["amount"];
        $date = $data["date"];

        $lastInsertId = insertDeposit(
            $clientId,
            $userId,
            $amount,
            $date,
            $connection
        );
        if ($lastInsertId != null) {
            $recordedDeposit = new stdClass();
            $recordedDeposit->id = $lastInsertId[0]["insert_id"];
            $recordedDeposit->error = false;
            return $recordedDeposit;
        } else {
            $errorMessage = "No pudo completarse la operación de registrar depósito: " . implode("| ", $connection->errorInfo());
            return new ModelError(self::$ModelErrorName, $errorMessage);
        }
    }

    public static function createEmptyDebt($data) {
        if(!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $connection = Connection::connect();

        $clientId = $data["clientId"];
        $daysToDefault = $data["daysToDefault"];
        $date = $data["date"];
        $userId = $data["userId"];

        $lastInsertId = insertEmptyDebt($clientId, $daysToDefault, $date, $userId, $connection);
        if ($lastInsertId != null) {
            $insertedEmptyDebt = new stdClass();
            $insertedEmptyDebt->id = $lastInsertId[0]["insert_id"];
            $insertedEmptyDebt->error = false;
            return $insertedEmptyDebt;
        } else {
            $errorMessage = "No pudo completarse la operación de adicionar deuda: " . implode("| ", $connection->errorInfo());
            return new ModelError(self::$ModelErrorName, $errorMessage);
        }
    }

    public static function modifyDebtForCreation($data) {
        if(!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $connection = Connection::connect();

        $amount = $data["amount"];
        $deposit = $data["deposit"];
        $interestRate = $data["interestRate"];
        $interestRateOnDefault = $data["interestRateOnDefault"];
        $daysToDefault = $data["daysToDefault"];
        $creationDate = $data["creationDate"];
        $debtId = $data["debtId"];

        $debtUpdated = updateDebtForCreation(
            $amount,
            $deposit,
            $interestRate,
            $interestRateOnDefault,
            $daysToDefault,
            $creationDate,
            $debtId,
            $connection
        );
        if ($debtUpdated) {
            $succeeded = new stdClass();
            $succeeded->error = false;
            return $succeeded;
        } else {
            $errorMessage = "No pudo completarse la operación de modificar deuda: " . implode("| ", $connection->errorInfo());
            return new ModelError(self::$ModelErrorName, $errorMessage);
        }
    }

    public static function createEmptyClient($data)
    {
        if(!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $connection = Connection::connect();

        $servicesUserId = $data["servicesUserId"];
        $date = $data["date"];
        $userId = $data["userId"];

        $lastInsertId = insertEmptyClient($servicesUserId, $date, $userId, $connection);
        if ($lastInsertId != null) {
            $insertedEmptyClient = new stdClass();
            $insertedEmptyClient->id = $lastInsertId[0]["insert_id"];
            $insertedEmptyClient->error = false;
            return $insertedEmptyClient;
        } else {
            $errorMessage = "No pudo completarse la operación de adicionar cliente: " . implode("| ", $connection->errorInfo());
            return new ModelError(self::$ModelErrorName, $errorMessage);
        }
    }

    public static function modifyClientForCretion($data) {
        if(!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $connection = Connection::connect();

        $name = $data["name"];
        $nick = $data["nick"];
        $phone = $data["phone"];
        $commentary = $data["commentary"];
        $interestRate = $data["interestRate"];
        $interestRateOnDefault = $data["interestRateOnDefault"];
        $clientId = $data["clientId"];

        $clientUpdated = updateClientForCreation(
            $name,
            $nick,
            $phone,
            $commentary,
            $interestRate,
            $interestRateOnDefault,
            $clientId,
            $connection
        );
        if ($clientUpdated) {
            $succeeded = new stdClass();
            $succeeded->error = false;
            return $succeeded;
        } else {
            $errorMessage = "No pudo completarse la operación de modificar cliente: " . implode("| ", $connection->errorInfo());
            return new ModelError(self::$ModelErrorName, $errorMessage);
        }
    }

    public static function getClients($data)
    {
        if(!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }

        $connection = Connection::connect();

        $userServicesId = $data["userServicesId"];

        $rawClients = getRawClients($userServicesId, $connection);
        $clients = array();
        foreach ($rawClients as $rawClient) {
            array_push($clients, new Client(
                $rawClient["id"],
                $rawClient["name"],
                $rawClient["nick"],
                $rawClient["phone"],
                $rawClient["commentary"],
                $rawClient["interest_rate"],
                $rawClient["interest_rate_on_default"],
                $rawClient["creator_name"],
                $rawClient["created_by"],
                $rawClient["disabled_debtor"]
            ));
        }
        return $clients;
    }

    public static function getClientDebts($data)
    {
        if(!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $connection = Connection::connect();
        $clientId = $data["clientId"];
        $rawDebts = selectClientDebts($clientId, $connection);
        $debts = array();
        foreach ($rawDebts as $rawDebt) {
            array_push($debts, new Debt(
                $rawDebt["id"],
                $clientId,
                $rawDebt["amount"],
                $rawDebt["deposit"],
                $rawDebt["interest_rate"],
                $rawDebt["interest_rate_on_default"],
                $rawDebt["days_to_default"],
                $rawDebt["creation_date"],
                $rawDebt["paid_date"],
                $rawDebt["creator_name"],
                $rawDebt["created_by"],
                boolval($rawDebt["void"])
            ));
        }
        return $debts;
    }

    public static function getClientDeposits($data)
    {
        if(!Generic::newOperationProtocolCorrect($data)) {
            return Generic::throwSessionError();
        }
        $connection = Connection::connect();

        $clientId = $data["clientId"];

        $rawDeposits = getRawClientDeposits($clientId, $connection);
        $deposits = array();
        foreach ($rawDeposits as $rawDeposit) {
            array_push($deposits, new Deposit(
                $rawDeposit["id"],
                $rawDeposit["amount"],
                $rawDeposit["reception_date"],
                $rawDeposit["creator_name"],
                $rawDeposit["received_by"],
                boolval($rawDeposit["void"])
            ));
        }
        return $deposits;
    }
}
