package nexus.http;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * This class helps to build url more easily from a url base
 * @author Pierre R.
 * @version 1.0
 * @since 1.0
 */
public class UrlBuilder {

    /** Entrance of the api Nexus */
    private static final String BASE_URL = "https://lgs-nexus.000webhostapp.com/";

    public UrlBuilder() {}

    /**
     * Add your string to the base url, and return it
     * @param plus the string to add to the base url
     * @return The completed url
     */
    public String search(String plus){
        return BASE_URL +plus;
    }

    /**
     * Complete your url with parameters
     * @param target url
     * @param param parameters of your request
     * @return the url completed with your parameters
     */
    public String addParameter(String target, Map<String,String> param) {
        StringBuilder resultUrl = new StringBuilder(target);
        resultUrl.append("?");
        for (Map.Entry<String, String> entry : param.entrySet()) {
            resultUrl.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            resultUrl.append("=");
            resultUrl.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
            resultUrl.append("&");

        }
        return resultUrl.length() > 0 ? resultUrl.substring(0,resultUrl.length()-1) : resultUrl.toString();
    }
}
