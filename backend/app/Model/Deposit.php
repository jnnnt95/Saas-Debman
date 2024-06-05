<?php
    class Deposit {
        public $id;
        public $amount;
        public $receptionDate;
        public $receiverName;
        public $receiverId;
        public $isVoid;

        public function __construct(
            $id,
            $amount,
            $receptionDate,
            $receiverName,
            $receiverId,
            $isVoid
        )
        {
            $this -> id = $id;
            $this -> amount = $amount;
            $this -> receptionDate = $receptionDate;
            $this -> receiverName = $receiverName;
            $this -> receiverId = $receiverId;
            $this -> isVoid = $isVoid;
        }
    }
?>