package grewal.json.flatten;

public class JsonFlattenerTestCases {

    protected static String NON_NESTED_JSON = "{\"a\" : 1, \"b\" : true}";
    protected static String NON_NESTED_JSON_EXPECTED = "{\"a\":1,\"b\":true}";

    protected static String NON_NESTED_MULTILINE_JSON = "{\"a\" : 1,\n \"b\" : true}";
    protected static String NON_NESTED_MULTILINE_JSON_EXPECTED = "{\"a\":1,\"b\":true}";

    protected static String NESTED_JSON = "{\"a\": true, \"b\": {\"c\": 5.0, \"d\": 8, \"e\" : {\"x\" : \"abcde\"}}}";
    protected static String NESTED_JSON_EXPECTED = "{\"a\":true,\"b.c\":5.0,\"b.d\":8,\"b.e.x\":\"abcde\"}";

    protected static String EMPTY_JSON = "{}";
    protected static String EMPTY_JSON_EXPECTED = "{}";

    protected static String TYPE_RETENTION_FOR_JSON = "{\"a\":\"true\", \"b\": true, \"c\":\"1\", \"d\":-1, \"e\":\"-1.0\"}";
    protected static String TYPE_RETENTION_FOR_JSON_EXPECTED = "{\"a\":\"true\",\"b\":true,\"c\":\"1\",\"d\":-1,\"e\":\"-1.0\"}";

    protected static String BAD_JSON_1 = "{";
    protected static String BAD_JSON_2 = "";
    protected static String BAD_JSON_3 = "{a:1}";

}
