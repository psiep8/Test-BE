package test1;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONClass {
    public static void main(String[] args) {
        try {
            JSONParser jsonParser = new JSONParser();
            String content = new String(Files.readAllBytes(Paths.get("src/main/resources/file.json")));
            JSONObject json = (JSONObject) jsonParser.parse(content);
            String[] keys = {"HTTP_302", "HTTP_NO", "MIME_ALL", "abcde"};
            JSONObject finalResult = new JSONObject();
            for (String keyArray : keys) {
                JSONArray resultsKey = new JSONArray();
                for (Object key : json.keySet()) {
                    JSONObject obj = (JSONObject) json.get(key);
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
            System.out.println(finalResult);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}