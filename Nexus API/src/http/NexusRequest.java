package http;

import exception.RequestException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Class to perform http request (get & post)
 *
 * @Author Pierre R.
 * @version 1.0
 * @since 1.0
 */

public class NexusRequest {


    HttpURLConnection req = null;

    /**
     * Prepare a get request on the target
     * @param target url of the target
     * @throws IOException if there is an error during the connection
     * @throws RequestException if the response code is an error
     */
    public void getRequest(String target)throws IOException, RequestException{

        URL url = new URL(target);
        this.req = (HttpURLConnection) url.openConnection();
        req.setRequestMethod("GET");
        req.setRequestProperty("content-type", "/application/json");
        if(this.req.getResponseCode() != 200)throw new RequestException(this.req.getResponseCode(), this.req.getResponseMessage());
    }

    /**
     * Prepare a post request
     * @param target url of the target
     * @throws IOException if there is an error during the connection
     * @throws RequestException if the response code is an error
     */

    public void postRequest(String target)throws IOException, RequestException{

        URL url = new URL(target);
        this.req = (HttpURLConnection) url.openConnection();
        req.setRequestMethod("POST");
        req.setDoOutput(true);
        if(this.req.getResponseCode() != 200)throw new RequestException(this.req.getResponseCode(), this.req.getResponseMessage());
    }


    /**
     * Post your request
     * @throws IOException if there is an error with the dataoutputstream
     */
    public void sendPostRequest() throws IOException{
        DataOutputStream out = new DataOutputStream(this.req.getOutputStream());
        out.close();
    }

    /**
     * Post your request with parameter
     * @param parameter form : "?paramater1=value&.."
     * @throws IOException if there is an error with the DataOutputStream
     */
    public void sendPostRequest(String parameter) throws IOException{

        DataOutputStream out = new DataOutputStream(this.req.getOutputStream());
        out.writeBytes(parameter);
        System.out.print(out);
        out.close();
    }

    /**
     * Get the response of the api after a request
     * @return the response of the api
     * @throws IOException if there is an error with the InputStream
     */
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
