<?php

function updateVoidDebt($debtId, $connection)
{
    $debtDeposits = selectDebtRelatedDeposits($debtId, $connection);
    foreach ($debtDeposits as $debtDeposit) {
        $depositId = $debtDeposit["deposit_id"];
        updateVoidDeposit($depositId, $connection);
    }

    $sql = "
    UPDATE 
        debt 
    SET 
        void = TRUE 
    WHERE 
        id = :debtId";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":debtId", $debtId, PDO::PARAM_INT);

    return $statement->execute();
}

function selectDebtRelatedDeposits($debtId, $connection)
{
    $sql = "
        SELECT 
            deposit_id 
        FROM 
            debt_deposit 
        WHERE 
            debt_id = :debtId";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":debtId", $debtId, PDO::PARAM_INT);
    $statement->execute();
    $result = $statement->fetchAll();
    return $result;
}

function selectDebtTotalLinkedDeposits($debtId, $connection)
{
    $sql = "
    SELECT 
        COUNT(*) AS total_linked_deposits 
    FROM 
        debt_deposit 
    WHERE 
        debt_id = :debtId";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":debtId", $debtId, PDO::PARAM_INT);

    if ($statement->execute()) {
        $result = $statement->fetchAll();
        return $result;
    } else {
        return null;
    }
}

function updateVoidDeposit($depositId, $connection)
{
    $depositDebts = selectDepositRelatedDebts($depositId, $connection);
    foreach ($depositDebts as $depositDebt) {
        $debtId = $depositDebt["debt_id"];
        $paidAmount = $depositDebt["paid_amount"];
        #$voidDeposit = $depositDebt["void"];
        unlinkDepositDebt($debtId, $paidAmount, $connection);
    }

    $sql = "
    UPDATE 
        deposit 
    SET 
        void = TRUE 
    WHERE 
        id = :depositId";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":depositId", $depositId, PDO::PARAM_INT);

    return $statement->execute();
}

function unlinkDepositDebt($debtId, $paidAmount, $connection)
{
    $sql = "
        UPDATE 
            debt 
        SET 
            paid_on_deposit = NULL, 
            paid_date = NULL,
            deposit = deposit - :paidAmount 
        WHERE id = :debtId";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":paidAmount", $paidAmount, PDO::PARAM_STR);
    $statement->bindParam(":debtId", $debtId, PDO::PARAM_INT);

    return $statement->execute();
}

function selectDepositRelatedDebts($depositId, $connection)
{
    $sql = "
        SELECT 
            debt_deposit.debt_id, 
            debt_deposit.paid_amount 
        FROM 
            debt_deposit 
        INNER JOIN 
            deposit ON 
                debt_deposit.deposit_id = deposit.id 
        WHERE 
            deposit.id = :depositId 
            AND 
            deposit.void = FALSE 
    ";

    #$sql = "
    #    SELECT 
    #        debt_id,
    #        paid_amount 
    #    FROM 
    #        debt_deposit 
    #    WHERE 
    #        deposit_id = :depositId";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":depositId", $depositId, PDO::PARAM_INT);
    $statement->execute();
    $result = $statement->fetchAll();
    return $result;
}

function insertTransaction(
    $debtId,
    $depositId,
    $paidAmount,
    $connection
) {
    $sql = "
        INSERT INTO 
            debt_deposit ( 
                debt_id, 
                deposit_id, 
                paid_amount 
            ) 
        VALUES ( 
            :debtId, 
            :depositId, 
            :paidAmount 
        )";
    $statement = $connection->prepare($sql);
    $statement->bindParam(":debtId", $debtId, PDO::PARAM_INT);
    $statement->bindParam(":depositId", $depositId, PDO::PARAM_INT);
    $statement->bindParam(":paidAmount", $paidAmount, PDO::PARAM_STR);

    $statement->execute();
}

function updateDisableClient($clientId, $connection)
{
    $sql = "
        UPDATE 
            debtor 
        SET 
            disabled_debtor = TRUE 
        WHERE id = :clientId";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":clientId", $clientId, PDO::PARAM_INT);

    return $statement->execute();
}

