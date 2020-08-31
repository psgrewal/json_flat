package grewal.json.flatten;

import java.util.Map;

/**
 * Interface for flattening any nested map to a single level map
 * @author Parminder Grewal
 */
public interface MapFlattener {

    Map<String, Object> flatten(Map<String, Object> input);
}
