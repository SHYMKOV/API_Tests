package api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.assertj.core.api.SoftAssertions;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static api.YAMLDeserializer.fromFileToMap;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestAPI {

    public static String baseUrl;
    public static String categoryUrl;
    public static String eventAll;

    static Map<String, String> map;

    @BeforeAll
    static void beforeAll() {
        map = fromFileToMap("data");
        baseUrl = map.get("baseUrl");
        categoryUrl = map.get("categoryUrl");
        eventAll = map.get("eventAll");
    }

    @Test
    public void getAllCategoriesTest() {
        given()
                .when()
                .get(baseUrl + categoryUrl + "All")
                .then()
                .statusCode(200).assertThat()
                .body("size()", is(24));
    }

    @Test
    public void verifyStartDate() {
        RestAssured.baseURI = eventAll;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("");

        JsonPath jsonPathEvaluator = response.jsonPath();

        List<LinkedHashMap<String, String>> events = jsonPathEvaluator.get("items");
        SoftAssertions softAssertions = new SoftAssertions();

        for (LinkedHashMap<String, String> event : events) {
            softAssertions.assertThat(LocalDateTime.parse(event.get("dateFrom")).isBefore(LocalDateTime.now()))
                    .withFailMessage(event.get("title") + " did not pass the start date check ").isTrue();
        }

        softAssertions.assertAll();
    }

    @ParameterizedTest
    @MethodSource("provideImpl")
    public void getAllCategoriesIdTest(String url, String title) {
        given()
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .assertThat()
                .body("title", is(title));
    }

    private static Stream<Arguments> provideImpl() {
        String eventUrl = "https://eventsexpress-test.azurewebsites.net/api/Event/";
        return Stream.of(
                Arguments.of(eventUrl + "d5f3b3a7-4a8b-4b20-2d1b-08d9f149cc58", "Woman in Science"),
                Arguments.of(eventUrl + "31c9799b-4125-441d-0595-08d9f718ca2e", "Wash your window")
        );
    }

}
