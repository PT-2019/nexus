<?php
include_once "header.php";

//init router
$router = new AltoRouter();
$router->setBasePath(BASE_PATH);

//base
$router->map( 'GET', '/', function() {
    //error ask to select a section :
    //GAMES, NEWS
    echo json_encode(Helper::loadErrorWith(NOT_ALLOWED,SELECT_SECTION));
});

//maps everything to the parser
$router->map( 'GET', '/[s:category]/[i:id]?/?', function($category, $id=null) {
    if(!in_array($category, CATEGORIES)){
        echo json_encode(Helper::loadErrorWith(NOT_ALLOWED,NO_SUCH_CATEGORY));
    }
    echo json_encode(Parser::parse($category, $id, count($_GET)==0?null:$_GET));
});

//mapping
$match = $router->match();
if( is_array($match) && is_callable( $match['target'] ) ) {
    call_user_func_array( $match['target'], $match['params']);
} else {
    echo json_encode(ERROR_MESSAGE);
}