package nexus.http;

import nexus.exception.NexusNoNetwork;
import nexus.exception.NexusRequestException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Class to perform http request (get & post)
 *
 * @author Pierre R.
 * @version 1.1
 * @since 1.0
 */

public class NexusRequest {

    /** save request **/
    private HttpURLConnection req = null;

    public NexusRequest() {}

    // ------------------------------ RESPONSE ----------------------------- \\

    /**
     * Get the response of the api after a request
     * @return the response of the api
     * @throws NexusRequestException if there is an error with the InputStream
     */
    public String readResponse() throws NexusRequestException {
        try {
            //preparation
            BufferedReader buff = new BufferedReader(new InputStreamReader(this.req.getInputStream()));
            String reader;
            StringBuilder str = new StringBuilder();

            //read
            while((reader = buff.readLine()) != null) str.append(reader);

            //close
            buff.close();
            this.req.disconnect();

            return str.toString();
        } catch (IOException e){
            throw new NexusRequestException("Error reading the response.", e);
        }
    }

    // ------------------------------ GET ----------------------------- \\

    /**
     * Prepare a get request on the target
     * @param target url of the target
     * @throws nexus.exception.NexusNoNetwork if there is an error during the connection
     * @throws nexus.exception.NexusRequestException if the response code is an error
     */
    public void prepareGetRequest(String target)throws NexusRequestException {
        try {
            URL url = new URL(target);
            try {
                this.req = (HttpURLConnection) url.openConnection();
            } catch (IOException noNetWork){
                throw new NexusNoNetwork(NexusNoNetwork.OPEN_CONNEXION_FAILED, noNetWork);
            }
            this.req.setRequestMethod("GET");
            this.req.setRequestProperty("content-type", "/application/json");
            if(this.req.getResponseCode() != 200)
                throw new NexusRequestException(this.req.getResponseCode(), this.req.getResponseMessage());
        } catch (IOException e){
            throw new NexusRequestException("Error preparing the request", e);
        }
    }

    // ------------------------------ POST ----------------------------- \\

    /**
     * Prepare a post request
     * @param target url of the target
     * @throws NexusNoNetwork if there is an error during the connection
     * @throws NexusRequestException if the response code is an error
     */
    public void postRequest(String target)throws NexusRequestException {

        try {
            URL url = new URL(target);
            try {
                this.req = (HttpURLConnection) url.openConnection();
            } catch (IOException noNetWork){
                throw new NexusNoNetwork(NexusNoNetwork.OPEN_CONNEXION_FAILED, noNetWork);
            }
            this.req.setRequestMethod("POST");
            this.req.setDoOutput(true);
            if(this.req.getResponseCode() != 200)
                throw new NexusRequestException(this.req.getResponseCode(), this.req.getResponseMessage());
        } catch (IOException e){
            throw new NexusRequestException("Error preparing the request", e);
        }
    }


    /**
     * Post your request
     * @throws NexusRequestException if there is an error with the dataoutputstream
     */
    public void sendPostRequest() throws NexusRequestException {
        try {
            DataOutputStream out = new DataOutputStream(this.req.getOutputStream());
            out.close();
        } catch (IOException e){
            throw new NexusRequestException(e);
        }
    }

    /**
     * Post your request with parameter
     * @param parameter form : "?paramater1=value&.."
     * @throws NexusRequestException if there is an error with the DataOutputStream
     */
    public void sendPostRequest(String parameter) throws NexusRequestException {
        try {
            DataOutputStream out = new DataOutputStream(this.req.getOutputStream());
            out.writeBytes(parameter);
            System.out.print(out);
            out.close();
        } catch (IOException e) {
            throw new NexusRequestException(e);
        }
    }
}
