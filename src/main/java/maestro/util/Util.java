package maestro.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class Util {
    public static ResponseEntity<Object> createResponseEntity(Object responseObject) {
        Map<String, Object> response = new HashMap<>();
        response.put("response", responseObject);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
