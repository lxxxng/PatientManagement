package com.example.integration_tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;


public class PatientIntegrationTest {

    @BeforeAll
    static void setup(){
        RestAssured.baseURI = "http://localhost:4004";
    }

    @Test
    public void shouldReturnPatientsWithValidToken(){
        // 1. Arrange
        String loginPayload = 
        """
            {                   
                "email": "testuser@test.com",
                "password": "password123"
            }
        """;

        String token = given()
            .contentType(ContentType.JSON)
            .body(loginPayload)
            .when()                 // 2. Act
            .post("/auth/login")
            .then()                 // 3. Assert
            .statusCode(200)                    // asserts that response has status code 200
            .extract()
            .jsonPath()
            .get("token");

        given()
            .header("Authorization", "Bearer " + token) // add the token to the header
            .when()                 // 2. Act
            .get("/api/patients")
            .then()                 // 3. Assert
            .statusCode(200)                    
            .body("patients", notNullValue());
    }
}
