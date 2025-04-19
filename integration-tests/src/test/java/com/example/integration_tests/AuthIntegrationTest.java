package com.example.integration_tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class AuthIntegrationTest {
    
    @BeforeAll
    static void setup(){
        RestAssured.baseURI = "http://localhost:4004";
    }

    @Test
    public void shouldReturnOKWithValidToken(){
        // 1. Arrange
        String loginPayload = 
        """
            {                   
                "email": "testuser@test.com",
                "password": "password123"
            }
        """;

        Response response = given()
            .contentType(ContentType.JSON)
            .body(loginPayload)
            .when()                 // 2. Act
            .post("/auth/login")
            .then()                 // 3. Assert
            .statusCode(200)                    // asserts that response has status code 200
            .body("token", notNullValue())      // asserts that response.body.token != null
            .extract()
            .response();

        System.out.println("Generated token haha: " + response.jsonPath().getString("token"));

        
    }


}
