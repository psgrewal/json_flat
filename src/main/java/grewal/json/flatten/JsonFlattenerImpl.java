package grewal.json.flatten;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

/**
 * @author Parminder Grewal
 */
public class JsonFlattenerImpl implements JsonFlattener {

    private final JsonMapConverter converter;

    private final MapFlattener mapFlattener;

    /**
     * @param converter instance of JsonMapConverter that will be used for serialization & deserialization of JSON String <-> Map
     * @param mapFlattener Instance of map flattener, used to flatten the map from nested maps if any to a single level map
     */
    public JsonFlattenerImpl(JsonMapConverter converter, MapFlattener mapFlattener) {
        this.converter = converter;
        this.mapFlattener = mapFlattener;
    }

    /**
     * Function that returns a new instance of Json Flattener
     * @return
     */
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
