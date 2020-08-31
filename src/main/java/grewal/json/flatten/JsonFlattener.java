package grewal.json.flatten;

/**
 * Interface for flattening JSON string so that there is no nested fields in the json
 * @author Parminder Grewal
 */
public interface JsonFlattener {

    /**
     * Input json string. json should not contain arrays as this is not supported
     * @param inputJson
     * @return
     * @throws JsonFlattenerException
     */
    String flattenJsonString(String inputJson) throws JsonFlattenerException;
}
