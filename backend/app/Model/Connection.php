<?php

class Connection
{
    private static $server_name = "localhost";
    private static $db_name = "db_test";
    private static $db_username = "root";
    private static $db_password = "";
    private static $link;

    public static function connect()
    {
        self::$link = new PDO("mysql:host=" . self::$server_name . ";dbname=" . self::$db_name, self::$db_username, self::$db_password);
        self::$link->exec("set names utf8");
        return self::$link;
    }
}
