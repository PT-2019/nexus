package nexus.http;

import nexus.categories.Category;
import nexus.categories.games.Game;
import nexus.categories.news.News;
import nexus.exception.NexusParserException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class which parse the response of the api into an java object
 *
 * @author Pierre R.
 * @version 1.0
 * @since 1.0
 */
public class NexusRequestParser {

    /**
     *  Parse the response of the api and return an array
     * @param response response of the api
     * @param cat class need to implements Category
     * @param <T> type need to implements Category
     * @return the array of objects
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
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ie) {
                throw new NexusParserException(ie);
            }
        } else return list; //throw new NexusParserException("Nothing to parse ! ");
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
            throw new NexusParserException(jse);
        }
        throw new NexusParserException("Nothing to parse !");
    }


    /**
     * Handle the error
     * @param e your error
     */
    public static void errorHandler(Exception e){
        System.err.println(e + " / Please contact the dev team ! /");
        e.printStackTrace();
    }

    public static Map<Integer, Game> getAllGames(String response) {
        try {
            if (response.length() > 0) {
                JSONObject parse = new JSONObject(response);
                JSONArray array = parse.getJSONArray("result");

                HashMap<Integer, Game> games = new HashMap<>();

                for (Object e:array) {
                    JSONObject o = ((JSONObject)e);
                    Game game = new Game();
                    game.parseResponse(o);
                    games.put(
                            o.getInt("id_game"),
                            game
                    );
                }
                return games;
            }
        }catch(JSONException jse){
            throw new NexusParserException(jse);
        }
        throw new NexusParserException("Nothing to parse !");
    }

    public static News getNewsByID(String response) {
        try {
            if (response.length() > 0) {
                JSONObject parse = new JSONObject(response);
                News cat = new News();
                cat.parseResponse(parse);
                return cat;
            }
        }catch(JSONException jse){
            throw new NexusParserException(jse);
        }
        throw new NexusParserException("Nothing to parse !");
    }

    public static Game getGame(String response) {
        try {
            if (response.length() > 0) {
                JSONObject parse = new JSONObject(response);
                Game cat = new Game();
                cat.parseResponse(parse);
                return cat;
            }
        }catch(JSONException jse){
            throw new NexusParserException(jse);
        }
        throw new NexusParserException("Nothing to parse !");
    }
}






