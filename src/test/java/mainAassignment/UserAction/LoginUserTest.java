package mainAassignment.UserAction;

import api_responses.User;
import io.restassured.http.ContentType;
import mainAassignment.Interfaces.SingleTestInterface;
import mainAassignment.TaskAction.AddTaskTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.RestAssured.given;


public class LoginUserTest extends UserBaseTest implements SingleTestInterface {

    @BeforeTest
    public void init(){
        log.info("login test before ");
    }

    @Test
    public void logIn(){
        log.info("login test int");

        String data = "{\n" +
                "    \"email\":\"neeraj123@gmail.com\",\n" +
                "    \"password\":\"12345678\"\n" +
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

          User user = userCreator.getUser();

          System.out.println("email " + user.getEmail());
          System.out.println("age" + user.getAge());
          System.out.println("id " + user.getId());
          System.out.println("v " + user.getV());

      }


    }

    @Override
    @Test(priority = 1)
    public void statusCodeValidation(){
        super.statusCodeValidation(responseSpecification,200);
    }

    @Override
    @Test(priority = 1)
    public void contentTypeValidation(){
        super.contentTypeValidation(responseSpecification);
    }

    @Override
    @Test(priority = 1)
    public void responseValidation(){
        super.responseValidation(responseSpecification,"user_json_schema.json");
    }



}
