package mainAassignment.TaskAction;

import Utils.TaskSheetHandler;
import api_responses.Task;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import mainAassignment.Interfaces.BaseTestInterface;
import mainAassignment.UserAction.UserBaseTest;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.ArrayList;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class TaskBaseTest implements BaseTestInterface {


    public static final String baseUrl = "https://api-nodejs-todolist.herokuapp.com";
    public static final String addTaskPath = "/task";
    public static final String getTaskPath = "/task?limit=2&skip=10";

    public static final String authToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MjQ2YjFhOWQ3ZTZjMjAwMTc1ZDYxNTMiLCJpYXQiOjE2NDg4MDAxNjl9.ph8rITOgwcbjgpjVzrlhEMEvo33Xl1EASUJdmRLxO6g";


    public static TaskSheetHandler taskSheetHandler;
    public ArrayList<Task> taskList = new ArrayList<>();
    Logger log = Logger.getLogger(UserBaseTest.class);



    Response response ;
    RequestSpecification requestSpecification;
    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    ResponseSpecification responseSpecification;
    ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();


    @BeforeTest
    @Override
    public void testInitialization() {
        log.info("base test initialized");
        try {
            taskSheetHandler = new TaskSheetHandler("src/main/java/Utils/TasksDetails.xlsx");
            taskList = taskSheetHandler.readTaskFile();
        }catch (IOException e){
            log.info(e.getMessage());
            log.error(e.getMessage());
            e.printStackTrace();
        }


    }

    @Override
    public RequestSpecBuilder getRequestSpecBuilder() {
        return requestSpecBuilder.setBaseUri(baseUrl).setContentType(ContentType.JSON);
    }

    @Override
    public ResponseSpecBuilder getResponseSpecBuilder() {
        return responseSpecBuilder.expectHeader("Content-Type", Matchers.equalTo("text/html; charset=utf-8"));
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
