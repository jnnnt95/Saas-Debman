<?php

function selectVersionsTokens($connection) {
    $sql = "
        SELECT  
            token
        FROM 
            version
        ORDER BY 
            creation_date";

    $statement = $connection->prepare($sql);
    $statement -> execute();
    $result = $statement -> fetchAll();
    return $result;
}

function selectVersionKey($version, $connection) {
    $sql =
        "
    SELECT 
        token
    FROM  
        version
    WHERE 
        name = :version";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":version", $version, PDO::PARAM_STR);
    if($statement->execute()) {
        return $statement->fetchAll();
    }
    else {
        return null;
    }
}

function selectEmailVerification($email, $token, $connection) {
    $sql =
        "
    SELECT 
        COUNT(*) AS count
    FROM  
        request, 
        user
    WHERE 
        request.user_id = user.id AND 
        request.token = :token AND 
        user.email = :email";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":email", $email, PDO::PARAM_STR);
    $statement->bindParam(":token", $token, PDO::PARAM_STR);
    $statement->execute();
    $result = $statement->fetchAll();
    return $result;
}

function updateRequestExecuted($token, $connection)
{
    $sql =
        "
    UPDATE 
        request 
    SET 
        executed = TRUE 
    WHERE 
        token = :token";


    $statement = $connection->prepare($sql);
    $statement->bindParam(":token", $token, PDO::PARAM_STR);

    return $statement->execute();
}

function updateUserPassword($userId, $newPass, $connection)
{
    $sql =
        "
    UPDATE 
        user 
    SET 
        password = :newPass 
    WHERE 
        id = :userId";


    $statement = $connection->prepare($sql);
    $statement->bindParam(":userId", $userId, PDO::PARAM_INT);
    $statement->bindParam(":newPass", $newPass, PDO::PARAM_STR);

    return $statement->execute();
}

function selectTokenInfo($token, $connection)
{
    $sql = "
    SELECT 
        request.id AS srId, 
        request.request_type AS srTypeId, 
        request_type.name AS srTypeName, 
        request.user_id AS srUserId, 
        request.token AS srToken, 
        request.date AS srDate, 
        request.executed AS srExecuted
    FROM 
        request, 
        request_type 
    WHERE 
        token = :token 
            AND 
        request.request_type = request_type.id";


    $statement = $connection->prepare($sql);
    $statement->bindParam(":token", $token, PDO::PARAM_STR);
    $statement->execute();
    $result = $statement->fetchAll();
    return $result;
}

function selectTokenRepeated($token, $connection)
{
    $sql = "
        SELECT 
            * 
        FROM 
            request 
        WHERE 
            token = :token";


    $statement = $connection->prepare($sql);
    $statement->bindParam(":token", $token, PDO::PARAM_STR);
    $statement->execute();
    $result = $statement->fetchAll();

    return $result;
}

function tokenExists($token, $connection)
{
    $sql = "
    SELECT 
        * 
    FROM 
        request   
    WHERE 
        token = :token";


    $statement = $connection->prepare($sql);
    $statement->bindParam(":token", $token, PDO::PARAM_STR);

    if ($statement->execute()) {
        $result = $statement->fetchAll();
        return $result;
    } else {
        return null;
    }
}

function getUserIdFromEmail($email, $connection)
{
    $sql = "
    SELECT 
        id 
    FROM 
        user   
    WHERE 
        email = :email";


    $statement = $connection->prepare($sql);
    $statement->bindParam(":email", $email, PDO::PARAM_STR);

    if ($statement->execute()) {
        $result = $statement->fetchAll();
        return $result;
    } else {
        return null;
    }
}

function insertPasswordResetRequest($requestType, $userId, $token, $time, $connection)
{
    $sql =
        "
        INSERT INTO 
            request ( 
                request_type, 
                user_id, 
                token, 
                date
            ) 
        VALUES ( 
            :requestType, 
            :userId, 
            :token, 
            :time 
        )";


    $statement = $connection->prepare($sql);
    $statement->bindParam(":requestType", $requestType, PDO::PARAM_INT);
    $statement->bindParam(":userId", $userId, PDO::PARAM_INT);
    $statement->bindParam(":token", $token, PDO::PARAM_STR);
    $statement->bindParam(":time", $time, PDO::PARAM_STR);

    $statement->execute();
}

function updateOperationExecutionCorrect($userId, $time, $connection)
{
    $sql =
        "
        UPDATE 
            session 
        SET 
            last_operation_date = :time 
        WHERE 
            user_id = :userId";


    $statement = $connection->prepare($sql);
    $statement->bindParam(":userId", $userId, PDO::PARAM_INT);
    $statement->bindParam(":time", $time, PDO::PARAM_STR);

    return $statement->execute();
}

