package nexus.categories.news;

import nexus.categories.Category;
import org.json.JSONObject;

/**
 * This class represent an new
 *
 * @author Pierre R.
 * @contributor Quentin R.A.
 * @version 1.1
 * @since 1.0
 */
public class News implements Category{

    /** Title of the new */
    private String title;
    /** Subtitle of the new */
    private String subTitle;
    /** Date of the new */
    private String release;
    /** Id of the new */
    private Integer id;
    /** Path to the image */
    private String pathImage;

    // ------------------------------ CONSTRUCTORS ----------------------------- \\

    public News(){ this("", "","",-1,""); }
    public News(String title){ this(title, "","",-1,""); }

    public News(String title, String subTitle, String release, Integer id, String pathImage) {
        this.title = title;
        this.subTitle = subTitle;
        this.release = release;
        this.id = id;
        this.pathImage = pathImage;
    }

    // ------------------------------ GETTERS ----------------------------- \\

    public String getTitle() { return title; }
    public String getSubTitle() { return subTitle; }
    public String getRelease() { return release; }
    public Integer getId() { return id; }
    public String getPathImage() { return pathImage; }

    // ------------------------------ UTILS ----------------------------- \\

    /**
     * Fill the object with the data contained in the json object
     * @param info info object Json containing information about the new
     */
    @Override
    public void parseResponse(JSONObject info) {
        this.title =  info.getString("title");
        this.subTitle = info.getString("sub_title");
        this.id = info.getInt("id");
        this.release = info.getString("released");
        this.pathImage = info.getString("img");
    }

    /** @deprecated use {@link #toString()} instead. */
    @Deprecated
    public void print() {
        System.out.println("---" + this.pathImage + "---");
        System.out.println("---" + this.title+ "---");
        System.out.println("---" + this.subTitle + "---");
        System.out.println("---" + this.release + "---");
        System.out.println("---" + this.id + "---");
        System.out.println();
    }

    @Override
    public String toString() {
        return "News{" + "title='" + title + '\'' + ", subTitle='" + subTitle + '\'' + ", release='" + release + '\'' +
                ", id=" + id + ", pathImage='" + pathImage + '\'' + '}';
    }
}