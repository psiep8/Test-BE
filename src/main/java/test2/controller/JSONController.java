package test2.controller;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test2.interfaces.JSONService;

@RestController
public class JSONController {

    private final JSONService jsonService;

    public JSONController(JSONService jsonService) {
        this.jsonService = jsonService;
    }

    @PostMapping("/json-test")
    public JSONObject myJSONControllerTest(@RequestBody JSONObject jsonFile) throws Exception {
        return jsonService.getResult(jsonFile);
    }
}