package bens.graphtests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonDelimitedParser {
    public final ObjectMapper mapper = new ObjectMapper();

    public JsonDelimitedParser() {}

    public List<JsonNode> parseDelimitedJson(String json, String delimiter) throws JsonProcessingException {
        List<JsonNode> ret = new ArrayList<>();

        // check for empty string
        if(json == null || json.length() == 0){
            return ret;
        }

        String[] strings = json.split(delimiter);

        for (String str : strings) {
            if(str == null || str.length() == 0){
                continue;
            }
            JsonNode node = mapper.readTree(str);
            ret.add(node);
        }

        return ret;
    }
}
