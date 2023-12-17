package com.ada.bookStore.test.user;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;

public class UserRequestDefinitions {
    private RequestSpecification request = RestAssured.given()
            .baseUri("http://localhost:8080")
            .contentType(ContentType.JSON);

    private UserRequest user = new UserRequest();
    private Response response = null;

    @Given("user is unknown")
    public void userIsUnknown(){
//        UserRequest user = new UserRequest();
        user.setName(RandomStringUtils.randomAlphabetic(20));
        user.setPassword(RandomStringUtils.randomAlphabetic(10));
        user.setEmail(RandomStringUtils.randomAlphabetic(10)+"@email.com");
    }

    @When("user is registered with success")
    public void userIsRegistered(){
        response = request.body(user).when().post("/user");
        response.then().statusCode(201);
    }

    @Then("user is known")
    public void userIsKnown(){
        response = request.when().get("/user/name/{name}", user.getName());
        response.then().statusCode(200);
        String nameResponse = response.jsonPath().get("[0].name");
        Assertions.assertEquals(user.getName(), nameResponse);
    }




}
