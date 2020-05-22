import categories.games.Game;
import categories.news.News;
import exception.RequestException;
import http.NexusRequest;
import http.Parser;
import http.UrlBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Principal controller of the wrapper
 *
 * @Author Pierre R.
 * @version 1.0
 * @since 1.0
 */
public class Nexus {


    /**
     * The connection with the api
     */

    private NexusRequest request;

    /**
     * Helper to build the url
     */

    private UrlBuilder build;

    /**
     * Debug mode in console
     */
    private static boolean debug = false;


    /**
     * Logs
     */

    private ArrayList<String> log;


    /**
     * Instantiate all object
     */
    public Nexus(){
        this.request = new NexusRequest();
        this.build = new UrlBuilder();
        this.log = new ArrayList<>();
    }

    /**
     * Instantiate all object with the mode debug
     * @param debugmode true to active the debug mode
     */

    public Nexus(boolean debugmode){
        this.request = new NexusRequest();
        this.build = new UrlBuilder();
        this.debug = debugmode;
        this.log = new ArrayList<>();
    }


    /**
     * Get the game associated with the string
     *
     * @param name name of the game
     * @return return the game associated with the name
     */

    public Game getGameByName(String name){
        try{
            request.getRequest(build.search(name));
            String response = request.readResponse();
            return Parser.getInformationCategory(response, new Game());
        }catch(IOException | RequestException e){
            if (this.debug) this.log.add(name + ": " + e.getMessage());
            return null;
        }
    }

    /**
     * Get the new associated with the id
     *
     * @param game the game
     * @param id id of the new
     * @return return the new associated with the id
     * @throws IllegalArgumentException
     */

    public News getNewById(Game game, Integer id)
        throws IllegalArgumentException{
            if(game.getName().isEmpty())throw new IllegalArgumentException("This game is empty");
            try {
                HashMap<String, String> parameter = new HashMap<>();
                parameter.put("id", id.toString());
                request.getRequest(build.addParameter(build.search(game.getName()+"/news"), parameter));
                String response = request.readResponse();
                return Parser.getInformationCategory(response, new News());
            }catch(IOException | RequestException e) {
                if (this.debug)                 this.log.add(game.getName() + " id " + id.toString() + ": " + e.getMessage());
                return null;
            }
        }


    /**
     * Get all the new of the game
     *
     * @param game the game
     * @return array of news or null
     * @throws IllegalArgumentException if the game doesnt exist
     */
    public ArrayList<News> getNews(Game game)
        throws IllegalArgumentException {
        if (game.getName().isEmpty()) throw new IllegalArgumentException("This game is empty");
        try {
            request.getRequest(build.search(game.getName() + "/news"));
            String response = request.readResponse();
            ArrayList<News> listNews = Parser.getListCategory(response, News.class);
            return listNews;
        } catch (IOException | RequestException e) {
            if (this.debug)
                this.log.add(game.getName() + ": " + e.getMessage());
            return null;
        }
        }

    /**
     * Return the logs
     * @return logs
     */

    public ArrayList<String> getLog(){
            return this.log;
        }
}