function insertNewSession($userId, $sessionKey, $time, $connection)
{
    $sql =
        "
        INSERT INTO 
            session ( 
                user_id, 
                session_key, 
                last_operation_date, 
                login_date 
            ) 
        VALUES ( 
            :userId, 
            :sessionKey, 
            :time, 
            :time 
        )";


    $statement = $connection->prepare($sql);
    $statement->bindParam(":userId", $userId, PDO::PARAM_INT);
    $statement->bindParam(":sessionKey", $sessionKey, PDO::PARAM_STR);
    $statement->bindParam(":time", $time, PDO::PARAM_STR);

    $statement->execute();
}

function erasePreviousSessions($userId, $connection)
{
    $sql = "
        DELETE FROM 
            session 
        WHERE 
            user_id = :userId
    ";


    $statement = $connection->prepare($sql);
    $statement->bindParam(":userId", $userId, PDO::PARAM_INT);

    if ($statement->execute()) {
        $result = $statement->fetchAll();
        return $result;
    } else {
        return null;
    }
}

function selectSessionData($userId, $connection)
{
    $sql = "
    SELECT 
        session_key, 
        last_operation_date, 
        login_date
    FROM 
        session  
    WHERE 
        user_id = :userId";


    $statement = $connection->prepare($sql);
    $statement->bindParam(":userId", $userId, PDO::PARAM_INT);

    if ($statement->execute()) {
        $result = $statement->fetchAll();
        return $result;
    } else {
        return null;
    }
}

function selectAssociatedUsers($servicesUserId, $connection)
{
    $sql = "
        SELECT 
            CONCAT(user.names, ' ', user.surnames) AS name, 
            user.phone, 
            user.email, 
            user.type 
        FROM 
            user 
        WHERE 
            ( user.id = :servicesUserId ) 
            OR 
            ( user.parent_services_user = :servicesUserId )";


    $statement = $connection->prepare($sql);
    $statement->bindParam(":servicesUserId", $servicesUserId, PDO::PARAM_INT);

    if ($statement->execute()) {
        $result = $statement->fetchAll();
        return $result;
    } else {
        return null;
    }
}

function selectUserInfo($userId, $connection)
{
    $sql = "
        SELECT 
            user.names, 
            user.surnames, 
            user.username, 
            user.phone, 
            user.email, 
            user.creation_date, 
            user_type.user_type 
        FROM 
            user, 
            user_type 
        WHERE 
            user.id = :userId
            AND 
            user.type = user_type.id";


    $statement = $connection->prepare($sql);
    $statement->bindParam(":userId", $userId, PDO::PARAM_INT);

    if ($statement->execute()) {
        $result = $statement->fetchAll();
        return $result;
    } else {
        return null;
    }
}

function selectDepositClients($servicesUserId, $date, $connection)
{
    $sql = "
        SELECT 
            debtor.id, 
            debtor.name, 
            debtor.nick, 
            SUM(deposit.amount) AS total_deposit_on_date 
        FROM 
            debtor, 
            deposit 
        WHERE 
            deposit.received_by IN 
                (
                    SELECT 
                        user.id 
                    FROM 
                        user 
                    WHERE 
                        ( user.id = :servicesUserId )
                        OR 
                        ( user.parent_services_user = :servicesUserId )
                ) 
            AND 
                ( debtor.id = deposit.debtor_id ) 
            AND 
                ( deposit.reception_date = :date ) 
            AND 
                ( deposit.void = FALSE )
        GROUP BY debtor.id 
        ORDER BY deposit.reception_date ";


    $statement = $connection->prepare($sql);
    $statement->bindParam(":servicesUserId", $servicesUserId, PDO::PARAM_INT);
    $statement->bindParam(":date", $date, PDO::PARAM_STR);
    $statement->execute();
    $result = $statement->fetchAll();
    if (!$result) {
        return array();
    } else {
        return $result;
    }
}

function selectCashInRegister($servicesUserId, $date, $connection)
{
    $sql = "
        SELECT sum(deposit.amount) AS cash_in_register 
        FROM deposit 
        WHERE deposit.received_by IN ( 
            SELECT user.id FROM user WHERE user.id = :servicesUserId OR user.parent_services_user = :servicesUserId 
            ) AND deposit.reception_date = :date AND deposit.void = FALSE";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":servicesUserId", $servicesUserId, PDO::PARAM_INT);
    $statement->bindParam(":date", $date, PDO::PARAM_STR);

    if ($statement->execute()) {
        $result = $statement->fetchAll();
        return $result;
    } else {
        return null;
    }
}

function getSubusersAllowed($userId, $connection)
{
    $sql = "
        SELECT 
            allowed_subusers 
        FROM 
            user 
        WHERE 
            user.id = :userId";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":userId", $userId, PDO::PARAM_INT);
    $statement->execute();
    $result = $statement->fetchAll();

    return $result;
}

function getSubusersCreated($userId, $connection)
{
    $sql = "
        SELECT 
            COUNT(*) AS subusers_created
        FROM 
            user 
        WHERE 
            user.parent_services_user = :userId";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":userId", $userId, PDO::PARAM_INT);
    $statement->execute();
    $result = $statement->fetchAll();

    return $result;
}
