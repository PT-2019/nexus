package http;

import categories.Category;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Class which parse the response of the api into an java object
 *
 * @Author Pierre R.
 * @version 1.0
 * @since 1.0
 */

public class Parser {

    /**
     *  Parse the response of the api and return an array
     * @param response response of the api
     * @param cat class need to implements Category
     * @param <T> type need to implements Category
     * @return the array of object or null
     */

    public static <T extends Category>  ArrayList<T> getListCategory(String response, Class<T> cat){

        ArrayList<T> list = new ArrayList<>();

        if(response.length() > 0) {
            JSONObject parse = new JSONObject(response);
            JSONArray array = parse.getJSONArray("result");
            try {
                for (int i = 0; i < parse.getInt("total"); i++) {
                    JSONObject info = array.getJSONObject(i);
                    T tmp = cat.getDeclaredConstructor().newInstance();
                    tmp.parseResponse(info);
                    list.add(tmp);
                }
                return list;
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                return null;
            }
        }
        return null; // Return null if nothing to parse
    }


    /**
     *  Parse the response of the api and return an object
     * @param response response of the api
     * @param cat class need to implements Category
     * @param <T> type need to implements Category
     * @return the object or null
     */

    public static <T extends Category> T getInformationCategory(String response, T cat){
        try {
            if (response.length() > 0) {
                JSONObject parse = new JSONObject(response);
                JSONArray array = parse.getJSONArray("result");
                JSONObject info = array.getJSONObject(0);
                cat.parseResponse(info);
                return cat;
            }
        }catch(JSONException jse){
            return null;
        }
        return null;
    }


    /**
     * Handle the error
     * @param e your error
     */

    public static void errorHandler(Exception e){
        System.err.println(e + " / Please contact the dev team ! /");
        e.printStackTrace();
    }
}






