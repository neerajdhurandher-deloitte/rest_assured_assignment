package mainAassignment.UserAction;

import api_responses.User;
import mainAassignment.Interfaces.SingleTestInterface;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RegisterUserTest extends  UserBaseTest implements SingleTestInterface {

    @BeforeTest
    @Override
    public void init() {

    }

    @Test
    public void registerUser(){

        String data = "{\n" +
                "\t\"name\": \"Neeraj third\",\n" +
                "\t\"email\": \"neeraj3@gmail.com\",\n" +
                "\t\"password\": \"12345678\",\n" +
                "\t\"age\": 21\n" +
                "}";

        requestSpecification = super.getRequestSpecBuilder().setBasePath(registerPath).setBody(data).build();
        responseSpecification = super.getResponseSpecBuilder().build();


        response =   given().spec(requestSpecification)
                .when()
                .post().then()
                .spec(responseSpecification).extract().response();

        System.out.println(response.body().jsonPath().getString("user.name"));

        if(response.getStatusCode() == 201) {

            UserCreator userCreator = new UserCreator(response);

            User user = userCreator.getUser();

            userList.add(user);

            userSheetHandler.addUserData(user);
        }

    }


    @Override
    @Test(priority = 1)
    public void statusCodeValidation(){
        super.statusCodeValidation(responseSpecification,201);
    }

    @Override
    @Test(priority = 1)
    public void contentTypeValidation(){
        super.contentTypeValidation(responseSpecification);
    }
}
