package grewal.json.flatten;

public interface JsonFlattener {

    String flattenJsonString(String inputJson) throws JsonFlattenerException;
}
