package bens.tests;

import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ObjectMapperTest {

    public final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void writeValuesAsStringBase() throws Exception{

    }
    @Test
    public void writeValueAsString_1_1() throws Exception {
        TestClass_1_1 obj = new TestClass_1_1();
        obj.setA(1);

        String s = mapper.writeValueAsString(obj);

        assertTrue(isValidJSON(s));
    }
    @Test
    public void writeValueAsString_2_2() throws Exception {
        TestClass_2_2 obj = new TestClass_2_2();
        obj.setA(1);
        obj.setB("hello");

        String s = mapper.writeValueAsString(obj);

        assertTrue(isValidJSON(s));
    }
    @Test
    public void writeValueAsString_3_3() throws Exception {
        TestClass_3_3 obj = new TestClass_3_3();
        obj.setA(1);
        obj.setB("hello");

        String s = mapper.writeValueAsString(obj);

        assertTrue(isValidJSON(s));
    }
    @Test
    public void writeValueAsString_4_4() throws Exception {
        TestClass_4_4 obj = new TestClass_4_4();
        obj.setA(new TestClass_1_1());

        String s = mapper.writeValueAsString(obj);

        assertTrue(isValidJSON(s));
    }
    @Test
    public void writeValueAsString_5_5() throws Exception {
        TestClass_5_5 obj = new TestClass_5_5();
        obj.setA(new TestClass_1_1());

        String s = mapper.writeValueAsString(obj);

        assertTrue(isValidJSON(s));
    }
    @Test
    public void writeValueAsString_6_6() throws Exception {
        TestClass_6_6 obj = new TestClass_6_6();
        obj.setA(new TestClass_1_1());
        obj.setB(2);
        obj.setC("hello");

        String s = mapper.writeValueAsString(obj);

        assertTrue(isValidJSON(s));
    }
    @Test
    public void writeValueAsString_7_7() throws Exception {
        TestClass_7_7 obj = new TestClass_7_7();
        obj.setA(new TestClass_1_1());

        String s = mapper.writeValueAsString(obj);

        assertTrue(isValidJSON(s));
    }
    @Test
    public void writeValueAsString_8_8() throws Exception {
        TestClass_8_8 obj = new TestClass_8_8();
        obj.setA(new TestClass_1_1());
        obj.setB(new TestClass_1_1());
        obj.setC(2);

        String s = mapper.writeValueAsString(obj);

        assertTrue(isValidJSON(s));
    }
    @Test
    public void writeValueAsString_9_9() throws Exception {
        TestClass_9_9 obj = new TestClass_9_9();
        obj.setA(new TestClass_1_1());
        obj.setB(new TestClass_1_1());
        obj.setD("hello");

        String s = mapper.writeValueAsString(obj);

        assertTrue(isValidJSON(s));
    }

    @Test
    public void readValueBase() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        String json = "{\"x\":1,\"y\":\"hello world\",\"z\":{\"a\":0,\"b\":-1}}";

        TestClass_1_1 t = mapper.readValue(json, TestClass_1_1.class);
    }
    @Test(expected = MismatchedInputException.class)
    public void readValue_10_1() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        TestClass_1_1 obj = mapper.readValue(json, TestClass_1_1.class);
    }
    @Test(expected = MismatchedInputException.class)
    public void readValue_11_2() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{";
        TestClass_1_1 obj = mapper.readValue(json, TestClass_1_1.class);
    }
    @Test
    public void readValue_12_3() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"a\":1, \"b\":0 }";
        TestClass_1_1 obj = mapper.readValue(json, TestClass_1_1.class);

        assertNotNull(obj);
    }
    @Test(expected = MismatchedInputException.class)
    public void readValue_13_6() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"a\":{\"a\":1, \"b\":0}}";
        TestClass_1_1 obj = mapper.readValue(json,TestClass_1_1.class);

        assertNotNull(obj);
    }
    @Test
    public void readValue_14_9() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"a\":{\"a\":1, \"b\":0}, \"b\":{\"a\":1, \"b\":0}}";
        TestClass_7_7 obj = mapper.readValue(json,TestClass_7_7.class);

        assertNotNull(obj);
    }
    @Test
    public void readValue_15_10() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"a\":{\"a\":1, \"b\":0}}";
        TestClass_4_4 obj = mapper.readValue(json,TestClass_4_4.class);

        assertNotNull(obj);
    }
    @Test
    public void readTreeBase() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        String json = "{\"x\":1,\"y\":\"hello world\",\"z\":{\"a\":0,\"b\":-1}}";
        JsonNode node = mapper.readTree(json);
    }
    @Test
    public void readTree_16_1 () throws Exception{
        String json = "{\"a\": 1}";
        JsonNode node = mapper.readTree(json);
        assertNotNull(node);
    }
    @Test
    public void readTree_17_5 () throws Exception{
        String json = "{\"a\": {\"x\": 1}}";
        JsonNode node = mapper.readTree(json);
        assertNotNull(node.get("a"));
    }
    @Test
    public void readTree_18_6 () throws Exception{
        String json = "{\"a\": {\"x\": 1}, \"b\": {\"x\": 1}}";
        JsonNode node = mapper.readTree(json);
        assertNotNull(node.get("a"));
        assertNotNull(node.get("b"));
    }
    @Test
    public void readTree_19_8 () throws Exception{
        String json = "{\"a\": {\"x\": {\"b\": -1}}}";
        JsonNode node = mapper.readTree(json);
        assertNotNull(node.get("a").get("x"));
    }
    @Test
    public void readTree_20_9 () throws Exception{
        String json = "{\"a\": {\"x\": {\"b\": -1}}, \"b\": {\"x\": {\"b\": -1}}}";
        JsonNode node = mapper.readTree(json);
        assertNotNull(node.get("a").get("x"));
        assertNotNull(node.get("b").get("x"));
    }
    @Test(expected = JsonEOFException.class)
    public void readTree_21_10 () throws Exception{
        String json = "{\"a\": {\"x\": 1}, \"b\": {\"x\": 1}";
        JsonNode node = mapper.readTree(json);
    }

    public boolean isValidJSON(String json) {
        try{
            mapper.readTree(json);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}

