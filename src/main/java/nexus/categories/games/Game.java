package nexus.categories.games;

import nexus.categories.Category;
import org.json.JSONObject;

/**
 * This class represent a game
 *
 * @author Pierre R.
 * @version 1.1
 * @since 1.0
 */
public class Game implements Category {

    /** Name of the game */
    private String name;

    /** Id of the game */
    private Integer id;

    /** Version of the game */
    private String version;


    public Game() {
        this.name = "";
        this.id = -1;
        this.version = "";
    }

    // ------------------------------ GETTERS ----------------------------- \\

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public String getVersion() {
        return version;
    }

    // ------------------------------ IMPLEMENTS ----------------------------- \\


    /**
     * Fill the object with the data contained in the json object
     * @param info object Json containing information about the game
     */
    @Override
    public void parseResponse(JSONObject info) {
        this.name = info.getString("name");
        this.id = info.getInt("id");
        this.version = info.getString("version");
    }
}
