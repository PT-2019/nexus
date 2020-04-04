package categories.games;

import categories.Category;
import org.json.JSONObject;

public class Game implements Category {


    private String name;
    private Integer id;
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

    @Override
    public void parseResponse(JSONObject info) {
        this.name = info.getString("name");
        this.id = info.getInt("id");
        this.version = info.getString("version");
    }
}
