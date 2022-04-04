package mainAssignment.Interfaces;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public interface BaseTestInterface {

    void testInitialization();

    RequestSpecBuilder getRequestSpecBuilder();

    ResponseSpecBuilder getResponseSpecBuilder();

    void statusCodeValidation(ResponseSpecification responseSpecification, int code);

    void contentTypeValidation(ResponseSpecification responseSpecification);

    void responseValidation(ResponseSpecification responseSpecification, String jsonSchema);


    }
