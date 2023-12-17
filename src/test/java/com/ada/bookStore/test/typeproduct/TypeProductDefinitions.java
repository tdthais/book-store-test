package com.ada.bookStore.test.typeproduct;

import com.ada.bookStore.test.user.UserRequest;
import com.jayway.jsonpath.JsonPath;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class TypeProductDefinitions {

    private RequestSpecification request = RestAssured.given()
            .baseUri("http://localhost:8080")
            .contentType(ContentType.JSON);

    private TypeProductRequest typeProduct = new TypeProductRequest();
    private Response response = null;

    @Given("typeProduct is unknown")
    public void typeProductIsUnknown(){
        typeProduct.setName(RandomStringUtils.randomAlphabetic(20));
    }

    @When("typeProduct is registered with success")
    public void typeProductIsRegistered(){
        response = request.body(typeProduct).when().post("/book-subject");
        response.then().statusCode(201);
    }

    @Then("typeProduct is known")
    public void typeProductIsKnown(){
        response = request.when().get("/book-subject?name=" + typeProduct.getName());
        response.then().statusCode(200);

        List<String> nameResponse = response.jsonPath().get("name");
        System.out.println(nameResponse.get(0));
        Assertions.assertTrue(nameResponse.stream().anyMatch(s -> s.equals(typeProduct.getName())));
    }

}
