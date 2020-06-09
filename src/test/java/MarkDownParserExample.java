import nexus.markdown.MarkdownFile;
import nexus.markdown.MarkdownParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * An usage of markdown class
 *
 * @since 1.2
 * @version 1.0
 * @author Quentin Ra
 */
public class MarkDownParserExample {

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
        MarkdownFile content = MarkdownParser.parse(row.getString("content"));
        System.out.println(content);
    }

}
