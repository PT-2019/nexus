<?php

/**
 * Class DBAccess
 *
 * Manage DB Access
 *
 * @since 1.0
 * @version 1.0
 */
class DBAccess {

    private $connexion;

    /**
     * Open DB connexion
     *
     * @since 1.0
     * @version 1.0
     */
    public function connect(){
        $this->connexion = mysqli_connect(DB_HOST, DB_USER, DB_PWD, DB_NAME);
        mysqli_set_charset($this->connexion, "utf8");//set UTF-8
    }

    /**
     * Close DB connexion
     *
     * @since 1.0
     * @version 1.0
     */
    public function close(){
        mysqli_close($this->connexion);
    }

    /**
     * Return prepared request from sql
     *
     * @param $request array
     *      query : SQL request
     *      types : '?' types such as "ssi" for string string int
     *      var : array of values for '?' parameters
     *
     * @return false|mysqli_stmt sql prepared request or false (error)
     *
     * @since 1.0
     * @version 1.0
     */
    public function request($request){
        $stmt = mysqli_prepare($this->connexion, $request["query"]);
        if($stmt === false){return false;}
        if(isset($request["types"]) && $request["types"] != "" && isset($request["var"])) {
            mysqli_stmt_bind_param($stmt, $request["types"], ... $request["var"]);
        }
        return $stmt;
    }

    /**
     * Process request and return SQL result
     * @param $stmt mysqli_stmt mysqli statement
     * @return false|mysqli_result|null
     *
     * @since 1.0
     * @version 1.0
     */
    public function requestSQLResult($stmt){
        $res = null;
        //execution
        if(!mysqli_stmt_execute($stmt)) return null;
        //s'il existe un résultat
        $res = mysqli_stmt_get_result($stmt);
        mysqli_stmt_close($stmt);

        if($res->num_rows == 0){
            return null;
        }
        return $res;
    }

    /**
     * Process request and return now count
     * @param $stmt mysqli_stmt mysqli statement
     * @return int|null row count or null
     *
     * @since 1.0
     * @version 1.0
     */
    public function getRowCount($stmt){
        $res = null;
        //execution
        if(!mysqli_stmt_execute($stmt)) return null;
        //s'il existe un résultat
        $res = mysqli_stmt_get_result($stmt);
        mysqli_stmt_close($stmt);


        return mysqli_fetch_assoc($res)["Count(*)"];
    }

    /**
     * Process request and true if there is at least one match or false
     * @param $stmt mysqli_stmt mysqli statement
     * @return boolean true if there is at least one match or false
     *
     * @since 1.0
     * @version 1.0
     */
    public function hasSQLField($stmt){
        //execution
        if(!mysqli_stmt_execute($stmt)) return false;
        //s'il existe un résultat
        $res = mysqli_stmt_get_result($stmt);
        mysqli_stmt_close($stmt);

        if($res->num_rows == 0){
            return false;
        }

        return true;
    }
}