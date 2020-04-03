<?php
include_once "header.php";

//base
$router->map( 'GET', '/', function() {
    echo json_encode(Helper::loadErrorWith(NOT_ALLOWED,SELECT_GAME));
});

//news
$router->map( 'GET', '/[s:game]/?(/[s:category](/)?)?', function($game, $category=null) {
    echo json_encode(Parser::parse($game, $category, count($_GET)==0?null:$_GET));
});
//one news
$router->map( 'GET', '/[s:game]/[s:category]/[i:key]', function($game, $category, $key) {
    echo json_encode(Parser::parse($game, $category, $key));
});

//mapping
$match = $router->match();
if( is_array($match) && is_callable( $match['target'] ) ) {
    call_user_func_array( $match['target'], $match['params']);
} else {
    echo json_encode(ERROR_MESSAGE);
}