function updateDebtOnPaid(
    $deposit,
    $paidDate,
    $paymentId,
    $debtId,
    $connection
) {
    $sql = "
    UPDATE 
        debt 
    SET 
        deposit = :deposit, 
        paid_date = :paidDate, 
        paid_on_deposit = :paymentId 
    WHERE 
        id = :debtId";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":deposit", $deposit, PDO::PARAM_STR);
    $statement->bindParam(":paidDate", $paidDate, PDO::PARAM_STR);
    $statement->bindParam(":paymentId", $paymentId, PDO::PARAM_INT);
    $statement->bindParam(":debtId", $debtId, PDO::PARAM_INT);

    return $statement->execute();
}

function updateDebt(
    $deposit,
    $debtId,
    $connection
) {
    $sql = "
    UPDATE 
        debt 
    SET 
        deposit = :deposit
    WHERE 
        id = :debtId";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":deposit", $deposit, PDO::PARAM_STR);
    $statement->bindParam(":debtId", $debtId, PDO::PARAM_INT);

    return $statement->execute();
}

function insertDeposit(
    $clientId,
    $userId,
    $amount,
    $date,
    $connection
) {
    $sql = "
        INSERT INTO 
            deposit ( 
                debtor_id, 
                received_by, 
                amount, 
                reception_date 
            ) 
        VALUES ( 
            :clientId, 
            :userId, 
            :amount, 
            :date 
        )";
    $statement = $connection->prepare($sql);
    $statement->bindParam(":clientId", $clientId, PDO::PARAM_INT);
    $statement->bindParam(":userId", $userId, PDO::PARAM_INT);
    $statement->bindParam(":amount", $amount, PDO::PARAM_INT);
    $statement->bindParam(":date", $date, PDO::PARAM_STR);

    if ($statement->execute()) {
        return getLastInsertId($connection);
    } else {
        return null;
    }
}

function insertEmptyDebt($clientId, $daysToDefault, $date, $userId, $connection)
{
    $sql = "
        INSERT INTO 
            debt ( 
                debtor_id, 
                days_to_default, 
                creation_date, 
                created_by
            ) 
        VALUES ( 
            :clientId, 
            :daysToDefault, 
            :date, 
            :userId
        )";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":clientId", $clientId, PDO::PARAM_INT);
    $statement->bindParam(":daysToDefault", $daysToDefault, PDO::PARAM_INT);
    $statement->bindParam(":date", $date, PDO::PARAM_STR);
    $statement->bindParam(":userId", $userId, PDO::PARAM_INT);

    if ($statement->execute()) {
        return getLastInsertId($connection);
    } else {
        return null;
    }
}

function updateDebtForCreation(
    $amount,
    $deposit,
    $interestRate,
    $interestRateOnDefault,
    $daysToDefault,
    $creationDate,
    $debtId,
    $connection
) {
    $sql = "
        UPDATE 
            debt 
        SET 
            amount = :amount, 
            deposit = :deposit, 
            interest_rate = :interestRate, 
            interest_rate_on_default = :interestRateOnDefault, 
            days_to_default = :daysToDefault, 
            creation_date = :creationDate, 
            paid_date = NULL
        WHERE 
            id = :debtId";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":amount", $amount, PDO::PARAM_INT);
    $statement->bindParam(":deposit", $deposit, PDO::PARAM_STR);
    $statement->bindParam(":interestRate", $interestRate, PDO::PARAM_STR);
    $statement->bindParam(":interestRateOnDefault", $interestRateOnDefault, PDO::PARAM_STR);
    $statement->bindParam(":daysToDefault", $daysToDefault, PDO::PARAM_INT);
    $statement->bindParam(":creationDate", $creationDate, PDO::PARAM_STR);
    $statement->bindParam(":debtId", $debtId, PDO::PARAM_INT);

    return $statement->execute();
}

