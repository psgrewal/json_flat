package grewal.json.flatten;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static grewal.json.flatten.JsonFlattenerTestCases.*;

class JsonFlattenerImplTest {

    private JsonMapConverter converter;

    private MapFlattener mapFlattener;

    private JsonFlattenerImpl jsonFlattener;

    private static Stream<Arguments> paramsForFlattenerTests() {
        return Stream.of(
                Arguments.of("Non-nested json", NON_NESTED_JSON, NON_NESTED_JSON_EXPECTED),
                Arguments.of("Non-nested multi-line json", NON_NESTED_MULTILINE_JSON, NON_NESTED_MULTILINE_JSON_EXPECTED),
                Arguments.of("Nested json", NESTED_JSON, NESTED_JSON_EXPECTED),
                Arguments.of("Empty json", EMPTY_JSON, EMPTY_JSON_EXPECTED),
                Arguments.of("Type Retention For json", TYPE_RETENTION_FOR_JSON, TYPE_RETENTION_FOR_JSON_EXPECTED)
        );
    }

    private static Stream<Arguments> paramsForExceptionTests() {
        return Stream.of(
                Arguments.of("Bad Json 1", BAD_JSON_1),
                Arguments.of("Bad Json 2", BAD_JSON_2),
                Arguments.of("Bad Json 3", BAD_JSON_3)
        );
    }

    @BeforeEach
    void setUp() {
        this.converter = Mockito.spy(new JsonMapConverter());
        this.mapFlattener = Mockito.spy(new RecursiveMapFlattener());
        this.jsonFlattener = new JsonFlattenerImpl(converter, mapFlattener);
    }

    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("paramsForFlattenerTests")
    void flattenJsonString(String name, String inputJson, String expected) throws JsonFlattenerException, JsonProcessingException {
        String actual = this.jsonFlattener.flattenJsonString(inputJson);
        Assertions.assertEquals(expected, actual.replaceAll("\\s+", ""));
        Mockito.verify(this.converter, Mockito.times(1)).fromJson(inputJson);
        Mockito.verify(this.mapFlattener, Mockito.times(1)).flatten(Mockito.anyMap());
        Mockito.verify(this.converter, Mockito.times(1)).toJson(Mockito.anyMap());
    }

    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("paramsForExceptionTests")
    void exceptionThrownWhenBadJson(String name, String inputJson) throws JsonProcessingException {
        Assertions.assertThrows(JsonFlattenerException.class, () -> this.jsonFlattener.flattenJsonString(inputJson));
        Mockito.verify(this.converter, Mockito.times(1)).fromJson(inputJson);
        Mockito.verify(this.mapFlattener, Mockito.times(0)).flatten(Mockito.anyMap());
        Mockito.verify(this.converter, Mockito.times(0)).toJson(Mockito.anyMap());

    }
}