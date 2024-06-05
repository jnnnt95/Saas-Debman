<?php

function getRawLicense($servicesUserId, $connection) {
    $sql = "
        SELECT 
            license.id, 
            license.creation_date, 
            license.expiration_date, 
            CASE 
                WHEN DATEDIFF(license.expiration_date, CURDATE()) < 0 THEN TRUE 
                ELSE FALSE 
                END AS license_expired, 
            DATEDIFF(license.expiration_date, CURDATE()) AS days_to_expiration
        FROM 
            license 
        WHERE 
            license.suscriber = :servicesUserId 
        ORDER BY 
            license.expiration_date DESC LIMIT 1";

    $statement = $connection->prepare($sql);
    $statement -> bindParam(":servicesUserId", $servicesUserId, PDO :: PARAM_INT);
    $statement -> execute();
    $result = $statement -> fetchAll();
    return $result;
}

function getRawSubusers($userId, $connection)
{
    $sql = "
        SELECT 
            subuser.id, 
            subuser.username, 
            subuser.phone, 
            subuser.email, 
            subuser.creation_date, 
            subuser.names, 
            subuser.surnames, 
            subuser.disabled_subuser, 
            parent_services.preferred_interest_rate, 
            parent_services.preferred_interest_rate_on_default, 
            parent_services.days_to_default 
        FROM 
            user AS subuser 
            INNER JOIN user AS parent_services 
            ON subuser.parent_services_user = parent_services.id 
        WHERE 
            subuser.parent_services_user = :userId";

    $statement = $connection->prepare($sql);
    $statement -> bindParam(":userId", $userId, PDO :: PARAM_INT);
    $statement -> execute();
    $result = $statement -> fetchAll();
    return $result;
}

function getRawParentUser($parentServicesUserId, $connection)
{

    $sql = "
        SELECT 
            user.preferred_interest_rate,  
            user.preferred_interest_rate_on_default, 
            user.days_to_default 
        FROM 
            user 
        WHERE 
            id = :parentServicesUserId";

    $statement = $connection->prepare($sql);
    $statement -> bindParam(":parentServicesUserId", $parentServicesUserId, PDO :: PARAM_INT);
    $statement -> execute();
    $result = $statement -> fetchAll();

    return $result;
}

function getRawUser($username, $password, $connection)
{
    $sql = "
        SELECT 
            user.id, 
            user.username, 
            user.phone, 
            user_type.user_type, 
            user.email, 
            user.creation_date, 
            user.names, 
            user.surnames, 
            user.preferred_interest_rate, 
            user.preferred_interest_rate_on_default, 
            user.days_to_default, 
            user.parent_services_user, 
            user.disabled_subuser 
        FROM 
            user, 
            user_type 
        WHERE 
            user.username = :username 
            AND 
            user.password = :password 
            AND 
            user.type = user_type.id";

    $statement = $connection->prepare($sql);
    $statement -> bindParam(":username", $username, PDO :: PARAM_STR);
    $statement -> bindParam(":password", $password, PDO :: PARAM_STR);
    $statement -> execute();
    $result = $statement -> fetchAll();

    return $result;
}
