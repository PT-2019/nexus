package exception;

public class RequestException extends Exception{

    public RequestException(){
        super();
    }

    public RequestException(Integer code, String msg){
        super(code.toString() + " : " + msg);
    }
}
