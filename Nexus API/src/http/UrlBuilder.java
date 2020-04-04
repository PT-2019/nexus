package http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;


/**
 *  Author : Pierre R.
 *
 *
 */
public class UrlBuilder {

    private final String baseurl = "https://lgs-nexus.000webhostapp.com/";

    /**
     * Add your string to the base url, and return it
     * @param plus
     * @return [String] Url
     */
    public String search(String plus){
        return this.baseurl+plus;
    }


    /**
     *  Add parameter to your url
     * @param target [String] Url
     * @param param [Map<String,String>] Paramater
     * @return The url with your parameter
     * @throws UnsupportedEncodingException
     */
    public String addParameter(String target, Map<String,String> param) throws UnsupportedEncodingException{
        StringBuilder resultUrl = new StringBuilder(target);
        resultUrl.append("?");
        for (Map.Entry<String, String> entry : param.entrySet()) {
            resultUrl.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            resultUrl.append("=");
            resultUrl.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
            resultUrl.append("&");

        }return resultUrl.length() > 0 ? resultUrl.substring(0,resultUrl.length()-1) : resultUrl.toString();
    }
}
