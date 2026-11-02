package com.example.qa.tests;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JsonPathTest {

    @Test
    public void jsonCheck (){
        String json = """
        {
          "id": 101,
          "name": "Alice",
          "address": {
            "city": "Almaty"
          },
          "roles": ["reader", "editor"]
        }
        """;
        JsonPath data = new JsonPath(json);

        int id = data.getInt("id");
        String name = data.getString("name");
        String city = data.getString("address.city");
        String secondRole = data.getString("roles[1]");


        Assert.assertEquals(id,101);
        Assert.assertEquals(name,"Alice");
        Assert.assertEquals(city, "Almaty");;
        Assert.assertEquals(secondRole, "editor");
    }
}
