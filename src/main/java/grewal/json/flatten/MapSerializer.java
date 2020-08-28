package grewal.json.flatten;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public interface MapSerializer {

    String toJson(Map<String, Object> fieldMap) throws JsonProcessingException;
}
