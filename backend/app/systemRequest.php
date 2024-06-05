<?php

require_once "Model/Generic.php";
require_once "Model/SystemRequest.php";
date_default_timezone_set("America/Bogota");

#error_reporting(0);

$token = $_GET["srt"];
$tokenInfo = Generic::getTokenInfo($token);
SystemRequest::startSystemRequestOperation($tokenInfo);