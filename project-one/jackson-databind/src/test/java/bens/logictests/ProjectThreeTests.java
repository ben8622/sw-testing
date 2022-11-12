package bens.logictests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ProjectThreeTests {
    public final ObjectMapper mapper = new ObjectMapper();
    public final JsonDelimitedParser parser = new JsonDelimitedParser();
    public final JsonCombiner combiner = new JsonCombiner();

    @Test
    public void parseDelimitedJsonBase() throws JsonProcessingException {
        String input = "";
        String delimiter = "";

        List<JsonNode> result = parser.parseDelimitedJson(input, delimiter);

        Assert.assertNotNull(result);
    }

    @Test
    public void parseDelimitedJson1_1() throws JsonProcessingException {
        String input = null;
        String delimiter = ",";

        List<JsonNode> result = parser.parseDelimitedJson(input, delimiter);

        Assert.assertNotNull(result);
    }

    @Test
    public void parseDelimitedJson2_2() throws JsonProcessingException {
        String input = "";
        String delimiter = "/";

        List<JsonNode> result = parser.parseDelimitedJson(input, delimiter);

        Assert.assertNotNull(result);
    }

    @Test
    public void parseDelimitedJson3_3() throws JsonProcessingException {
        // predicate be T, json == null be F
        String input = "";
        String delimiter = "/";

        List<JsonNode> result = parser.parseDelimitedJson(input, delimiter);

        Assert.assertNotNull(result);
    }

    @Test
    public void parseDelimitedJson4_4() throws JsonProcessingException {
        String input = null;
        String delimiter = "/";

        List<JsonNode> result = parser.parseDelimitedJson(input, delimiter);

        Assert.assertNotNull(result);
    }

    @Test
    public void parseDelimitedJson5_5() throws JsonProcessingException {
        String input = "{\"a\": 1}/{\"b\": 2}";
        String delimiter = "/";

        List<JsonNode> result = parser.parseDelimitedJson(input, delimiter);

        Assert.assertNotNull(result);
    }

    @Test
    public void parseDelimitedJson6_6() throws JsonProcessingException {
        String input = "{\"a\": 1}/null";
        String delimiter = "/";

        List<JsonNode> result = parser.parseDelimitedJson(input, delimiter);

        Assert.assertNotNull(result);
    }

    @Test
    public void parseDelimitedJson7_7() throws JsonProcessingException {
        String input = "{\"a\": 1}//";
        String delimiter = "/";

        List<JsonNode> result = parser.parseDelimitedJson(input, delimiter);

        Assert.assertNotNull(result);
    }

    @Test
    public void parseDelimitedJson8_8() throws JsonProcessingException {
        String input = "{\"a\": 1}//";
        String delimiter = "/";

        List<JsonNode> result = parser.parseDelimitedJson(input, delimiter);

        Assert.assertNotNull(result);
    }

    @Test
    public void parseDelimitedJson9_9() throws JsonProcessingException {
        String input = "{\"a\": 1}/null";
        String delimiter = "/";

        List<JsonNode> result = parser.parseDelimitedJson(input, delimiter);

        Assert.assertNotNull(result);
    }

    @Test
    public void combineJsonBase() throws Exception {
        String s1 = "{\"a\": 1}";
        String s2 = "{\"b\": 1}";

        String res = combiner.combineJson(s1, s2);

        Assert.assertNotNull(res);
    }

    @Test
    public void combineJsonBase10_1() throws Exception {
        String s1 = "{\"a\": 1}";
        String s2 = "{\"b\": 1";

        String res = combiner.combineJson(s1, s2);

        Assert.assertNotNull(res);
    }

    @Test(expected = Exception.class)
    public void combineJsonBase11_2() throws Exception {
        String s1 = "{\"a\": 1";
        String s2 = "{\"b\": 1";

        String res = combiner.combineJson(s1, s2);

        Assert.assertNotNull(res);
    }

    @Test(expected = Exception.class)
    public void combineJsonBase12_4() throws Exception {
        String s1 = "{\"a\": ";
        String s2 = "{\": 1}";

        String res = combiner.combineJson(s1, s2);

        Assert.assertNotNull(res);
    }

    @Test
    public void combineJsonBase13_5() throws Exception {
        String s1 = "\"x\": tr";
        String s2 = "{\"asdf\": 1}";

        String res = combiner.combineJson(s1, s2);

        Assert.assertNotNull(res);
    }

    @Test
    public void combineJsonBase14_7() throws Exception {
        String s1 = "{\"x\": true}";
        String s2 = "{\"asdf\": 1}";

        String res = combiner.combineJson(s1, s2);

        Assert.assertNotNull(res);
    }

    @Test(expected = Exception.class)
    public void combineJsonBase15_9() throws Exception {
        String s1 = "{\"a";
        String s2 = "{";

        String res = combiner.combineJson(s1, s2);

        Assert.assertNotNull(res);
    }

    @Test(expected = Exception.class)
    public void combineJsonBase16_11() throws Exception {
        String s1 = "{\"a 1}";
        String s2 = ": 1}";

        String res = combiner.combineJson(s1, s2);

        Assert.assertNotNull(res);
    }

    @Test
    public void combineJsonBase17_13() throws Exception {
        String s1 = "{\"finger\": true}";
        String s2 = "{\"toe\": false}";

        String res = combiner.combineJson(s1, s2);

        Assert.assertNotNull(res);
    }

    @Test(expected = Exception.class)
    public void combineJsonBase18_15() throws Exception {
        String s1 = "{\"a\": 1}";
        String s2 = "{\"a\": 1}";

        String res = combiner.combineJson(s1, s2);

        Assert.assertNotNull(res);
    }

    @Test
    public void combineJsonBase20_15() throws Exception {
        String s1 = "{\"a\": 1}";
        String s2 = "{\"b\": 1}";

        String res = combiner.combineJson(s1, s2);

        Assert.assertNotNull(res);
    }

}
