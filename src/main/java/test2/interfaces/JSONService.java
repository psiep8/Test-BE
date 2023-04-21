package test2.interfaces;

import org.json.simple.JSONObject;

public interface JSONService {
    JSONObject getResult(JSONObject jsonFile) throws Exception;
}
