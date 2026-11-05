package com.example.qa.tests;
import com.example.qa.models.CreatePostRequest;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import org.testng.Assert;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Allure;

public class CreatePostRequestTest {

    @Test
    public void shouldKeepValuesFromConstructor() {
        CreatePostRequest request = new CreatePostRequest(
                "My first post", "Learning API testing", 1);

        Assert.assertEquals(request.getTitle(),"My first post");
        Assert.assertEquals(request.getBody(),"Learning API testing");
        Assert.assertEquals(request.getUserId(),1);

    }

    @Test
    public void shouldChangeTitle() {
        CreatePostRequest request = new CreatePostRequest(
                "My first post", "Learning API testing", 1);

        request.setTitle("New title");

        Assert.assertEquals(request.getTitle(),"New title");
        Assert.assertEquals(request.getBody(),"Learning API testing");
        Assert.assertEquals(request.getUserId(),1);
    }

    @Test
    public void shouldSerializePostRequest() throws JsonProcessingException {
        CreatePostRequest request = new CreatePostRequest("My first post","Learning API testing",1);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(request);
        Allure.attachment("JSON после сериализации", "application/json", json);
        System.out.println(json);
        JsonPath data = new JsonPath(json);

        Allure.step("Проверить поля JSON после сериализации", () -> {
            Assert.assertEquals(data.getString("title"), "My first post");
            Assert.assertEquals(data.getString("body"), "Learning API testing");
            Assert.assertEquals(data.getInt("userId"), 1);
        });

    }

}
