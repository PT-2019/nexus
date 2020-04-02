<?php

/**
 * Class Helper
 *
 * @since 1.0
 * @version 1.0
 */
class Helper {

    /**
     * @param $val array("status": error code, "type": error message type)
     * @param $msg string specific error message
     * @return array json error
     * @since 1.0
     * @version 1.0
     */
    static function loadErrorWith($val, $msg){
        $err = ERROR_MESSAGE;
        $err["status"] = $val["status"];
        http_response_code( $val["status"] );
        $err["type"] = $val["type"];
        $err["message"] = $msg;
        return $err;
    }

    /**
     * Convert mysqli result to our json format
     * @param $result mysqli_result mysqli result
     * @return array json
     * @since 1.0
     * @version 1.0
     */
    static function resultAsJSON($result){
        $json = array(
            "result"=>array(),
        );

        if($result !== null) {
            while ($enr = mysqli_fetch_assoc($result)) {
                array_push($json["result"], $enr);
            }
        }

        return $json;
    }
}