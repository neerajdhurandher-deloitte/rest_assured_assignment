package mainAssignment.UserAction;

import api_responses.User;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.http.ContentType;
import mainAssignment.Interfaces.SingleTestInterface;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class LoginUserTest extends UserBaseTest implements SingleTestInterface {

    private final ExtentTest extentTest = extent.createTest("Login Test");

    User userResponse;
    User inputUser;

    @BeforeTest
    public void init(){
        log.info("login test before ");
    }

//    @Parameters("user")
    @Test
    public void logIn(){

        inputUser = userList.get(0);

        extentTest.log(Status.PASS,"Login Test setup");

        log.info("login test int");

        String data = "{\n" +
                "    \"email\":"+inputUser.getEmail()+",\n" +
                "    \"password\":"+inputUser.getPassword()+" \n" +
                "}";

//        String data = "";
        requestSpecification = super.getRequestSpecBuilder().setBasePath(logInPath).setBody(data).setContentType(ContentType.JSON).build();
        responseSpecification = super.getResponseSpecBuilder().build();

      response =   given().spec(requestSpecification)
                    .when()
                    .post().then()
                    .spec(responseSpecification).extract().response();

      if(response.getStatusCode() == 200) {

          String name = response.body().jsonPath().getString("user.name");

          System.out.println("name " + name);

          UserCreator userCreator = new UserCreator(response);

          userResponse = userCreator.getUser();


          System.out.println("email " + userResponse.getEmail());
          System.out.println("age" + userResponse.getAge());
          System.out.println("id " + userResponse.getId());
          System.out.println("v " + userResponse.getV());

      }


    }

    @Override
    @Test(priority = 1)
    public void statusCodeValidation(){
        super.statusCodeValidation(responseSpecification,200);
        extentTest.log(Status.PASS,"Status Code Validation successful");

    }

    @Override
    @Test(priority = 1)
    public void contentTypeValidation(){
        super.contentTypeValidation(responseSpecification);
        extentTest.log(Status.PASS,"Content Type Validation successful");

    }

    @Override
    @Test(priority = 1)
    public void responseValidation(){
        super.responseValidation(responseSpecification,"user_json_schema.json");
        extentTest.log(Status.PASS,"Response Validation successful");

    }

    @Test(priority = 2)
    public void responseJsonValidation(){
        if(response.statusCode() == 201){
            dataValidate(extentTest,inputUser.getName(),userResponse.getName(),"name");
            dataValidate(extentTest,inputUser.getEmail(),userResponse.getEmail(),"email");
            dataValidate(extentTest,inputUser.getAge(),userResponse.getAge(),"age");
            extentTest.log(Status.PASS,"Response data Validation successful");

        }
    }


}
