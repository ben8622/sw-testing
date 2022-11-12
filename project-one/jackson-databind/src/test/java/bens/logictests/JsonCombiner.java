package bens.logictests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonCombiner {
    public final ObjectMapper mapper = new ObjectMapper();

    public JsonCombiner() {}

    public String combineJson(String s1, String s2) throws Exception, JsonProcessingException {
        if(isValidJson(s1) && !isValidJson(s2)) { return s1; }
        if(!isValidJson(s1) && isValidJson(s2)) { return s2; }
        if(!isValidJson(s1) && !isValidJson(s2)) { throw new Exception("Invalids JSON strings."); }

        // convert strings to JsonNodes
        JsonNode node1 = mapper.readTree(s1);
        JsonNode node2 = mapper.readTree(s2);

        List<Map.Entry<String, JsonNode>> children = new ArrayList<Map.Entry<String, JsonNode>>();

        // add children of original JSON to a collection
        Iterator<Map.Entry<String, JsonNode>> fields1 = node1.fields();
        fields1.forEachRemaining(field -> children.add(field));

        Iterator<Map.Entry<String, JsonNode>> fields2 = node2.fields();
        fields2.forEachRemaining(field -> children.add(field));

        // loop over all children and add them to a new object checking
        JsonNode combined = mapper.createObjectNode();
        for(Map.Entry<String, JsonNode> child : children){
            if(combined.has(child.getKey())) {
                throw new Exception("JSON strings contain duplicate keys");
            }
            ((ObjectNode)combined).put(child.getKey(), child.getValue());
        }
        return combined.toString();
    }

    public boolean isValidJson(String s) throws JsonProcessingException{
        try{
            mapper.readTree(s);
        }
        catch(Exception e) {
            return false;
        }
        return true;
    }
}
