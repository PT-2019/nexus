import nexus.NexusAPI;
import nexus.categories.games.Game;
import nexus.categories.news.News;
import java.util.ArrayList;

/**
 * @author Pierre R.
 * @version 1.0
 * @since 1.0
 */
public class Test {

    public static void main(String[] args){
        NexusAPI rap = new NexusAPI();
        System.out.println("------------------- Start the test -----------------------");
        System.out.println("Request to get a game by his name : ");
        Game g = rap.getGameByName("enigma-editor");
        System.out.println("Name : " + g.getName());
        System.out.println("Id : " + g.getId());
        System.out.println("Version : " + g.getVersion());
        System.out.println();

       System.out.println("Request to get a new by id : ");
       News n = rap.getNewsByID(31);
       //n.print();
       System.out.println(n);

       System.out.println("Request to get all news of a game : ");
        ArrayList<News> arr = rap.getAllNews(g);
        for(News a : arr){
            //a.print();
            System.out.println(a);
        }
    }

}
