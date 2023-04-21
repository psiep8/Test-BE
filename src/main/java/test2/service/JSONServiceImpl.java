package test2.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import test2.interfaces.JSONService;

import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class JSONServiceImpl implements JSONService {

    @Override
    public JSONObject getResult(JSONObject jsonFile) throws Exception {
        JSONParser jsonParser = new JSONParser();
        String[] keys = {"HTTP_302", "HTTP_NO", "MIME_ALL", "abcde"};
        JSONObject finalResult = new JSONObject();
        for (String keyArray : keys) {
            JSONArray resultsKey = new JSONArray();
            for (Object key : jsonFile.keySet()) {
                JSONObject objKeyParse = (JSONObject) jsonParser.parse(jsonFile.toJSONString());
                JSONObject obj = (JSONObject) objKeyParse.get(key);
                JSONObject allObject = (JSONObject) obj.get("ALL");
                JSONObject resultObject = new JSONObject();
                if (allObject.get(keyArray) != null) {
                    resultObject.put("crawlId", key);
                    resultsKey.add(resultObject);
                    finalResult.put(keyArray, resultsKey);
                }
                for (Object o : allObject.keySet()) {
                    if (keyArray.equalsIgnoreCase((String) o)) {
                        resultObject.put("total", allObject.get(o));
                    }
                    if (o.toString().equalsIgnoreCase("INT-".concat(keyArray))) {
                        resultObject.put("totalInt", allObject.get("INT-".concat(keyArray)));
                    }
                    if (o.toString().equalsIgnoreCase("EXT-".concat(keyArray))) {
                        resultObject.put("totalExt", allObject.get("EXT-".concat(keyArray)));
                    }
                }
            }
        }
        return finalResult;
    }
}