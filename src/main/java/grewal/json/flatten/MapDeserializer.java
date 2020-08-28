package grewal.json.flatten;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public interface MapDeserializer {

    Map<String, Object> fromJson(String inputJson) throws JsonProcessingException;
}
