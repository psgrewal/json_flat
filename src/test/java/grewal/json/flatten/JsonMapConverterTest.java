package grewal.json.flatten;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonMapConverterTest {

    private JsonMapConverter converter;

    private static Stream<Arguments> paramsForJsonDeserialization() {
        Map<String, Object> nullMap = new HashMap<>();
        nullMap.put("a", null);
        return Stream.of(
                Arguments.of("Boolean Test", "{\"a\": true, \"b\": false}", Map.<String, Object>of("a", Boolean.TRUE, "b", Boolean.FALSE)),
                Arguments.of("Integer Boundary Test", "{\"a\": 2147483647, \"b\" : -2147483648 }", Map.<String, Object>of("a", 2_147_483_647, "b", -2_147_483_648)),
                Arguments.of("Long Test", "{\"a\": 2147483648, \"b\" : -2147483649}", Map.<String, Object>of("a", 2_147_483_648L, "b", -2_147_483_649L)),
                Arguments.of("Double Test", "{\"a\": 2147483649.99}", Map.<String, Object>of("a", 2_147_483_649.99D)),
                Arguments.of("Single Depth Multi-Field Test", "{\"a\": 2147483649, \"b\":false, \"c\": \"some value\"}",
                        Map.<String, Object>of("a", 2_147_483_649L, "b", Boolean.FALSE, "c", "some value")),
                Arguments.of("Multi-Depth Multi-Field Test", "{\"a\": 2147483649, \"d\" : { \"b\":false, \"c\": \"some value\", \"d\": \"test\"}}",
                        Map.of("a", 2_147_483_649L, "d", Map.<String, Object>of("b", Boolean.FALSE, "c", "some value", "d", "test"))),
                Arguments.of("Null Test", "{\"a\": null}", nullMap),
                Arguments.of("Fields With Different Cases", "{\"a\": 2147483649, \"A\":false}",
                        Map.<String, Object>of("a", 2_147_483_649L, "A", Boolean.FALSE))
        );
    }

    @BeforeEach
    public void initialize() {
        this.converter = new JsonMapConverter();
    }

    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("paramsForJsonDeserialization")
    void testJsonToMapDeserialization(String testName, String jsonValue, Map<String, Object> expected) throws JsonProcessingException {
        Map<String, Object> actual = this.converter.fromJson(jsonValue);
        assertTrue(TestUtility.compareMaps(expected, actual));
    }


}
