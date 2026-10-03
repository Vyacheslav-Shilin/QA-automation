package com.example.qa;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.anEmptyMap;

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

    @Test
    public void shouldExtractPostData () {
        Response response = RestAssured.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/posts/1");
        response.then().statusCode(200);
        JsonPath data = response.jsonPath();
        int id = data.getInt("id");
        int userId = data.getInt("userId");
        String title = data.getString("title");

        Assert.assertEquals(id, 1);
        Assert.assertEquals(userId,1);

        System.out.println("Title: " + title);

    }

    @Test
    public void shouldExtractSecondUserData () {

        String json = """
        {
          "users": [
            {
              "id": 101,
              "name": "Alice",
              "address": {"city": "Almaty"}
            },
            {
              "id": 102,
              "name": "Bob",
              "address": {"city": "Astana"}
            }
          ]
        }
        """;

        JsonPath data = new JsonPath(json);

        int id = data.getInt("users[1].id");
        String name = data.getString("users[1].name");
        String city = data.getString("users[1].address.city");

        Assert.assertEquals(id,102);
        Assert.assertEquals(name, "Bob");
        Assert.assertEquals(city,"Astana");

    }

    @Test
    public void shouldCreatePost(){

        String requestBody = """
        {
          "title": "My first post",
          "body": "Learning API testing",
          "userId": 1
        }
        """;

        Response response = RestAssured.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/posts");
                response.then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("title", equalTo("My first post"))
                .body("body", equalTo("Learning API testing"))
                .body("userId",equalTo(1));

        int createdId = response.jsonPath().getInt("id");
        Assert.assertTrue(createdId > 0);
        System.out.println("Created ID: " + createdId);

    }

    @Test
    public void shouldUpdatePost() {
        String requestBody = """
        {
          "id": 1,
          "title": "Updated title",
          "body": "Updated text",
          "userId": 1
        }
        """;
        RestAssured.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/posts/1")
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id",equalTo(1))
                .body("title", equalTo("Updated title"))
                .body("body", equalTo("Updated text"))
                .body("userId",equalTo(1));

    }

    @Test
    public void shouldPatchPostTitle() {
        String requestBody = """
        {
          "title": "Patched title"
        }
        """;
                RestAssured.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch("/posts/1")
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id",equalTo(1))
                .body("title", equalTo("Patched title"))
                .body("userId",equalTo(1));
    }

    @Test
    public void shouldDeletePost() {
                RestAssured.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .delete("/posts/1")
                .then()
                .log().all()
                .statusCode(200);

    }

    @Test
    public void shouldReturn404ForMissingPost() {
        RestAssured.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/posts/9999")
                .then()
                .log().all()
                .statusCode(404)
                .contentType(ContentType.JSON)
                .body("$", anEmptyMap());
    }

    @Test
    public void shouldReturn404ForUnknownRoute() {
        RestAssured.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/unknown-route")
                .then()
                .log().all()
                .statusCode(404)
                .contentType(ContentType.JSON)
                .body("$", anEmptyMap());
    }
}