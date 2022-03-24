package api;

import api.DTO.UserProfileDTO;
import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static api.ApiClient.requestConf;
import static api.UserHandler.logIn;
import static api.Utils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewTest {

    UserHandler uh = new UserHandler();
    UserProfileDTO usrProf;
    String USER_TOKEN;

    @BeforeEach
    public void beforeEach(){

        USER_TOKEN = logIn("volodeead.v@gmail.com", "1qaz2wsx3edc");
        usrProf = uh.getUserProfile(USER_TOKEN);

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

        Gson g = new Gson();

        String changeGenderUrl = "https://eventsexpress-test.azurewebsites.net/api/Users/EditGender";
        JSONObject requestBody = new JSONObject();

        List<Integer> index = new ArrayList<Integer>(List.of(0,1,2));

        //TODO
        index.remove(usrProf.getGender().getOrdinalGender());

        int newGenderInt = (int)(Math.random()*2);
        requestBody.put("gender", newGenderInt );

        RequestSpecification request = requestConf(USER_TOKEN);

        request.body(requestBody.toString());
        Response response = request.post(changeGenderUrl);

        assertEquals( Gender.fromInt(newGenderInt) , uh.getUserProfile(USER_TOKEN).getGender() );

    }

    @Test
    public void changeBirthdayDate(){
        String baseUrl = "https://eventsexpress-test.azurewebsites.net/api/Users/EditBirthday";
        JSONObject requestBody = new JSONObject();
        requestBody.put("birthday", "1990-03-17");
        RequestSpecification request = requestConf(USER_TOKEN);
        request.body(requestBody.toString());
        Response response = request.post(baseUrl);
        assertTrue(usrProf.getBirthday().equals("1990-03-17T00:00:00"));

    }


}
