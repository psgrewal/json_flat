package grewal.json.flatten;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

/**
 * Interface to serialize Map<String, Object> -> JSON String
 * @author Parminder Grewal
 */
public interface MapSerializer {

    String toJson(Map<String, Object> fieldMap) throws JsonProcessingException;
}
