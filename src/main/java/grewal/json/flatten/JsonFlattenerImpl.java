package grewal.json.flatten;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public class JsonFlattenerImpl implements JsonFlattener {

    private final JsonMapConverter converter;

    private final MapFlattener mapFlattener;

    public JsonFlattenerImpl(JsonMapConverter converter, MapFlattener mapFlattener) {
        this.converter = converter;
        this.mapFlattener = mapFlattener;
    }

    public static JsonFlattener getInstance() {
        return new JsonFlattenerImpl(new JsonMapConverter(), new RecursiveMapFlattener());
    }

    @Override
    public String flattenJsonString(String inputJson) throws JsonFlattenerException {
        try {
            Map<String, Object> jsonDataMap = converter.fromJson(inputJson);
            Map<String, Object> flattenedMap = mapFlattener.flatten(jsonDataMap);
            return converter.toJson(flattenedMap);
        } catch (JsonProcessingException e) {
            throw new JsonFlattenerException(e.getMessage());
        }
    }
}
