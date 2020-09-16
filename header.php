<?php
//class
require 'utils/AltoRouter.php';//router
require 'utils/DBAccess.php';//db
require 'utils/Parser.php';//parser
require 'utils/Helper.php';

//constants
include_once('data/const.php');
//connect DB data
/* If not found
define("DB_HOST", "");
define("DB_USER", "");
define("DB_PWD", "");
define("DB_NAME", "");
 */
include_once('data/db_data.php');

//header
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");