function insertEmptyClient($servicesUserId, $date, $userId, $connection)
{
    $sql = "
        INSERT INTO 
            debtor ( 
                services_user_id, 
                name, 
                nick, 
                phone, 
                creation_date, 
                commentary, 
                interest_rate, 
                created_by 
            ) 
        VALUES ( 
            :servicesUserId, 
            '', 
            '', 
            '', 
            :date, 
            '', 
            0, 
            :userId 
        )";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":servicesUserId", $servicesUserId, PDO::PARAM_INT);
    $statement->bindParam(":date", $date, PDO::PARAM_STR);
    $statement->bindParam(":userId", $userId, PDO::PARAM_INT);

    if ($statement->execute()) {
        return getLastInsertId($connection);
    } else {
        return null;
    }
}

function updateClientForCreation(
    $name,
    $nick,
    $phone,
    $commentary,
    $interestRate,
    $interestRateOnDefault,
    $clientId,
    $connection
) {
    $sql = "
        UPDATE 
            debtor 
        SET 
            name = :name, 
            nick = :nick, 
            phone = :phone, 
            commentary = :commentary, 
            interest_rate = :interestRate, 
            interest_rate_on_default = :interestRateOnDefault 
        WHERE 
            id = :clientId";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":name", $name, PDO::PARAM_STR);
    $statement->bindParam(":nick", $nick, PDO::PARAM_STR);
    $statement->bindParam(":phone", $phone, PDO::PARAM_STR);
    $statement->bindParam(":commentary", $commentary, PDO::PARAM_STR);
    $statement->bindParam(":interestRate", $interestRate, PDO::PARAM_STR);
    $statement->bindParam(":interestRateOnDefault", $interestRateOnDefault, PDO::PARAM_STR);
    $statement->bindParam(":clientId", $clientId, PDO::PARAM_INT);

    return $statement->execute();
}

function getLastInsertId($connection)
{
    $sql = "
        SELECT LAST_INSERT_ID() AS insert_id";

    $statement = $connection->prepare($sql);
    $statement->execute();
    $result = $statement->fetchAll();
    return $result;
}

function getRawClients($userId, $connection)
{
    $sql = "
        SELECT 
            debtor.id, 
            debtor.name, 
            debtor.nick, 
            debtor.phone, 
            debtor.commentary, 
            debtor.interest_rate, 
            debtor.interest_rate_on_default, 
            CONCAT(user.names, ' ', user.surnames) AS creator_name, 
            debtor.disabled_debtor, 
            debtor.created_by 
        FROM 
            debtor, 
            user 
        WHERE 
            debtor.created_by = user.id 
                AND 
            debtor.services_user_id = :userId 
        ORDER BY 
            debtor.creation_date";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":userId", $userId, PDO::PARAM_INT);
    $statement->execute();
    $result = $statement->fetchAll();
    return $result;
}

function selectClientDebts($clientId, $connection)
{
    $sql = "
        SELECT 
            debt.id, 
            debt.amount, 
            debt.deposit, 
            debt.days_to_default, 
            debt.interest_rate, 
            debt.interest_rate_on_default, 
            debt.creation_date, 
            debt.paid_date, 
            CONCAT(user.names, ' ', user.surnames) AS creator_name, 
            debt.created_by, 
            debt.void
        FROM 
            debt, 
            user 
        WHERE 
            debt.debtor_id = :clientId 
                AND 
            user.id = debt.created_by 
        ORDER BY 
            debt.creation_date";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":clientId", $clientId, PDO::PARAM_INT);
    $statement->execute();
    $result = $statement->fetchAll();
    return $result;
}

function getRawClientDeposits($clientId, $connection)
{
    $sql = "
        SELECT 
            deposit.id, 
            deposit.amount, 
            deposit.reception_date, 
            CONCAT(user.names, ' ', user.surnames) AS creator_name, 
            deposit.received_by, 
            deposit.void
        FROM 
            deposit, 
            user 
        WHERE 
            deposit.debtor_id = :clientId 
                AND 
            deposit.received_by = user.id 
        ORDER BY 
            deposit.reception_date ";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":clientId", $clientId, PDO::PARAM_INT);
    $statement->execute();
    $result = $statement->fetchAll();
    return $result;
}
