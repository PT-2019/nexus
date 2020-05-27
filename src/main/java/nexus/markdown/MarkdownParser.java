package nexus.markdown;

import nexus.exception.MarkdownException;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Parse a Nexus markdown file to it's model representation.
 *
 * @since 1.2
 * @version 1.0
 * @author Quentin Ra
 */
public class MarkdownParser {

    public void parse(String base){
        MarkdownFile file = new MarkdownFile();
        String[] content = base.split("\n");

        for (int i = 0; i < content.length; i++) {
            String s = content[i];
            if(s.trim().startsWith("#")){//title
                this.extractTitle(file, i, s);
            } else if (s.trim().equals("---")){
                file.add(MarkdownElement.HR, "---");
            } else if (s.startsWith("* ")){
                file.add(MarkdownElement.UL, s.replace("* ","").trim());
                for (i++; i < content.length ; i++) {
                    s = content[i];
                    if(isBreakLine(s, i, content)) break;
                    if(!s.startsWith("    *")){ i--; break; }
                    int r = parseMultiLinesList(file, s, i, content);
                    if(i != r) i = r - 1;;
                }
            } else if(s.startsWith("|")){
                this.extractTable(file, s, i, content);
            } else if(!isBreakLine(s, i, content)) {//this is a paragraph, but it may contains * or _ or ~ or |
                int r = parseMultiLinesP(file, s, i, content);
                if(i != r) i = r - 1;
            }
        }

        System.out.println(file);
    }

    // ------------------------------ PARSE ----------------------------- \\

    /** Pack again a couple of lines into one. */
    private int parseMultiLinesP(MarkdownFile file, String line, int i, String[] content) {
        StringBuilder buf = new StringBuilder();
        ArrayList<Object> e = new ArrayList<>();
        i++;

        //check bold
        if(line.contains("**")){
            extractBold(line, buf, e);
            buf = new StringBuilder();
        } else buf.append(line);

        if(i < content.length) line = content[i];
        for (; i < content.length && !isBreakLine(line, i, content); i++){
            line = content[i];
            if(line.contains("**")){
                extractBold(line, buf, e);
                buf = new StringBuilder();
                continue;
            }
            buf.append(" ");
            buf.append(line);
        }
        if(e.isEmpty()) file.add(MarkdownElement.P, buf.toString());
        else {
            if(!buf.toString().isEmpty()) e.add(buf.toString());
            file.add(new MarkdownSection(MarkdownElement.P, e));
        }
        return i;
    }

    /** Pack again a couple of lines into one. */
    private int parseMultiLinesList(MarkdownFile file, String line, int i, String[] content) {
        StringBuilder buf = new StringBuilder(line);
        ArrayList<Object> tables = new ArrayList<>();
        i++;
        if(i < content.length) line = content[i];
        for (; i < content.length && !isBreakLine(line, i, content)
                && !line.trim().startsWith("* "); i++){
            line = content[i];
            if(line.trim().startsWith("|")){
                int r = extractTable(file, line, i, content, tables);
                if(i != r) i = r - 1;
                continue;
            }
            buf.append(" ");
            buf.append(line.trim());
        }

        file.add(MarkdownElement.LI, buf.toString().trim().replaceFirst("[*] ",""));

        for (Object table :tables) {
            file.add(MarkdownElement.TABLE, table);
        }

        return i;
    }

    // ------------------------------ CONVENIENCE ----------------------------- \\

    /** Returns if a line is a breaking/empty line meaning an end tag of a p, ul, etc.... */
    private boolean isBreakLine(String line, int i, String[] lines) {
        //another tag
        if(line.trim().equals("---") || line.trim().startsWith("#"))
            return false;

        if(line.trim().isEmpty()){
            //check if there are an array in the next line
            if(i+1 < lines.length){
                String next = lines[i+1];
                return !next.trim().startsWith("|");
            }
            return true;
        }

        return false;
    }

    // ------------------------------ EXTRACT ----------------------------- \\

    private void extractBold(String line, StringBuilder buf, ArrayList<Object> content){
        int bIndex1 = line.indexOf("**")+2;
        int bIndex2 = line.lastIndexOf("**");
        String subP = line.substring(bIndex1, bIndex2);

        String[] parts = line.split("[*]{2}" + subP + "[*]{2}");
        content.add(buf.toString()+" "+parts[0]);
        content.add(new MarkdownSection(MarkdownElement.B, subP));
        content.add(parts[1]);
    }

    private int extractTable(MarkdownFile file, String line, int i, String[] content){
       return extractTable(file, line, i, content, null);
    }

    private int extractTable(MarkdownFile file, String line, int i, String[] content,
                             ArrayList<Object> o) {

        ArrayList<String> cols = new ArrayList<>();
        ArrayList<ArrayList<Object>> values = new ArrayList<>();
        //titles
        for (String th:line.split("[|]")) {
            if(th.trim().isEmpty()) continue;
            cols.add(th.trim());
            values.add(new ArrayList<>());
        }
        //content
        for (i++; i < content.length ; i++) {
            line = content[i];
            if(!line.trim().startsWith("|")) break;
            if(line.trim().startsWith("|-")) continue;

            String[] rawValues = line.split("[|]");
            int j = 0;
            for (ArrayList<Object> a:values) {
                while(rawValues[j].trim().isEmpty()) j++;
                a.add(rawValues[j].trim());
                j++;
            }
        }

        DefaultTableModel table = new DefaultTableModel();
        int c = 0;
        for (String col :cols) {
            table.addColumn(col, values.get(c).toArray());
        }

        if(o == null) file.add(MarkdownElement.TABLE, table);
        else o.add(table);

        return i;
    }

    /**
     * Extract titles so lines such as or throws exception
     * # title
     * ## title
     * ### title
     * #### title
     * ##### title
     * ###### title
     */
    private void extractTitle(MarkdownFile file, int line, String titleRaw){
        int i;
        MarkdownElement h;
        String title;
        for (i = 0; i < titleRaw.length(); i++) {
            char c = titleRaw.charAt(i);
            if(c != '#') break;
        }

        switch (i+1){
            case 1: h = MarkdownElement.H1; break;
            case 2: h = MarkdownElement.H2; break;
            case 3: h = MarkdownElement.H3; break;
            case 4: h = MarkdownElement.H4; break;
            case 5: h = MarkdownElement.H5; break;
            case 6: h = MarkdownElement.H6; break;
            default: throw new MarkdownException(line, "Invalid title tag.");
        }

        title = titleRaw.substring(i).trim();
        if(title.isEmpty())
            throw new MarkdownException(line, "Empty Title.");

        file.add(h, title);
    }

    // ------------------------------ TEST ----------------------------- \\

    public static void main(String[] args) {
        //Read file from stream
        StringBuilder sb = new StringBuilder();
        try {
            InputStream stream = MarkdownParser.class.getResourceAsStream("/test.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            String line;
            while((line = reader.readLine()) != null){
                sb.append(line);
            }
            reader.close();
        } catch (Exception ignore){}

        //process test
        JSONObject file = new JSONObject(sb.toString());
        JSONArray rows = file.getJSONArray("result");
        JSONObject row = (JSONObject) rows.get(0);
        //parse content
        new MarkdownParser().parse(row.getString("content"));
    }

}
