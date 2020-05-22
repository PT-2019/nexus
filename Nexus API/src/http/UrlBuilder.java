package http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;


/**
 * This class helps to build url more easily from a url base
 * @Author Pierre R.
 * @version 1.0
 * @since 1.0
 */

public class UrlBuilder {


    /**
     * Entrance of the api Nexus
     */

    private final String baseurl = "https://lgs-nexus.000webhostapp.com/";

    /**
     * Add your string to the base url, and return it
     * @param plus the string to add to the base url
     * @return The completed url
     */
    public String search(String plus){
        return this.baseurl+plus;
    }


    /**
     *  Complete your url with parameters
     * @param target url
     * @param param parameters of your request
     * @return the url completed with your parameters
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
