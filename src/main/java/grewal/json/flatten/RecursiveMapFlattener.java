package grewal.json.flatten;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Recursively flattens a java Map<String, Object>
 *
 * @author Parminder Grewal
 */
public class RecursiveMapFlattener implements MapFlattener {

    @Override
    public Map<String, Object> flatten(Map<String, Object> input) {
        Map<String, Object> outputMap = new LinkedHashMap<>();
        recursivelyTraverse(null, input, outputMap);
        return outputMap;
    }

    private void recursivelyTraverse(String prefix, Map<String, Object> currentMap, Map<String, Object> outputMap) {
        for (Map.Entry<String, Object> entry : currentMap.entrySet()) {
            String key = prefix == null ? entry.getKey() : prefix + "." + entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                recursivelyTraverse(key, (Map<String, Object>) value, outputMap);
            } else {
                outputMap.put(key, value);
            }
        }
    }
}
