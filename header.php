<?php
//class
require 'utils/AltoRouter.php';//router
require 'utils/DBAccess.php';//db
require 'utils/Parser.php';//parser
require  'utils/Helper.php';

//constants
include_once('utils/data/const.php');
//connect DB data
/*
define("DB_HOST", "");
define("DB_USER", "");
define("DB_PWD", "");
define("DB_NAME", "");
 */
include_once('utils/data/db_data.php');

//header
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");

//init router
$router = new AltoRouter();
$router->setBasePath(BASE_PATH);