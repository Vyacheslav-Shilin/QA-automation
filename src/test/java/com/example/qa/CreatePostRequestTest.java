package com.example.qa;
import org.testng.annotations.Test;
import org.testng.Assert;

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
}
