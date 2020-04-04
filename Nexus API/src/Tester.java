
import categories.games.Game;
import categories.news.News;
import java.util.ArrayList;

public class Tester {


    public static void main(String[] args){


        Rapporteur rap = new Rapporteur();
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

    }
}
