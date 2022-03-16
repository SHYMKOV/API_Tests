package api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
