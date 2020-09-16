<?php

/**
 * Class Game
 *
 * Return data related to LGS Games
 *
 * @since 1.0
 * @version 2.0
 */
class Game {

    /**
     * Return a games data
     * @param $request array options
     * @return array return a json array with the result or an error
     * @since 2.0
     * @version 2.0
     */
    public static function getGames($request){
        if($request == null) {
            //envoi de la requête
            $db = new DBAccess();
            $db->connect();
            $result = $db->requestSQLResult($db->request(array(
                "query" => "Select * From game",
            )));
            $db->close();
            //traitement du résultat
            $result = Helper::resultAsJSON($result);

            return $result;
        } else {
            if(!isset($request['name'])){
                return Helper::loadErrorWith(NOT_ALLOWED, NO_SUCH_OPTIONS."(".json_encode($request).")");
            } else {
                if(!in_array($request['name'], GAMES))
                    return Helper::loadErrorWith(NOT_ALLOWED, NO_SUCH_GAME);

                //envoi de la requête
                $db = new DBAccess();
                $db->connect();
                $result = $db->requestSQLResult($db->request(array(
                    "query" => "Select * From game Where name='".$request['name']. "'",
                )));
                $db->close();
                //traitement du résultat
                $result = Helper::resultAsJSON($result);

                return $result;
            }
        }
    }

    /**
     * Return game data
     * @param $id int|string game id
     * @return array game data as a json or an error as json
     * @since 1.0
     * @version 2.0
     */
    public static function getGameByID($id){
        if(intval($id) === 0 && $id !== "0" || intval($id) < 0){//test id
            return Helper::loadErrorWith(NOT_ALLOWED, BAD_ID." ($id as game ID)");
        }
        //connection à la base
        $db = new DBAccess();
        $db->connect();
        $result = $db->requestSQLResult($db->request(array(
            "query"=>"Select * from game where id_game=".$id.";",
        )));
        $db->close();
        //traitement du résultat
        $result = Helper::resultAsJSON($result);

        //Récupération du contenu
        if(!isset($result["result"][0])){
            return Helper::loadErrorWith(NOT_FOUND, NO_SUCH_ID);
        } else {
            $result = $result["result"][0];
        }

        return $result;
    }
}