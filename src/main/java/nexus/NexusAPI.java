package nexus;

import nexus.categories.games.Game;
import nexus.categories.news.News;
import nexus.exception.NexusRequestException;
import nexus.http.NexusRequest;
import nexus.http.NexusRequestParser;
import nexus.http.UrlBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Principal controller of the wrapper
 *
 * @author Pierre R.
 * @contributor Quentin R.A.
 * @version 1.2
 * @since 1.0
 */
public class NexusAPI {

    /** The connection with the api */
    private final NexusRequest request;
    /** Helper to build the url */
    private final UrlBuilder build;

    /**
     * Instantiate all object
     */
    public NexusAPI(){
        this.request = new NexusRequest();
        this.build = new UrlBuilder();
    }

    // ------------------------------ NEWS ----------------------------- \\

    /**
     * Get all the new of the game
     *
     * @param game the game
     * @return array of news or null
     * @throws IllegalArgumentException if the game doesnt exist
     */
    public ArrayList<News> getAllNews(Game game){ return getAllNews(game.getName()); }

    /**
     * Get all the new of the game
     *
     * @param game the game as a string
     * @return array of news
     * @throws IllegalArgumentException if the game doesnt exist
     */
    public ArrayList<News> getAllNews(String game) {
        if(game.isEmpty()) throw new IllegalArgumentException("This game is empty");

        this.request.prepareGetRequest(this.build.search(game+"/news"));
        return NexusRequestParser.getListCategory(this.request.readResponse(), News.class);
        //return new ArrayList<>();
    }

    /**
     * Get the new associated with the id
     *
     * @param game the game
     * @param id id of the new
     * @return return the new associated with the id
     * @throws IllegalArgumentException if name is empty
     */
    public News getNewsByID(Game game, Integer id) throws IllegalArgumentException {
        return getNewsByID(game.getName(), id);
    }

    /**
     * Get the new associated with the id
     *
     * @param game the game
     * @param id id of the new
     * @return return the new associated with the id
     * @throws IllegalArgumentException if name is empty
     */
    public News getNewsByID(String game, Integer id) throws IllegalArgumentException {
        if(game.isEmpty()) throw new IllegalArgumentException("This game is empty");
        //HashMap<String, String> parameter = new HashMap<>();
        //parameter.put("id", id.toString());
        //this.request.prepareGetRequest(this.build.addParameter(this.build.search(game+"/news"), parameter));
        this.request.prepareGetRequest(this.build.search(game+"/news/"+id));
        String response = this.request.readResponse();
        return NexusRequestParser.getInformationCategory(response, new News());
    }

    // ------------------------------ GAMES ----------------------------- \\

    /**
     * Get the game associated with the string
     *
     * @param name name of the game
     * @return return the game associated with the name
     */
    public Game getGameByName(String name){
        this.request.prepareGetRequest(this.build.search(name));
        String response = this.request.readResponse();
        return NexusRequestParser.getInformationCategory(response, new Game());
    }
}
