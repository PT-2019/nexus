package http;

import categories.Category;
import org.json.JSONArray;
import org.json.JSONObject;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Parser {


    public static <T extends Category>  ArrayList<T> getListCategory(String response, Class<T> cat){

        ArrayList<T> list = new ArrayList<>();

        if(response.length() > 0) {
            JSONObject parse = new JSONObject(response);
            JSONArray array = parse.getJSONArray("result");
            try {
                for (int i = 0; i < parse.getInt("total"); i++) {
                    JSONObject info = array.getJSONObject(i);
                    T tmp = cat.getDeclaredConstructor().newInstance();
                    tmp.parseResponse(info);
                    list.add(tmp);
                }
                return list;
            } catch (InstantiationException ie) {
                System.err.print(ie);
            }catch(IllegalAccessException iae){
                System.err.print(iae);
            }catch(NoSuchMethodException nsme){
                System.err.print(nsme);
            }catch(InvocationTargetException ite){
                System.err.print(ite);
            }
        }else System.out.println("Nothing to parse ! ");
        return null;
    }

    public static <T extends Category> T getInformationCategory(String response, T cat){

        if(response.length() > 0) {
            JSONObject parse = new JSONObject(response);
            JSONArray array = parse.getJSONArray("result");
            JSONObject info = array.getJSONObject(0);
            cat.parseResponse(info);
        }else System.out.println("Nothing to parse !");
        return cat;
    }

    public static void errorHandler(Exception e){
        System.err.println(e + " / Please contact the dev team ! /");
        System.exit(0);
    }
}






