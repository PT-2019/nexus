package categories.games;

import categories.Category;
import org.json.JSONObject;

/**
 * This class represent a game
 *
 * @Author Pierre R.
 * @version 1.0
 * @since 1.0
 */

public class Game implements Category {


    /**
     * Name of the game
     */
    private String name;

    /**
     * Id of the game
     */
    private Integer id;

    /**
     * Version of the game
     */
    private String version;


    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public String getVersion() {
        return version;
    }


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
