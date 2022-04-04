package mainAssignment.UserAction;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
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

public class UserBaseTest extends BaseTest implements BaseTestInterface {

    public static final String baseUrl = "https://api-nodejs-todolist.herokuapp.com";
    public static final String registerPath = "user/register";
    public static final String logInPath = "/user/login";


    Response response ;
    RequestSpecification requestSpecification;
    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    ResponseSpecification responseSpecification;
    ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();


//    @BeforeTest
    @Override
    public void testInitialization(){
        log.info("user base test initialized");

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
        log.info("Status code is " + code);
    }

    @Override
    public void contentTypeValidation(ResponseSpecification responseSpecification){
        responseSpecification.expect().contentType(ContentType.JSON);
        log.info("Content type is JSON");
    }

    @Override
    public void responseValidation(ResponseSpecification responseSpecification, String jsonSchema){
        responseSpecification.expect().body(matchesJsonSchemaInClasspath(jsonSchema));
    }

    public void dataValidate(ExtentTest extentTest, String input, String response , String filed){
        if(input.equals(response)){
            extentTest.log(Status.PASS,"User "+filed+" is valid");
        }else{
            extentTest.log(Status.FAIL,"User "+filed+" is not valid");
        }
    }

    public void dataValidate(ExtentTest extentTest, int input, int response , String filed){
        if(input == response){
            extentTest.log(Status.PASS,"User "+filed+" is valid");
        }else{
            extentTest.log(Status.FAIL,"User "+filed+" is not valid");
        }
    }

    public ExtentReports getExtentReports(){
        return extent;
    }
}
