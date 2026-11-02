package com.example.qa.tests;

import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    protected RequestSpecification spec;

    protected abstract String getBaseUri();

    @BeforeMethod
    public void setUp() {
        spec = new RequestSpecBuilder()
                .setBaseUri(getBaseUri())
                .addHeader("Accept", "application/json")
                .build();
    }
}
