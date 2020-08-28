package grewal.json.flatten;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Uses jackson library to serialize and deserialize arbitrary json to/from
 * a java.util.Map of String -> Object
 *
 * @author Parminder Grewal
 */
public class JsonMapConverter implements MapSerializer, MapDeserializer {

    private final ObjectMapper objectMapper;

    public JsonMapConverter() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);
    }

    @Override
    public Map<String, Object> fromJson(String inputJson) throws JsonProcessingException {
        return objectMapper.readValue(inputJson, new TypeReference<HashMap>() {
        });
    }

    @Override
    public String toJson(Map<String, Object> fieldMap) throws JsonProcessingException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(fieldMap);
    }
}
