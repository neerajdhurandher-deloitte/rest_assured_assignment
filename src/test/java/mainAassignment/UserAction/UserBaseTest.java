package mainAassignment.UserAction;

import Utils.UserSheetHandler;
import api_responses.Register_user_response;
import api_responses.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import mainAassignment.Interfaces.BaseTestInterface;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.ArrayList;

public class UserBaseTest implements BaseTestInterface {

    public static final String baseUrl = "https://api-nodejs-todolist.herokuapp.com";
    public static final String registerPath = "user/register";
    public static final String logInPath = "/user/login";


    public static UserSheetHandler userSheetHandler;
    public ArrayList<User> userList = new ArrayList<>();
    Logger log = Logger.getLogger(UserBaseTest.class);


    Response response ;
    RequestSpecification requestSpecification;
    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    ResponseSpecification responseSpecification;
    ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();


    @BeforeTest
    @Override
    public void testInitialization(){
        log.info("base test initialized");
        try {
            userSheetHandler = new UserSheetHandler("src/main/java/Utils/UserDetails.xlsx");
            userList = userSheetHandler.readUserFile();
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
}
