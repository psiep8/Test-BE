import org.json.simple.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONParser {
    public static void main(String[] args) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/file.json")));
        JSONObject json = new JSONObject(content);
        String[] keys = {"HTTP_302", "HTTP_NO", "MIME_ALL"};
        Object extHttp = null;
        Object intHttp = null;
        JSONObject finalResult = new JSONObject();
        for (String key : json.keySet()) {
            JSONArray resultsKey = new JSONArray();
            JSONObject obj = (JSONObject) json.get(key);
            JSONObject allObject = (JSONObject) obj.get("ALL");
            JSONObject resultObject = new JSONObject();
            resultObject.put("crawlId", key);
            resultsKey.add(resultObject);
            for (String k : allObject.keySet()) {
                for (String s : keys) {
                    finalResult.put(s, resultsKey);
                    if (k.equals("EXT-".concat(s)) || k.equals("INT-".concat(s))) {
                        if ("EXT-".concat(s).equals(k)) {
                            extHttp = allObject.get("EXT-".concat(s));
                            System.out.println("EXT-".concat(s) + " " + extHttp);
                            resultObject.put("totalExt", extHttp);
                        }
                        if ("INT-".concat(s).equals(k)) {
                            intHttp = allObject.get("INT-".concat(s));
                            System.out.println("INT-".concat(s) + " " + intHttp);
                            resultObject.put("totalInt", intHttp);
                        }
//                        resultObject.put("total");
                    }
                }
            }
        }
        System.out.println(finalResult);

    }
}