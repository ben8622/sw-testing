package bens.graphtests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProjectTwoTests {
    public final ObjectMapper mapper = new ObjectMapper();
    public final JsonDelimitedParser jsonDelimitedParser = new JsonDelimitedParser();

    @Before
    public void setup(){
    }

    @Test
    public void baseReadTree() throws JsonProcessingException {
        String json = "";

        DeserializationConfig config = new DeserializationConfig(null, null, null, null, null, null);
        mapper.setConfig(config);
        mapper.readTree(json);
    }

    @Test(expected = JsonProcessingException.class)
    public void readTree1_1() throws JsonProcessingException {
        String json = "{ ..r234 }";

        mapper.readTree(json);
    }

    @Test
    public void readTree2_2() throws JsonProcessingException {
        String json = "";

        Object res = mapper.readTree(json);
        Assert.assertEquals(res.getClass(), MissingNode.class);
    }

    @Test(expected = Exception.class)
    public void readTree3_3() throws JsonProcessingException {
        String json = "{\"a\":null}";

        DeserializationConfig config = new DeserializationConfig(null, null, null, null, null, null);
        mapper.setConfig(config);

        Object res = mapper.readTree(json);
        Assert.assertNotNull(res);
    }

    @Test
    public void readTree4_4() throws JsonProcessingException {
        String json = "{\"a\":null}";

        Object res = mapper.readTree(json);
        Assert.assertNotNull(res);
    }

    @Test(expected = Exception.class)
    public void readTree5_5() throws JsonProcessingException {
        String json = "{\"a\":1}";

        DeserializationConfig config = new DeserializationConfig(null, null, null, null, null, null);
        mapper.setConfig(config);
        mapper.readTree(json);
    }

    @Test
    public void readTree6_6() throws JsonProcessingException {
        String json = "{\"a\":1}";

        Object res = mapper.readTree(json);
        Assert.assertNotNull(res);
    }

    @Test
    public void readTree7_7() throws JsonProcessingException {
        String json = "{\"a\":1}}";

        Object res = mapper.readTree(json);
        Assert.assertNotNull(res);
    }

    @Test(expected = Exception.class)
    public void readTree8_8() throws JsonProcessingException {
        String json = "{\"a\":null, null}";

        DeserializationConfig config = new DeserializationConfig(null, null, null, null, null, null);
        mapper.setConfig(config);
        mapper.readTree(json);
    }

    @Test(expected = Exception.class)
    public void readTree9_9() throws JsonProcessingException {
        String json = "{\"a\":null, null}";

        Object res = mapper.readTree(json);
        Assert.assertNotNull(res);
    }

    @Test(expected = Exception.class)
    public void readTree10_10() throws JsonProcessingException {
        String json = "{\"a\":null, null}";

        DeserializationConfig config = new DeserializationConfig(null, null, null, null, null, null);
        mapper.setConfig(config);
        mapper.readTree(json);
    }

    @Test(expected = Exception.class)
    public void readTree11_11() throws JsonProcessingException {
        String json = "{\"a\":null, null}";

        mapper.readTree(json);
    }

    @Test(expected = Exception.class)
    public void readTree12_12() throws JsonProcessingException {
        String json = "{null}}";

        mapper.readTree(json);
    }

    @Test
    public void baseWriteValueAsString() throws JsonProcessingException {
        Object obj = new Object();

        DeserializationConfig config = new DeserializationConfig(null, null, null, null, null, null);
        mapper.setConfig(config);
        mapper.writeValueAsString(obj);
    }

    @Test(expected = Exception.class)
    public void writeValueAsString13_1() throws JsonProcessingException {
        TestClass1_1 a = new TestClass1_1();
        TestClass1_1 b = new TestClass1_1();
        a.a = b;
        b.a = a;

        String json = mapper.writeValueAsString(a);
        Assert.assertNotNull(json);
    }

    @Test
    public void writeValueAsString14_2() throws JsonProcessingException {
        ArrayList arr = new ArrayList();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        DeserializationConfig config = new DeserializationConfig(null, null, null, null, null, null);
        mapper.setConfig(config);
        String json = mapper.writeValueAsString(arr);
        Assert.assertNotNull(json);
    }

    @Test(expected = Exception.class)
    public void writeValueAsString15_3() throws JsonProcessingException {
        TestClass1_1 a = new TestClass1_1();
        TestClass1_1 b = new TestClass1_1();
        a.a = b;
        b.a = a;

        String json = mapper.writeValueAsString(b);
        Assert.assertNotNull(json);
    }

    @Test
    public void writeValueAsString16_4() throws JsonProcessingException {
        String json = mapper.writeValueAsString(null);
        Assert.assertNotNull(json);
    }

    @Test
    public void writeValueAsString17_5() throws JsonProcessingException {
        TestClass1_2 obj = new TestClass1_2();
        obj.setB(3);
        String json = mapper.writeValueAsString(obj);
        Assert.assertNotNull(json);
    }

    @Test
    public void writeValueAsString18_6() throws JsonProcessingException {
        TestClass1_1 obj = new TestClass1_1();
        DeserializationConfig config = new DeserializationConfig(null, null, null, null, null, null);
        mapper.setConfig(config);
        String json = mapper.writeValueAsString(obj);
        Assert.assertNotNull(json);
    }

    @Test
    public void writeValueAsString19_7() throws JsonProcessingException {
        TestClass1_1 obj = new TestClass1_1();
        String json = mapper.writeValueAsString(obj);
        Assert.assertNotNull(json);
    }

    @Test
    public void writeValueAsString20_8() throws JsonProcessingException {
        TestClass1_1 obj = new TestClass1_1();
        obj.a = new TestClass1_1();
        String json = mapper.writeValueAsString(obj);
        Assert.assertNotNull(json);
    }

    @Test
    public void baseParseDelimitedJson() throws JsonProcessingException {
        String json = "{}/{}/{}";
        String delim = "/";

        jsonDelimitedParser.parseDelimitedJson(json, delim);
    }

    @Test
    public void parseDelimitedJson21_1() throws JsonProcessingException {
        String json = "";
        String delim = "/";

        List<JsonNode> res =  jsonDelimitedParser.parseDelimitedJson(json, delim);
        Assert.assertNotNull(res);
    }

    @Test
    public void parseDelimitedJson22_2() throws JsonProcessingException {
        String json = " ";
        String delim = "/";

        List<JsonNode> res =  jsonDelimitedParser.parseDelimitedJson(json, delim);
        Assert.assertNotNull(res);
    }

    @Test
    public void parseDelimitedJson23_3() throws JsonProcessingException {
        String json = "{\"a\": 1}/{\"b\": 2}";
        String delim = "/";

        List<JsonNode> res =  jsonDelimitedParser.parseDelimitedJson(json, delim);
        Assert.assertNotNull(res);
    }

    @Test
    public void parseDelimitedJson24_4() throws JsonProcessingException {
        String json = "{\"a\": 1}//";
        String delim = "/";

        List<JsonNode> res =  jsonDelimitedParser.parseDelimitedJson(json, delim);
        Assert.assertNotNull(res);
    }
}
