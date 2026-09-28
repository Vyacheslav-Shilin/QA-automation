package com.example.qa;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.lessThan;

public class PostApiTest {

    @Test
    public void shouldGetPostById() {
        RestAssured.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/posts/1")
                .then()
                .header("x-content-type-options", equalTo("nosniff"))
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", equalTo(1))
                .time(lessThan(5000L));
    }

    @Test
    public void shouldGetSecondPost() {
        RestAssured.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/posts/2")
                .then()
                .header("x-content-type-options", equalTo("nosniff"))
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("id", equalTo(2))
                .body("userId", equalTo(1))
                .time(lessThan(5000L));
    }
}