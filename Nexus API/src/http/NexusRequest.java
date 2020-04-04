package http;

import exception.RequestException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;



public class NexusRequest {

    HttpURLConnection req = null;

    public void prepareGetRequest(String target)throws IOException, RequestException{

        URL url = new URL(target);
        this.req = (HttpURLConnection) url.openConnection();
        req.setRequestMethod("GET");
        req.setRequestProperty("content-type", "/application/json");
        if(this.req.getResponseCode() != 200)throw new RequestException(this.req.getResponseCode(), this.req.getResponseMessage());
    }

    public void preparePostRequest(String target)throws IOException, RequestException{

        URL url = new URL(target);
        this.req = (HttpURLConnection) url.openConnection();
        req.setRequestMethod("POST");
        req.setDoOutput(true);
        if(this.req.getResponseCode() != 200)throw new RequestException(this.req.getResponseCode(), this.req.getResponseMessage());
    }


    /* For post method */

    public void postRequest() throws IOException{
        DataOutputStream out = new DataOutputStream(this.req.getOutputStream());
        out.close();
    }

    /* Post Request with parameter */

    public void postRequest(String parameter) throws IOException{

        DataOutputStream out = new DataOutputStream(this.req.getOutputStream());
        out.writeBytes(parameter);
        System.out.print(out);
        out.close();
    }

    public String readResponse()throws IOException{

        InputStream in = this.req.getInputStream();
        BufferedReader buff = new BufferedReader(new InputStreamReader(in));

        String lect;
        StringBuilder str = new StringBuilder();

        while((lect = buff.readLine()) != null){
            str.append(lect);
        }

        buff.close();
        req.disconnect();

        return str.toString();
    }
}
