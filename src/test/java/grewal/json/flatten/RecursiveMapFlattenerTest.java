package grewal.json.flatten;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecursiveMapFlattenerTest {

    private RecursiveMapFlattener flattener;

    private static Stream<Arguments> paramsForMapFlatteningTests() {
        return Stream.of(
                Arguments.of("Empty Map", Map.of(), Map.of()),
                Arguments.of("Single Level Map", Map.of("a", 1, "b", "xyz", "c", 2.3, "d", true),
                        Map.of("a", 1, "b", "xyz", "c", 2.3, "d", true)),
                Arguments.of("Nested Map Test 1", Map.of("a", 1.0, "b", Map.of("x", "some value", "y", false), "c", "10000"),
                        Map.of("a", 1.0, "b.x", "some value", "b.y", false, "c", "10000")),
                /**
                 * TODO : clarify how to handle this use case
                 */
                Arguments.of("Nested Map With Nested Empty Map", Map.of("a", 1, "b", Map.of("c", true, "d", Map.of())),
                        Map.of("a", 1, "b.c", true))
        );
    }

    @BeforeEach
    public void setup() {
        this.flattener = new RecursiveMapFlattener();
    }

    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("paramsForMapFlatteningTests")
    void testMapFlattening(String testName, Map<String, Object> input, Map<String, Object> expected) throws JsonProcessingException {
        Map<String, Object> actual = this.flattener.flatten(input);
        assertTrue(TestUtility.compareMaps(expected, actual));
    }
}
