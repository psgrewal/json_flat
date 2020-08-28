package grewal.json.flatten;

import java.util.Map;

public interface MapFlattener {

    Map<String, Object> flatten(Map<String, Object> input);
}
