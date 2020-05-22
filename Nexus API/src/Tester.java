import categories.games.Game;
import categories.news.News;
import java.util.ArrayList;

/**
 * @Author Pierre R.
 * @version 1.0
 * @since 1.0
 */

public class Tester {


    public static void main(String[] args){


        Nexus rap = new Nexus(true);
        System.out.println("------------------- Start the test -----------------------");
        System.out.println("Request to get a game by his name : ");
        Game g = rap.getGameByName("enigma-editor");
        System.out.println("Name : " + g.getName());
        System.out.println("Id : " + g.getId());
        System.out.println("Version : " + g.getVersion());
        System.out.println();

       System.out.println("Request to get a new by id : ");
       News n = rap.getNewById(g, 31);
       n.print();
       System.out.println();

       System.out.println("Request to get all news of a game : ");
        ArrayList<News> arr = rap.getNews(g);
        for(News a : arr){
            a.print();
        }

        ArrayList<String> err = rap.getLog();
       for(int i = 0; i < err.size(); i++){
           System.out.println(err.get(i));
       }

   }
}
