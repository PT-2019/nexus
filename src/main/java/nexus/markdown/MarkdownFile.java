package nexus.markdown;

import java.util.ArrayList;
import java.util.Map;

/**
 * A markdown file.
 *
 * @since 1.2
 * @version 1.0
 * @author Quentin Ra
 */
public class MarkdownFile {

    private final ArrayList<MarkdownSection> fileContent;

    public MarkdownFile(){
        this.fileContent = new ArrayList<>();
    }

    public void add(MarkdownElement e, Object data) {
        this.fileContent.add(new MarkdownSection(e, data));
    }

    public void add(MarkdownSection section) {
        this.fileContent.add(section);
    }

    public ArrayList<MarkdownSection> getContent(){
        return new ArrayList<>(this.fileContent);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MarkdownFile{\n" + "\tfileContent = [\n");

        for (MarkdownSection a: this.fileContent) {
            sb.append("\t\t");
            sb.append(a.toString(2));
            sb.append(",\n");
        }

        sb.append("\t]\n");
        sb.append('}');
        return sb.toString();
    }
}
