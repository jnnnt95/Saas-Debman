<?php
    class Debt {
        public $id;
        public $clientId;
        public $amount;
        public $deposit;
        public $interestRate;
        public $interestRateOnDefault;
        public $daysToDefault;
        public $creationDate;
        public $paidDate;
        public $creatorName;
        public $creatorId;
        public $isVoid;

        public function __construct(
            $id,
            $clientId,
            $amount,
            $deposit,
            $interestRate,
            $interestRateOnDefault,
            $daysToDefault,
            $creationDate,
            $paidDate,
            $creatorName,
            $creatorId,
            $isVoid
        )
        {
            $this -> id = $id;
            $this -> clientId = $clientId;
            $this -> amount = $amount;
            $this -> deposit = $deposit;
            $this -> interestRate = $interestRate;
            $this -> interestRateOnDefault = $interestRateOnDefault;
            $this -> daysToDefault = $daysToDefault;
            $this -> creationDate = $creationDate;
            $this -> paidDate = $paidDate;
            $this -> creatorName = $creatorName;
            $this -> creatorId = $creatorId;
            $this -> isVoid = $isVoid;

        }
    }
?>