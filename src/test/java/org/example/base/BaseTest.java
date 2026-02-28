package org.example.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.example.utils.TestResultLogger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestResultLogger.class)
public abstract class BaseTest {

    @BeforeAll
    public static void setup() {
        // Base URI configuration
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Global Request Configuration
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .log(LogDetail.URI)
                .build();

        // Global Response Configuration
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .build();
    }
}
