<?php
class License {
    public $id;
    public $creationDate;
    public $expirationDate;
    public $expiredLicense;
    public $daysToExpiration;

    public function __construct($id, $creationDate, $expirationDate, $expiredLicense, $daysToExpiration)
    {
        $this -> id = $id;
        $this -> creationDate = $creationDate;
        $this -> expirationDate = $expirationDate;
        $this -> expiredLicense = $expiredLicense;
        $this -> daysToExpiration = $daysToExpiration;
    }
}
?>