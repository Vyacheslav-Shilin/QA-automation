package com.example.qa.tests;

import com.example.qa.models.CreatePostRequest;
import com.example.qa.models.PostResponse;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.anEmptyMap;
import org.testng.annotations.DataProvider;
import com.example.qa.clients.PostClient;

public class PostApiTest  extends BaseTest {


    @Override
    protected String getBaseUri() {
        return "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void shouldGetPostById() {
        PostClient postClient = new PostClient(spec);
        Response response = postClient.getPostById(1);
                response.then()
                .header("x-content-type-options", equalTo("nosniff"))
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", equalTo(1))
                .time(lessThan(5000L));
    }

    @Test
    public void shouldGetSecondPost() {

                PostClient postClient = new PostClient(spec);
                Response response = postClient.getPostById(2);
                response.then()
                .header("x-content-type-options", equalTo("nosniff"))
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("id", equalTo(2))
                .body("userId", equalTo(1))
                .time(lessThan(5000L));

    }

    @Test
    public void shouldExtractPostData () {
        PostClient postClient = new PostClient(spec);
        Response response = postClient.getPostById(1);
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

        CreatePostRequest requestBody = new CreatePostRequest(
                "My first post", "Learning API testing", 1);

                PostClient postClient = new PostClient(spec);
                Response response = postClient.createPost(requestBody);
                response.then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON);

        PostResponse post = response.as(PostResponse.class);
        Assert.assertEquals(post.getTitle(),"My first post");
        Assert.assertEquals(post.getBody(), "Learning API testing");
        Assert.assertEquals(post.getUserId(),1);
        Assert.assertTrue(post.getId() > 0);

        System.out.println("Created ID: " + post.getId());

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

                PostClient postClient = new PostClient(spec);
                Response response = postClient.updatePost(1, requestBody);
                response.then()
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
                PostClient postClient = new PostClient(spec);
                Response response = postClient.patchPost(1, requestBody);
                response.then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id",equalTo(1))
                .body("title", equalTo("Patched title"))
                .body("userId",equalTo(1));
    }

    @Test
    public void shouldDeletePost() {
                PostClient postClient = new PostClient(spec);
                Response response = postClient.deletePost(1);
                response.then()
                .log().all()
                .statusCode(200);

    }

    @Test
    public void shouldReturn404ForMissingPost() {
                PostClient postClient = new PostClient(spec);
                Response response = postClient.getPostById(9999);
                response.then()
                .log().all()
                .statusCode(404)
                .contentType(ContentType.JSON)
                .body("$", anEmptyMap());
    }

    @Test
    public void shouldReturn404ForUnknownRoute() {
        RestAssured.given()
                .spec(spec)
                .when()
                .get("/unknown-route")
                .then()
                .log().all()
                .statusCode(404)
                .contentType(ContentType.JSON)
                .body("$", anEmptyMap());
    }

    @DataProvider(name = "postData")
    public Object[][] postData() {
        return new Object[][] {
                {1, 1},
                {2, 1},
                {3, 1}
        };
    }

    @Test(dataProvider = "postData")
    public void shouldGetPostWithDataProvider(int postId, int expectedUserId) {

        System.out.println(
                "Проверяем postId=" + postId
                        + ", ожидаем userId=" + expectedUserId
        );

                PostClient postClient = new PostClient(spec);
                Response response = postClient.getPostById(postId);
                response.then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("id", equalTo(postId))
                .body("userId", equalTo(expectedUserId));

    }

    @DataProvider(name = "missingRoutes")
    public Object[][] missingRoutes() {
        return new Object[][] {
                {"/unknown-route"},
                {"/another-missing-route"}
        };
    }

    @Test(dataProvider = "missingRoutes")
    public void shouldReturn404ForMissingRoutes(String route) {

        RestAssured.given()
                .spec(spec)
                .when()
                .get(route)
                .then()
                .statusCode(404);

    }


}