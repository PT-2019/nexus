<?php

/**
 * Class News
 *
 * @since 1.0
 * @version 1.0
 */
class News {

    /**
     * Return a specific news for a game from an id
     *
     * @param $game string game
     * @param $category string news
     * @param $request string id
     * @return array json
     *
     * @since 1.0
     * @version 1.0
     */
    static function getNew($game, $category, $request){
        if(intval($request) === 0 && $request !== "0"){//test id
            return Helper::loadErrorWith(NOT_ALLOWED, INVALID_VALUE." ($request for $game($category))");
        }

        //connection à la base
        $db = new DBAccess();
        $db->connect();
        $result = $db->requestSQLResult($db->request(array(
            "query"=>"Select * From News Where game='".$game."' AND id=".$request.";",
        )));
        $db->close();
        //traitement du résultat
        $result = Helper::resultAsJSON($result);

        //Récupération du contenu
        if(isset($result["result"][0])){
            $retour = $result["result"][0]["content"];
            $retour = file_get_contents($retour);
            if($retour !== false) $result["result"][0]["content"] = $retour;
        }

        return $result;
    }

    /**
     * Return news for a game
     *
     * @param $game string game
     * @param $category string news
     * @param $request array option (?field=value...)
     * @return array json
     *
     * @since 1.0
     * @version 1.0
     */
    static function getNews($game, $category, $request){
        $LIMIT = 10;
        $OFFSET = 0;
        $add_on = "";
        $params = ["c"=>"", "v" =>array()];

        if($request !== null){
            //test des options
            $array = constant($game."_".$category."_fields");
            foreach ($request as $key => $value){
                if(!in_array($key, $array)){
                    return Helper::loadErrorWith(NOT_ALLOWED, NO_SUCH_OPTION." ($key for $game/news)");
                }
            }

            //parse
            foreach ($request as $key => $value){
                switch ($key){
                    case 'id':
                        if(intval($value) === 0 && $value !== "0"){
                            return Helper::loadErrorWith(NOT_ALLOWED, INVALID_VALUE." ($value for $key)");
                        }
                        $add_on .= "AND id=?";
                        $params["c"] .= "i";
                        array_push($params["v"], intval($value));
                        break;
                    case 'limit':
                        if(intval($value) <= 25 && intval($value) >= 1){
                            $LIMIT = intval($value);
                        } else return Helper::loadErrorWith(NOT_ALLOWED, INVALID_VALUE." ($value for $key)");
                        break;

                    case 'offset':
                        if(intval($value) <= 25 && intval($value) >= 0){
                            $OFFSET = intval($value);
                        } else return Helper::loadErrorWith(NOT_ALLOWED, INVALID_VALUE." ($value for $key)");

                        break;
                }
            }
        }

        //envoi de la requête
        $query = "Select title, sub_title, released, id, img  From News Where game='".$game."' ".$add_on." ORDER BY id DESC LIMIT ".$OFFSET.",".($LIMIT)." ";

        $db = new DBAccess();
        $db->connect();
        $result = $db->requestSQLResult($db->request(array(
            "query"=>$query,
            "types"=>$params["c"],
            "var"=>$params["v"],
        )));

        //var_dump($query);var_dump($params);

        //traitement du résultat
        $result = Helper::resultAsJSON($result);
        $result["offset"] = $OFFSET;
        $result["limit"] = $LIMIT;
        $result["total"] = $db->getRowCount($db->request(array(
            "query"=>"Select Count(*) From News Where game='".$game."' ".$add_on.";",
            "types"=>$params["c"],
            "var"=>$params["v"],
        )));

        $db->close();

        return $result;
    }

}