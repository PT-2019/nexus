<?php

/**
 * Class News
 *
 * @since 1.0
 * @version 2.0
 */
class News {

    /**
     * Return a specific news for a game from an id
     *
     * @param $id string id
     * @return array json
     *
     * @since 1.0
     * @version 2.0
     */
    static function getNewsFromID($id){
        if(intval($id) === 0 && $id !== "0" || intval($id) < 0){//test id
            return Helper::loadErrorWith(NOT_ALLOWED, BAD_ID." ($id as news ID)");
        }
        //connection à la base
        $db = new DBAccess();
        $db->connect();
        $result = $db->requestSQLResult($db->request(array(
            "query"=>"Select * from news where id_news=".$id.";",
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

    /**
     * Return news according to a request
     *
     * @param $request array|null request
     * @return array json
     *
     * @since 1.0
     * @version 2.0
     */
    static function getNewsFromRequest($request){
        if($request !== null) {
            $limit = 10;
            $offset = 0;
            $add_on = "";
            $params = ["c"=>"", "v" =>array()];

            foreach ($request as $key => $value) {
                switch ($key) {
                    case 'game':
                        if (intval($value) === 0 && $value !== "0") {
                            return Helper::loadErrorWith(NOT_ALLOWED, INVALID_VALUE . " ($value for $key)");
                        }
                        $add_on .= "AND id_game=?";
                        $params["c"] .= "i";
                        array_push($params["v"], intval($value));
                        break;
                    case 'limit':
                        if (intval($value) <= REQUEST_LIMIT && intval($value) >= 1) {
                            $limit = intval($value);
                        } else return Helper::loadErrorWith(NOT_ALLOWED, INVALID_VALUE . " ($value for $key)");
                        break;

                    case 'offset':
                        if (intval($value) <= REQUEST_LIMIT && intval($value) >= 0) {
                            $offset = intval($value);
                        } else return Helper::loadErrorWith(NOT_ALLOWED, INVALID_VALUE . " ($value for $key)");

                        break;
                    default:
                        return Helper::loadErrorWith(NOT_ALLOWED, NO_SUCH_OPTION." ($key)");
                }
            }

            $add_on = substr($add_on, 4);

            //envoi de la requête
            $query = "Select *  From news where $add_on ORDER BY id_news DESC LIMIT ".$offset.",".($limit)." ";

            $db = new DBAccess();
            $db->connect();
            $result = $db->requestSQLResult($db->request(array(
                "query"=>$query,
                "types"=>$params["c"],
                "var"=>$params["v"],
            )));

            //traitement du résultat
            $result = Helper::resultAsJSON($result);
            $result["offset"] = $offset;
            $result["limit"] = $limit;
            $result["total"] = $db->getRowCount($db->request(array(
                "query"=>"Select Count(*) From news where $add_on;",
                "types"=>$params["c"],
                "var"=>$params["v"],
            )));
            $db->close();
        } else {
            //connection à la base
            $db = new DBAccess();
            $db->connect();
            $result = $db->requestSQLResult($db->request(array("query"=>"Select * from news ORDER BY id_news LIMIT 0,".REQUEST_LIMIT.";",)));
            //traitement du résultat
            $result = Helper::resultAsJSON($result);
            $result['offset'] = 0;
            $result['limit'] = REQUEST_LIMIT;
            $result["total"] = $db->getRowCount($db->request(array("query"=>"Select Count(*) From news;")));
            $db->close();
        }

        return $result;
    }
}