package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewTest {
    protected String token;
    @BeforeEach
    public void beforeEach(){

    token = new TestAPI().logIn("volodeead.v@gmail.com", "1qaz2wsx3edc");
    }
    @Test
    public void validateCategory() {

        RequestSpecification request = new TestAPI().requestConf(token);

        Response response = request.get("https://eventsexpress-test.azurewebsites.net/api/Users/GetCategories");

        JSONArray resp = new JSONArray(response.asString());

        boolean result = false;

        for(Object element : resp){
            if(((JSONObject)element).get("name").equals("Danc")){
                result = true; break;
            }
        }

        assertTrue(result);
    }
    public String userId(String token) {
        String[] arrToken = token.split("\\.");

        Base64.Decoder decoder = Base64.getUrlDecoder();
        JSONObject payload = new JSONObject(new String(decoder.decode(arrToken[1])));

        return (String) payload.get("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name");
    }
    public JSONObject getUserProfile(){

        String userID = userId(token);
        RequestSpecification request = requestConf(token);
        Response response = request.get("https://eventsexpress-test.azurewebsites.net/api/Users/GetUserProfileById?id=" + userID);
        return new JSONObject(response.asString());

    }
    @Test
    public void changeBirthdayDate(){
        String baseUrl = "https://eventsexpress-test.azurewebsites.net/api/Users/EditBirthday";
        JSONObject requestBody = new JSONObject();
        requestBody.put("birthday", "1990-03-17");
        RequestSpecification request = requestConf(token);
        request.body(requestBody.toString());
        Response response = request.post(baseUrl);
        assertTrue(getUserProfile().get("birthday").equals("1990-03-17T00:00:00"));

    }

    public RequestSpecification requestConf(String token) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + token);
        return request;
    }

}
