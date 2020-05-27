package nexus.exception;

/**
 * Nexus API parser related exceptions.
 * @author Pierre R.
 * @since 1.1
 * @version 1.0
 */
public class NexusParserException extends RuntimeException {

    public NexusParserException(){
        super();
    }
    public NexusParserException(String message){ super(message); }
    public NexusParserException(Throwable e){ super("",e); }
    public NexusParserException(String message, Throwable e){ super(message, e); }
    public NexusParserException(Integer code, String msg){ super(code.toString() + " : " + msg); }

}