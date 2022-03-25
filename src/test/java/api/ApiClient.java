package api;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class ApiClient {

    public static RequestSpecification requestConf(String token) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + token);
        return request;
    }

}
