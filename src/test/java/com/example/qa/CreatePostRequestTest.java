package com.example.qa;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import org.testng.Assert;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

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
        System.out.println(json);
        JsonPath data = new JsonPath(json);
        Assert.assertEquals(data.getString("title"),"My first post");
        Assert.assertEquals(data.getString("body"),"Learning API testing");
        Assert.assertEquals(data.getInt("userId"),1);
    }

}
