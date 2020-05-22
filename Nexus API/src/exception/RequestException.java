package exception;

/**
 * @Author Pierre R.
 * @version 1.0
 * @since 1.0
 */

public class RequestException extends Exception{

    public RequestException(){
        super();
    }

    public RequestException(Integer code, String msg){
        super(code.toString() + " : " + msg);
    }
}
