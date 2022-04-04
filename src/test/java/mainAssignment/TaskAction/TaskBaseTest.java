package mainAssignment.TaskAction;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import mainAssignment.BaseTest;
import mainAssignment.Interfaces.BaseTestInterface;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class TaskBaseTest extends BaseTest implements BaseTestInterface {


    public static final String baseUrl = "https://api-nodejs-todolist.herokuapp.com";
    public static final String addTaskPath = "/task";
    public static final String getTaskPath = "/task?limit=";

    public static final String authToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MjQ2YjFhOWQ3ZTZjMjAwMTc1ZDYxNTMiLCJpYXQiOjE2NDg4MDAxNjl9.ph8rITOgwcbjgpjVzrlhEMEvo33Xl1EASUJdmRLxO6g";


    Response response ;
    RequestSpecification requestSpecification;
    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    ResponseSpecification responseSpecification;
    ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();


    @BeforeTest
    @Override
    public void testInitialization() {
        log.info("tassk base test initialized");


    }

    @Override
    public RequestSpecBuilder getRequestSpecBuilder() {
        return requestSpecBuilder.setBaseUri(baseUrl).setContentType(ContentType.JSON);
    }

    @Override
    public ResponseSpecBuilder getResponseSpecBuilder() {
        return responseSpecBuilder.expectHeader("Content-Type", Matchers.equalTo("application/json; charset=utf-8"));
    }

    @Override
    public void statusCodeValidation(ResponseSpecification responseSpecification, int code){
        responseSpecification.expect().statusCode(code);
        log.info("Status code is 200");
    }

    @Override
    public void contentTypeValidation(ResponseSpecification responseSpecification){
        responseSpecification.expect().contentType(ContentType.JSON);
        log.info("Content type is JSON");
    }

    @Override
    public void responseValidation(ResponseSpecification responseSpecification, String jsonSchema) {
        responseSpecification.expect().body(matchesJsonSchemaInClasspath(jsonSchema));
    }

}
