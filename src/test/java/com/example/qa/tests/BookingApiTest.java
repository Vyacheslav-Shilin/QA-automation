package com.example.qa.tests;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.testng.Assert;
import static org.hamcrest.Matchers.equalTo;


public class BookingApiTest extends BaseTest {

    @Override
    protected String getBaseUri() {
        return "https://restful-booker.herokuapp.com";
    }


    @Test
    public void shouldCreateGetAndDeleteBooking() {

        String authBody = """
                {
                  "username": "admin",
                  "password": "password123"
                }
                """;


        Response authResponce = RestAssured.given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(authBody)
                .when()
                .post("/auth");
        authResponce.then()
                .statusCode(200)
                .contentType(ContentType.JSON);

        String token = authResponce.jsonPath().getString("token");

        Assert.assertNotNull(token, "В ответе отсутствует token");
        Assert.assertFalse(token.isBlank(), "Токен пустой");

        String requestBody = """
                {
                  "firstname": "Alice",
                  "lastname": "Brown",
                  "totalprice": 150,
                  "depositpaid": true,
                  "bookingdates": {
                    "checkin": "2026-11-01",
                    "checkout": "2026-11-05"
                  },
                  "additionalneeds": "Breakfast"
                }
                
                """;


        Response response = RestAssured.given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/booking");
        response.then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON);

        int bookingId = response.jsonPath().getInt("bookingid");

        Assert.assertTrue(bookingId > 0);

        System.out.println(bookingId);

        RestAssured.given()
                .spec(spec)
                .when()
                .get("/booking/" + bookingId)
                .then()
                .log().all()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("firstname", equalTo("Alice"))
                .body("lastname", equalTo("Brown"))
                .body("totalprice", equalTo(150))
                .body("depositpaid", equalTo(true))
                .body("bookingdates.checkin", equalTo("2026-11-01"))
                .body("bookingdates.checkout", equalTo("2026-11-05"))
                .body("additionalneeds", equalTo("Breakfast"));

        RestAssured.given()
                .spec(spec)
                .cookie("token", "invalid-token")
                .when()
                .delete("/booking/" + bookingId)
                .then()
                .log().all()
                .statusCode(403);

        RestAssured.given()
                .spec(spec)
                .when()
                .get("/booking/" + bookingId)
                .then()
                .log().all()
                .statusCode(200);

        RestAssured.given()
                .spec(spec)
                .cookie("token", token)
                .when()
                .delete("/booking/" + bookingId)
                .then()
                .log().all()
                .statusCode(201);

        RestAssured.given()
                .spec(spec)
                .when()
                .get("/booking/" + bookingId)
                .then()
                .log().all()
                .statusCode(404);


    }

    @Test
    public void shouldCreateAuthToken() {

        String authBody = """
                {
                  "username": "admin",
                  "password": "password123"
                }
                """;


        Response authResponce = RestAssured.given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(authBody)
                .when()
                .post("/auth");
        authResponce.then()
                .statusCode(200)
                .contentType(ContentType.JSON);

        String token = authResponce.jsonPath().getString("token");

        Assert.assertNotNull(token, "В ответе отсутствует token");
        Assert.assertFalse(token.isBlank(), "Токен пустой");

    }


}
