package categories.news;

import categories.Category;
import org.json.JSONObject;

public class News implements Category{

    private String title;
    private String subTitle;
    private String release;
    private Integer id;


    private String pathImage;

    public News(String title, String subTitle, String release, Integer id, String pathImage) {
        this.title = title;
        this.subTitle = subTitle;
        this.release = release;
        this.id = id;
        this.pathImage = pathImage;
    }

    public News(){}

    public void parseResponse(JSONObject info) {
        this.title =  info.getString("title");
        this.subTitle = info.getString("sub_title");
        this.id = info.getInt("id");
        this.release = info.getString("released");
        this.pathImage = info.getString("img");
    }

    public void print() {
        System.out.println("---" + this.pathImage + "---");
        System.out.println("---" + this.title+ "---");
        System.out.println("---" + this.subTitle + "---");
        System.out.println("---" + this.release + "---");
        System.out.println("---" + this.id + "---");
        System.out.println();
    }
}