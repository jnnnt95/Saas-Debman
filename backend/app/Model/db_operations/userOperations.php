<?php

function updateGeneralInfo(
    $preferredInterestRate,
    $preferredInterestRateOnDefault,
    $daysToDefault,
    $userId,
    $connection
) {
    $sql = "
        UPDATE 
            user 
        SET 
            preferred_interest_rate =  :preferredInterestRate, 
            preferred_interest_rate_on_default = :preferredInterestRateOnDefault, 
            days_to_default = :daysToDefault
        WHERE 
            id = :userId";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":preferredInterestRate", $preferredInterestRate, PDO::PARAM_STR);
    $statement->bindParam(":preferredInterestRateOnDefault", $preferredInterestRateOnDefault, PDO::PARAM_STR);
    $statement->bindParam(":daysToDefault", $daysToDefault, PDO::PARAM_INT);
    $statement->bindParam(":userId", $userId, PDO::PARAM_INT);

    return $statement->execute();
}

function updateEnableSubuser(
    $subuserId,
    $connection
) {
    $sql = "
        UPDATE 
            user 
        SET 
            disabled_subuser = FALSE 
        WHERE 
            id = :subuserId";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":subuserId", $subuserId, PDO::PARAM_INT);

    return $statement->execute();
}

function updateDisableSubuser(
    $subuserId,
    $connection
) {
    $sql = "
        UPDATE 
            user 
        SET 
            disabled_subuser = TRUE 
        WHERE 
            id = :subuserId";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":subuserId", $subuserId, PDO::PARAM_INT);

    return $statement->execute();
}

function insertNewSubuser(
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
) {
    $sql = "
        INSERT INTO 
            user (names, surnames, username, password, phone, email, type, parent_services_user, creation_date) 
        VALUES ( 
            :names, 
            :surnames, 
            :username, 
            :password, 
            :phone, 
            :email, 
            :type, 
            :parentServicesUser, 
            :cretionDate
        )";
    $statement = $connection->prepare($sql);
    $statement->bindParam(":names", $names, PDO::PARAM_STR);
    $statement->bindParam(":surnames", $surnames, PDO::PARAM_STR);
    $statement->bindParam(":username", $username, PDO::PARAM_STR);
    $statement->bindParam(":password", $password, PDO::PARAM_STR);
    $statement->bindParam(":phone", $phone, PDO::PARAM_STR);
    $statement->bindParam(":email", $email, PDO::PARAM_STR);
    $statement->bindParam(":type", $type, PDO::PARAM_INT);
    $statement->bindParam(":parentServicesUser", $parentServicesUser, PDO::PARAM_INT);
    $statement->bindParam(":cretionDate", $cretionDate, PDO::PARAM_STR);

    if ($statement->execute()) {
        return getLastInsertId($connection);
    } else {
        return null;
    }
}

function selectUsernameExistance($username, $connection)
{
    $sql = "
    SELECT TRUE 
    WHERE TRUE IN 
    (     
        SELECT 
            CASE WHEN     
            USER.username = :username 
            THEN TRUE 
            ELSE FALSE     
            END AS username_exists    
        FROM USER)";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":username", $username, PDO::PARAM_STR);

    $statement->execute();

    return $statement->fetchAll();
}

function selectPhoneExistance($phone, $connection)
{
    $sql = "
    SELECT TRUE 
    WHERE TRUE IN 
    (     
        SELECT 
            CASE WHEN 
            user.phone = :phone 
            THEN TRUE 
            ELSE FALSE 
            END AS phone_exists 
        FROM USER)";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":phone", $phone, PDO::PARAM_STR);

    $statement->execute();

    return $statement->fetchAll();
}

function selectEmailExistance($email, $connection)
{
    $sql = "
    SELECT TRUE 
    WHERE TRUE IN 
    (     
        SELECT 
            CASE WHEN 
            user.email = :email 
            THEN TRUE 
            ELSE FALSE 
            END AS email_exists 
        FROM USER)";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":email", $email, PDO::PARAM_STR);

    $statement->execute();

    return $statement->fetchAll();
}

function updatePassword($password, $userId, $connection)
{
    $sql = "
        UPDATE 
            user 
        SET 
            password = :password 
        WHERE 
            id = :userId";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":password", $password, PDO::PARAM_STR);
    $statement->bindParam(":userId", $userId, PDO::PARAM_INT);

    return $statement->execute();
}

function passwordVerified($password, $userId, $connection)
{
    $sql = "
    SELECT 
        CASE 
            WHEN user.password = :password THEN TRUE 
            ELSE FALSE 
        END AS auth 
    FROM 
        user 
    WHERE 
        id = :userId";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":password", $password, PDO::PARAM_STR);
    $statement->bindParam(":userId", $userId, PDO::PARAM_INT);

    if ($statement->execute()) {
        return $statement->fetchAll();
    } else {
        return null;
    }
}

function updateUser(
    $names,
    $surnames,
    $phone,
    $email,
    $userId,
    $connection
) {
    $sql = "
    UPDATE 
        user 
    SET 
        names = :names, 
        surnames = :surnames, 
        phone = :phone, 
        email = :email 
    WHERE 
        id = :userId";

    $statement = $connection->prepare($sql);
    $statement->bindParam(":names", $names, PDO::PARAM_STR);
    $statement->bindParam(":surnames", $surnames, PDO::PARAM_STR);
    $statement->bindParam(":phone", $phone, PDO::PARAM_STR);
    $statement->bindParam(":email", $email, PDO::PARAM_STR);
    $statement->bindParam(":userId", $userId, PDO::PARAM_INT);

    return $statement->execute();
}
