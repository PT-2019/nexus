package categories.news;

import categories.Category;
import org.json.JSONObject;

/**
 * This class represent an new
 *
 * @Author Pierre R.
 * @version 1.0
 * @since 1.0
 */

public class News implements Category{


    /**
     * Title of the new
     */
    private String title;

    /**
     * Subtitle of the new
     */
    private String subTitle;

    /**
     * Date of the new
     */
    private String release;

    /**
     * Id of the new
     */
    private Integer id;


    /**
     * Path to the image
     */

    private String pathImage;


    /**
     * Fill the object with the data contained in the json object
     * @param info info object Json containing information about the new
     */
    public void parseResponse(JSONObject info) {
        this.title =  info.getString("title");
        this.subTitle = info.getString("sub_title");
        this.id = info.getInt("id");
        this.release = info.getString("released");
        this.pathImage = info.getString("img");
    }


    /**
     * Print your object in the console (Test method)
     */

    public void print() {
        System.out.println("---" + this.pathImage + "---");
        System.out.println("---" + this.title+ "---");
        System.out.println("---" + this.subTitle + "---");
        System.out.println("---" + this.release + "---");
        System.out.println("---" + this.id + "---");
        System.out.println();
    }
}