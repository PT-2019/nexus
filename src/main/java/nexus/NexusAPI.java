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
import java.util.Map;

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
    public ArrayList<News> getAllNews(Game game){ return getAllNews(game.getId()); }

    /**
     * Get all the new of the game
     *
     * @param id the game id
     * @return array of news
     * @throws IllegalArgumentException if the game doesnt exist
     */
    public ArrayList<News> getAllNews(int id) {
        if(id <= 0) throw new IllegalArgumentException("Invalid game");

        this.request.prepareGetRequest(this.build.search("news?game="+id));
        return NexusRequestParser.getListCategory(this.request.readResponse(), News.class);
    }

    /**
     * Get the new associated with the id
     *
     * @param id id of the new
     * @return return the new associated with the id
     * @throws IllegalArgumentException if name is empty
     */
    public News getNewsByID(Integer id) throws IllegalArgumentException {
        if(id <= 0) throw new IllegalArgumentException("This game is empty");
        this.request.prepareGetRequest(this.build.search("news/"+id));
        String response = this.request.readResponse();
        return NexusRequestParser.getNewsByID(response);
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

    public Map<Integer, Game> getAllGames() {
        this.request.prepareGetRequest(this.build.search("games"));
        String response = this.request.readResponse();
        return NexusRequestParser.getAllGames(response);
    }
}
