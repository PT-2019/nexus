import categories.games.Game;
import categories.news.News;
import exception.RequestException;
import http.NexusRequest;
import http.Parser;
import http.UrlBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Rapporteur {


    private NexusRequest request;
    private UrlBuilder build;


    public Rapporteur(){
        this.request = new NexusRequest();
        this.build = new UrlBuilder();
    }


    public Game getGameByName(String name){
        try{
            request.prepareGetRequest(build.search(name));
            String response = request.readResponse();
            return Parser.getInformationCategory(response, new Game());
        }catch(IOException ioe){
            Parser.errorHandler(ioe);
        }catch(RequestException re){
            Parser.errorHandler(re);
        }
        return null;
    }

    public News getNewById(Game game, Integer id)
        throws IllegalArgumentException{
            if(game.getName().isEmpty())throw new IllegalArgumentException("This game is empty");
            try {
                HashMap<String, String> parameter = new HashMap<>();
                parameter.put("id", id.toString());
                request.prepareGetRequest(build.addParameter(build.search(game.getName()+"/news"), parameter));
                String response = request.readResponse();
                return Parser.getInformationCategory(response, new News());
            }catch(IOException ioe){
                Parser.errorHandler(ioe);
            }catch(RequestException re){
                Parser.errorHandler(re);
            }
        return null;
    }


    public ArrayList<News> getNews(Game game)
        throws IllegalArgumentException{
            if(game.getName().isEmpty())throw new IllegalArgumentException("This game is empty");
            try {
                request.prepareGetRequest(build.search(game.getName()+"/news"));
                String response = request.readResponse();
                ArrayList<News> listNews = Parser.getListCategory(response, News.class);
                return listNews;
            }catch(IOException ioe){
                Parser.errorHandler(ioe);
            }catch(RequestException re){
                Parser.errorHandler(re);
            }
            return null;
        }

}
