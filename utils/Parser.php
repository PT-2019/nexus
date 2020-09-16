<?php

/**
 * Class Parser
 *
 * Parse any request to matching classes
 *
 * @see News
 * @see Game
 *
 * @since 1.0
 * @version 2.0
 */
class Parser {

    /**
     * Parse a basic request
     * @param $category string what are we looking for : games ? news ? ....
     * @param $id string|int are we looking for a specific game/news ?
     * @param $request array|null was there specific filter (name=?, limit=? etc....)
     * @return array json request result or error
     * @since 1.0
     * @version 2.0
     */
    static function parse($category, $id, $request) {
        if($category == "games"){
            require 'nexus/Game.php';
            if($id != null) return Game::getGameByID($id);
            else return Game::getGames($request);
        }

        if($category == "news"){
            require 'nexus/News.php';
            if($id != null) return News::getNewsFromID($id);
            else return News::getNewsFromRequest($request);
        }

        return Helper::loadErrorWith(NOT_ALLOWED, NO_SUCH_CATEGORY);
    }
}