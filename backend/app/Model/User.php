<?php

class User {
    public $id;
    public $servicesUserId;
    public $username;
    public $phone;
    public $type;
    public $email;
    public $creationDate;
    public $names;
    public $surnames;
    public $preferredInterestRate;
    public $preferredInterestRateOnDefault;
    public $daysToDefault;
    public $subusers;
    public $disabledSubuser;
    public $license;
    public $error;

    public function __construct(
        $id,
        $servicesUserId,
        $username,
        $phone,
        $type,
        $email,
        $creationDate,
        $names,
        $surnames,
        $preferredInterestRate,
        $preferredInterestRateOnDefault,
        $daysToDefault,
        $license,
        $subusers
    ) {
        $this -> id = $id;
        $this -> servicesUserId = $servicesUserId;
        $this -> username = $username;
        $this -> phone = $phone;
        $this -> type = $type;
        $this -> email = $email;
        $this -> creationDate = $creationDate;
        $this -> names = $names;
        $this -> surnames = $surnames;
        $this -> preferredInterestRate = $preferredInterestRate;
        $this -> preferredInterestRateOnDefault = $preferredInterestRateOnDefault;
        $this -> daysToDefault = $daysToDefault;
        $this -> license = $license;
        $this -> subusers = $subusers;
        $this -> error = false;
    }

    public function setSubuserAbility($subuserAbility) {
        $this -> disabledSubuser = $subuserAbility;
    }
}

?>