package nexus.categories.games;

import nexus.categories.Category;
import org.json.JSONObject;

import java.util.Objects;

/**
 * This class represent a game
 *
 * @author Pierre R.
 * @author Quentin Ra
 * @version 2.0
 * @since 1.0
 */
public class Game implements Category {

    /** Name of the game */
    private String name;

    /** Id of the game */
    private Integer id;

    /** Version of the game */
    private String version;

    private String ddl_link_w, ddl_link_l, ddl_link_m;


    public Game() {
        this.name = "";
        this.id = -1;
        this.version = "";
        this.ddl_link_l = null;
        this.ddl_link_w = null;
        this.ddl_link_m = null;
    }

    // ------------------------------ GETTERS ----------------------------- \\

    public String getName() {
        return name;
    }
    public Integer getId() { return id; }
    public String getVersion() {
        return version;
    }
    public String getDdl_link_l() { return ddl_link_l; }
    public String getDdl_link_m() { return ddl_link_m; }
    public String getDdl_link_w() { return ddl_link_w; }

    // ------------------------------ IMPLEMENTS ----------------------------- \\


    /**
     * Fill the object with the data contained in the json object
     * @param info object Json containing information about the game
     */
    @Override
    public void parseResponse(JSONObject info) {
        this.name = info.getString("name");
        this.id = info.getInt("id_game");
        this.version = info.getString("version");
        if(info.get("ddl_link_m") instanceof String) this.ddl_link_m = info.getString("ddl_link_m");
        if(info.get("ddl_link_l") instanceof String) this.ddl_link_l = info.getString("ddl_link_l");
        if(info.get("ddl_link_w") instanceof String) this.ddl_link_w = info.getString("ddl_link_w");
    }

    public String getDDLCurrentOS() {
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("windows")) return this.ddl_link_w;
        if(os.toLowerCase().startsWith("macos")) return this.ddl_link_m;
        if(os.toLowerCase().startsWith("linux")) return this.ddl_link_l;
        else return this.ddl_link_l;
    }
}
