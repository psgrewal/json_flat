package grewal.json.flatten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Java Main application, which accepts a json string from the system input and converts it to a flattened json string
 * where nested fields if any are promoted to the base level with field names concatenated with "."
 */
public class JsonFlatteningApp {

    public static void main(String[] args) {
        String fullJson = null;
        try {
            fullJson = getJsonInput();
        } catch (IOException ex) {
            System.err.println("Exception when reading json from system input " + ex.getMessage());
            System.exit(-1);
        }
        JsonFlattener flattener = JsonFlattenerImpl.getInstance();
        try {
            System.out.println(flattener.flattenJsonString(fullJson));
        } catch (JsonFlattenerException ex) {
            System.err.println("Exception encountered when flattening json " + ex.getMessage());
            System.exit(-2);
        }
    }

    private static String getJsonInput() throws IOException {
        InputStreamReader isReader = new InputStreamReader(System.in);
        BufferedReader bufReader = new BufferedReader(isReader);
        String commandLineInput = null;
        StringBuffer jsonBuffer = new StringBuffer();
        while ((commandLineInput = bufReader.readLine()) != null) {
            jsonBuffer.append(commandLineInput);
        }
        return jsonBuffer.toString();
    }
}
