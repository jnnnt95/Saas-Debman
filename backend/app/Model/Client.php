<?php
    class Client {
        public $id;
        public $name;
        public $nick;
        public $phone;
        public $commentary;
        public $interestRate;
        public $interestRateOnDefault;
        public $creatorName;
        public $creatorId;
        public $disabledClient;

        public function __construct(
            $id,
            $name,
            $nick,
            $phone,
            $commentary,
            $interestRate,
            $interestRateOnDefault,
            $creatorName,
            $creatorId,
            $disabledClient
        )
        {
            $this -> id = $id;
            $this -> name = $name;
            $this -> nick = $nick;
            $this -> phone = $phone;
            $this -> commentary = $commentary;
            $this -> interestRate = $interestRate;
            $this -> interestRateOnDefault = $interestRateOnDefault;
            $this -> creatorName = $creatorName;
            $this -> creatorId = $creatorId;
            $this -> disabledClient = boolval($disabledClient);
        }
    }
?>