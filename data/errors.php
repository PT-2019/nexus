<?php

//codes
define("NOT_FOUND", ["status"=>404, "type"=> "Resource not Found"]);
define("NOT_ALLOWED", ["status"=>405,"type"=> "Method Not Allowed"]);

//errors

define("ERROR_MESSAGE",  array(
    "status"=> NOT_FOUND["status"],
    "type"=> NOT_FOUND["type"],
    "message"=> "This page doesn't exist.",
    "help"=> "For usage, please see: "."https://lgsnexus.docs.apiary.io/",
));

//wrong usage
define('SELECT_SECTION','Please select a section (games, news)');
define('BAD_ID','Please give an appropriate ID.');
define("INVALID_VALUE", "Invalid value.");

//Not found
define("NO_SUCH_CATEGORY", "No such category.");
define("NO_SUCH_ID", "No such ID.");
define("NO_SUCH_OPTION", "Unknown property");
define("NO_SUCH_OPTIONS", "Unknown properties");
define("NO_SUCH_GAME", "No such game.");
