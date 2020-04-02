<?php

define("DOC", "https://lgsnexus.docs.apiary.io/");
define("API", "https://lgs-nexus.000webhostapp.com/");
define("BASE_PATH", '/nexus');
//define("BASE_PATH", 'https://legendary-games-studio.000webhostapp.com');

//Data
define("GAMES", array("editor"));
//categories
define("CATEGORY_NEWS", "news");
//catégories pour chaque jeu
define("GAMES_CATEGORIES",
    array(
        "editor"=>[CATEGORY_NEWS],
    )
);

//options disponibles
define("enigma_news_fields", array());
define("editor_news_fields", array("id", "offset", "limit"));

//errors
//codes
define("NOT_FOUND", ["status"=>404, "type"=> "Resource not Found"]);
define("NOT_ALLOWED", ["status"=>405,"type"=> "Method Not Allowed"]);
//error message
define("ERROR_MESSAGE",  array(
    "status"=> NOT_FOUND["status"],
    "type"=> NOT_FOUND["type"],
    "message"=> "This page doesn't exist.",
    "help"=> "For usage, please see: ".DOC,
));
//messages
define("NO_SUCH_GAME", "No such game.");
define("NO_SUCH_CATEGORY", "No such category.");
define("NO_SUCH_OPTION", "No such option.");
define("INVALID_VALUE", "Invalid value.");
define("INVALID_OFFSET_OR_LIMIT", "Invalid offset/limit.");
