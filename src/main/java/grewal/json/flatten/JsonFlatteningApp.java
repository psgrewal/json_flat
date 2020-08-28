package grewal.json.flatten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
