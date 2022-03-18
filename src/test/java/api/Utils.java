package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.Base64;

public class Utils {

    //public static String USER_TOKEN;

    public static String userId(String token) {
        String[] arrToken = token.split("\\.");

        Base64.Decoder decoder = Base64.getUrlDecoder();
        JSONObject payload = new JSONObject(new String(decoder.decode(arrToken[1])));

        return (String) payload.get("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name");
    }

    public static String logIn(String email, String password) {
        String baseUrl = "https://eventsexpress-test.azurewebsites.net/api/";
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", email);
        requestBody.put("password", password);

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());
        Response response = request.post(baseUrl + "Authentication/Login");

        JSONObject token = new JSONObject(response.asString());

        return (String)token.get("token");
    }

    public static RequestSpecification requestConf(String token) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + token);
        return request;
    }

}
