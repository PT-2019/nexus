<?php

/**
 * Class GameData
 *
 * Return data related to LGS Games
 *
 * @since 1.0
 * @version 1.0
 */
class GameData {

    /**
     * Return a specific game data
     * @param $game string name
     * @param $request string options, unused
     * @return array return a json array with the result or an error
     * @since 1.0
     * @version 1.0
     */
    static function getGameInfo($game, $request){
        //envoi de la requête
        $db = new DBAccess();
        $db->connect();
        $result = $db->requestSQLResult($db->request(array(
            "query"=>"Select name, id, version From game Where name='".$game."'",
        )));
        $db->close();

        //traitement du résultat
        $result = Helper::resultAsJSON($result);

        return $result;
    }
}