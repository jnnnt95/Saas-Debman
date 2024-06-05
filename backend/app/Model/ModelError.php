<?php
    class ModelError {
        public $name;
        public $message;
        public $error;
        public $sessionError;

        public function __construct($name, $message)
        {
            $this -> name = $name;
            $this -> message = $message;
            $this -> error = true;
            $this -> sessionError = false;
        }
    }
?>