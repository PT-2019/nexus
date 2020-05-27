package nexus.exception;

/**
 * Markdown related exceptions
 *
 * @since 1.1
 * @version 1.0
 * @author Quentin Ra
 */
public class MarkdownException extends RuntimeException {

    public static final String PARSE_ERROR = "Parse error (at line: %s): %s";

    public MarkdownException() { super(); }
    public MarkdownException(String message) { super(message); }
    public MarkdownException(String message, Throwable cause) { super(message, cause); }
    public MarkdownException(Throwable cause) { super(cause); }

    // ------------------------------ CUSTOM ----------------------------- \\

    public MarkdownException(int line, String message) {
        super(PARSE_ERROR.replaceFirst("%s", ""+line).replaceFirst("%s", message));
    }

}
