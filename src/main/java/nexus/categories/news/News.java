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
    private String titleFr, titleEn;
    /** Date of the new */
    private String release;
    /** Id of the new */
    private Integer id, id_game;
    /** Path to the image */
    private String pathImage;
    /** Content */
    private String contentFr, contentEn;

    // ------------------------------ CONSTRUCTORS ----------------------------- \\

    public News(){ this(""); }
    public News(String title){ this(title, title,"",-1,-1,"","",""); }

    public News(String title_fr, String title_en, String release, Integer id, Integer id_game,
                String pathImage, String content_fr, String content_en) {
        this.titleFr = title_fr;
        this.titleEn = title_en;
        this.release = release;
        this.id = id;
        this.id_game = id_game;
        this.pathImage = pathImage;
        this.contentFr = content_fr;
        this.contentEn = content_en;
    }

    // ------------------------------ GETTERS ----------------------------- \\

    public String getTitleFr() { return titleFr; }
    public String getTitleEn() { return titleEn; }
    public String getRelease() { return release; }
    public Integer getId() { return id; }
    public Integer getId_game() { return id_game; }
    public String getPathImage() { return pathImage; }
    public String getContentFr() { return contentFr; }
    public String getContentEn() { return contentEn; }

    // ------------------------------ UTILS ----------------------------- \\

    /**
     * Fill the object with the data contained in the json object
     * @param info info object Json containing information about the new
     */
    @Override
    public void parseResponse(JSONObject info) {
        this.titleEn =  info.getString("title_en");
        this.titleFr =  info.getString("title_fr");
        this.id = info.getInt("id_news");
        this.id_game = info.getInt("id_game");
        this.release = info.getString("date");
        this.pathImage = info.getString("img_url");
        if(info.has("content_url_fr")) this.contentFr = info.getString("content_url_fr");
        if(info.has("content_url_en")) this.contentEn = info.getString("content_url_en");
    }

    @Override
    public String toString() {
        return "News{" +
                "titleFr='" + titleFr + '\'' +
                ", titleEn='" + titleEn + '\'' +
                ", release='" + release + '\'' +
                ", id=" + id +
                ", id_game=" + id_game +
                ", pathImage='" + pathImage + '\'' +
                ", contentFr='" + contentFr + '\'' +
                ", contentEn='" + contentEn + '\'' +
                '}';
    }

    public String getTitle(String language) { return language.equals("fr") ? titleFr:titleEn; }
    public String getContent(String language) { return language.equals("fr") ? contentFr:contentEn; }
}