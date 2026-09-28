package com.example.qa;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class PostApiTest {

    @Test
    public void shouldGetPostById() {
        RestAssured.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200); // Здесь будет запрос и проверка
    }

    @Test
    public void shouldGetSecondPost() {
        RestAssured.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/posts/2")
                .then()
                .statusCode(200); // Здесь будет запрос и проверка
    }
}