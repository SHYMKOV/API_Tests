package api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import static api.Utils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewTest {

    UserProfile usrProf;
    String USER_TOKEN;

    @BeforeEach
    public void beforeEach(){

        USER_TOKEN = logIn("volodeead.v@gmail.com", "1qaz2wsx3edc");
        usrProf = new UserProfile(USER_TOKEN);

    }

    @Test
    public void validateCategory() {

        RequestSpecification request = requestConf(USER_TOKEN);

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


    @Test
    public void changeGender(){

        String changeGenderUrl = "https://eventsexpress-test.azurewebsites.net/api/Users/EditGender";
        JSONObject requestBody = new JSONObject();

        List<Integer> index = new ArrayList<Integer>(List.of(0,1,2));

        //TODO
        index.remove(usrProf.getGender().ordinal());

        int newGenderInt = (int)(Math.random()*2);
        requestBody.put("gender", newGenderInt );

        RequestSpecification request = requestConf(USER_TOKEN);

        request.body(requestBody.toString());
        Response response = request.post(changeGenderUrl);

        UserProfile tempUsr = new UserProfile(USER_TOKEN);

        assertEquals( Gender.fromInt(newGenderInt) , tempUsr.getGender() );

    }





}
