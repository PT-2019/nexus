<?php

/**
 * Class Parser
 *
 * Parse any request to matching classes
 *
 * @see News
 * @see GameData
 *
 * @since 1.0
 * @version 1.0
 */
class Parser {

    /**
     * Parse a basic quest
     * @param $game string game (enigma, editor, ...)
     * @param $category string category (news, ..)
     * @param $request string|array request (order by, limit, offset, id, ....)
     * @return array json
     */
    static function parse($game,$category, $request) {
        //test si le jeu existe
        if(!in_array($game, GAMES)){
            return Helper::loadErrorWith(NOT_FOUND, NO_SUCH_GAME." ($game)");
        }

        //test si la catégorie existe pour ce jeu
        if($category!=null && !in_array($category, GAMES_CATEGORIES[$game])){
            return Helper::loadErrorWith(NOT_FOUND, NO_SUCH_CATEGORY." ($category)");
        }

        //si c'est une news
        if($category === CATEGORY_NEWS){
            require  'category/News.php';
            if(is_array($request) || $request === null) {
                return News::getAllNews($game, $category, $request);
            }

            return News::getANews($game, $category, $request);
        }

        require  'category/GameData.php';
        return GameData::getGameInfo($game, $request);
    }
}