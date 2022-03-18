package api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;

import static api.Utils.*;

public class UserProfile {

    private String birthday;
    private Gender gender;
    private String phone;
    private JSONArray myRates;
    private String name;
    private Boolean isBlocked;
    private int rating;
    private String id;
    private JSONArray categories;
    private JSONArray notificationTypes;
    private String email;
    private int attitude;

    private String token;

    public UserProfile( String token ) {

        String userID = userId(token);
        RequestSpecification request = requestConf(token);
        Response response = request.get("https://eventsexpress-test.azurewebsites.net/api/Users/GetUserProfileById?id=" + userID);

        JSONObject UserMap = new JSONObject(response.asString());

        // TODO

        setBirthday( (String) UserMap.get("birthday") );
        setGender( (int) UserMap.get("gender") );
        setPhone( (String) UserMap.get("phone") );
        setMyRates( (JSONArray) UserMap.get("myRates"));
        setName( (String) UserMap.get("name"));
        setBlocked( (Boolean) UserMap.get("isBlocked"));
        setRating( (int) UserMap.get("rating"));
        setId( (String) UserMap.get("id") );
        setCategories( (JSONArray) UserMap.get("categories"));
        setNotificationTypes( (JSONArray) UserMap.get("notificationTypes"));
        setEmail( (String) UserMap.get("email"));
        setAttitude( (int) UserMap.get("attitude"));

    }

    public String getToken(){

        return token;
    }

    public void setToken(){
        this.token = token;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = Gender.fromInt(gender);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public JSONArray getMyRates() {
        return myRates;
    }

    public void setMyRates(JSONArray myRates) {
        this.myRates = myRates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JSONArray getCategories() {
        return categories;
    }

    public void setCategories(JSONArray categories) {
        this.categories = categories;
    }

    public JSONArray getNotificationTypes() {
        return notificationTypes;
    }

    public void setNotificationTypes(JSONArray notificationTypes) {
        this.notificationTypes = notificationTypes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAttitude() {
        return attitude;
    }

    public void setAttitude(int attitude) {
        this.attitude = attitude;
    }

}
