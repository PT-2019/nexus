package nexus.markdown;

import java.util.ArrayList;

/**
 * A markdown section may be a bunch of lines in the real file.
 * A section ends with an empty line.
 *
 * Content may be a string or another section.
 *
 * @since 1.2
 * @version 1.0
 * @author Quentin Ra
 */
public class MarkdownSection {

    private final MarkdownElement tag;
    private final ArrayList<Object> content;

    public MarkdownSection(MarkdownElement tag, Object content) {
        this.tag = tag;
        this.content = new ArrayList<>();
        this.content.add(content);
    }

    public MarkdownSection(MarkdownElement tag, ArrayList<Object> content) {
        this.tag = tag;
        this.content = new ArrayList<>(content);
    }

    public MarkdownElement getTag() { return tag; }

    public ArrayList<Object> getContent() { return new ArrayList<>(content); }
    public Object getContent(int index) { return new ArrayList<>(content).get(index); }

    @Override
    public String toString() {
        return toString(0);
    }

    public String toString(int tSpace) {
        StringBuilder sb = new StringBuilder("{");

        sb.append(this.getTag());
        sb.append(":");
        if(this.content.size() == 1) sb.append(this.content.get(0));
        else if(this.content.size() == 0) sb.append("<none>");
        else {
            for (Object o: this.content) {
                sb.append(o);
            }
        }


        sb.append("}");
        return sb.toString();
    }
}
