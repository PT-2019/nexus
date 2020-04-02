<?php
include_once "header.php";

//base
$router->map( 'GET', '/', function() {
    $msg = ERROR_MESSAGE;
    http_response_code(405);
    $msg["status"] = 405;
    $msg["type"] = "Wrong Usage.";
    $msg["message"] = "Please chose a game.";
    echo json_encode($msg);
});

//news
$router->map( 'GET', '/[a:game]/?(/[a:category](/)?)?', function($game, $category=null) {
    echo json_encode(Parser::parse($game, $category, count($_GET)==0?null:$_GET));
});
//one news
$router->map( 'GET', '/[a:game]/[a:category]/[i:key]', function($game, $category, $key) {
    echo json_encode(Parser::parse($game, $category, $key));
});

//mapping
$match = $router->match();
if( is_array($match) && is_callable( $match['target'] ) ) {
    call_user_func_array( $match['target'], $match['params']);
} else {
    echo json_encode(ERROR_MESSAGE);
}