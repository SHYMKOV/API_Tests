package api;

import api.DTO.UserProfileDTO;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.Base64;

import static api.ApiClient.requestConf;
import static api.Utils.*;

public class UserHandler {

    public UserProfileDTO getUserProfile (String token){

        String userID = getDecryptUserID(token);
        RequestSpecification request = requestConf(token);
        Response response = request.get("https://eventsexpress-test.azurewebsites.net/api/Users/GetUserProfileById?id=" + userID);

        return new Gson().fromJson(response.asString(), UserProfileDTO.class);
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



}
