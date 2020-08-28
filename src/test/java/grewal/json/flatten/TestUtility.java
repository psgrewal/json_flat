package grewal.json.flatten;

import java.util.Map;

public class TestUtility {

    public static boolean compareMaps(Map<String, Object> expected, Map<String, Object> actual) {
        if (expected.size() != actual.size()) {
            return false;
        }
        for (Map.Entry<String, Object> entry : expected.entrySet()) {
            String key = entry.getKey();
            Object exp = entry.getValue();
            Object act = actual.get(key);
            if (exp == null && act == null) {
                continue;
            }
            if (act == null) {
                return false;
            }
            if (exp instanceof Map && act instanceof Map) {
                if (!compareMaps((Map) exp, (Map) act)) {
                    return false;
                }
            } else {
                if (!exp.equals(act)) {
                    return false;
                }
            }
        }
        return true;
    }
}
