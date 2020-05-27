package nexus.exception;

/**
 * No network internet connection available
 *
 * @since 2.0
 * @version 1.0
 * @author Quentin Ra
 */
public class NexusNoNetwork extends RuntimeException {

    public static final String OPEN_CONNEXION_FAILED = "Unable to open a connexion.";

    public NexusNoNetwork() { super(); }
    public NexusNoNetwork(String message) { super(message); }
    public NexusNoNetwork(String message, Throwable cause) { super(message, cause); }
    public NexusNoNetwork(Throwable cause) { super(cause); }

}
