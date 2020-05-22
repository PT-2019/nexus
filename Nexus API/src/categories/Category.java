package categories;

import org.json.JSONObject;

/**
 * @Author Pierre R.
 * @version 1.0
 * @since 1.0
 */

public interface Category{

    /**
     * Fill an object which implement Category with the data contained in the json object
     * @param info object Json containing information
     */

    void parseResponse(JSONObject info);

}


