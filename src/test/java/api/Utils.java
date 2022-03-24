package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.Base64;

public class Utils {

    public static String getDecryptUserID(String token) {
        String[] arrToken = token.split("\\.");

        Base64.Decoder decoder = Base64.getUrlDecoder();
        JSONObject payload = new JSONObject(new String(decoder.decode(arrToken[1])));

        return (String) payload.get("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name");
    }

}
