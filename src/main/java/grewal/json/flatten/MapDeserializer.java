package grewal.json.flatten;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

/**
 * Interface to convert JSON string -> Map
 * @author Parminder Grewal
 */
public interface MapDeserializer {

    Map<String, Object> fromJson(String inputJson) throws JsonProcessingException;
}
