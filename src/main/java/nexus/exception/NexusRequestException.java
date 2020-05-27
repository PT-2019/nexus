package nexus.exception;

/**
 * Nexus API related exceptions.
 * @author Pierre R.
 * @since 1.0
 * @version 1.1
 */
public class NexusRequestException extends RuntimeException {

    public NexusRequestException(){
        super();
    }
    public NexusRequestException(String message){ super(message); }
    public NexusRequestException(Throwable e){ super("",e); }
    public NexusRequestException(String message, Throwable e){ super(message, e); }
    public NexusRequestException(Integer code, String msg){ super(code.toString() + " : " + msg); }
    
}
