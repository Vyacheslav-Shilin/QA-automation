package com.example.qa.clients;

import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.example.qa.models.CreatePostRequest;
import io.restassured.http.ContentType;

public class PostClient {

    private final RequestSpecification spec;

    public PostClient(RequestSpecification spec) {
        this.spec = spec;
    }

    public Response getPostById(int postId) {
        return RestAssured.given()
                .spec(spec)
                .when()
                .get("/posts/" + postId);
    }

    public Response createPost(CreatePostRequest requestBody) {
        return RestAssured.given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/posts");
    }

    public Response deletePost(int postId) {
        return RestAssured.given()
                .spec(spec)
                .when()
                .delete("/posts/" + postId);
    }

    public Response updatePost(int postId, String requestBody) {
        return RestAssured.given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/posts/" + postId);
    }

    public Response patchPost(int postId, String requestBody) {
        return RestAssured.given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch("/posts/" + postId);
    }

}
