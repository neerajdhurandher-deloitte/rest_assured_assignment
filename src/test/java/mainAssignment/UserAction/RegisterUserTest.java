package mainAssignment.UserAction;

import api_responses.User;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import mainAssignment.Interfaces.SingleTestInterface;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class RegisterUserTest extends  UserBaseTest implements SingleTestInterface {
    User user;
    String name;
    String email;
    String password;
    int age;
    Random rand = new Random();

    ExtentTest extentTest;

    @BeforeTest
    @Override
    public void init() {
        name = getRandomName();
        email = getRandomEmail();
        password = getRandomPassword();
        age = rand.nextInt(100);
        extentTest = extent.createTest("User Registration");
    }

    @Test
    public void registerUser(){

        String data = "{\n" +
                "   \"name\": \""+name+"\",\n" +
                "   \"email\": \""+email+"@gmail.com\",\n" +
                "   \"password\": \""+password+"\",\n" +
                "   \"age\": "+age+"\n" +
                "}";


        requestSpecification = super.getRequestSpecBuilder().setBasePath(registerPath).setBody(data).build();
        responseSpecification = super.getResponseSpecBuilder().build();


        response =   given().spec(requestSpecification)
                .when()
                .post().then()
                .spec(responseSpecification).extract().response();


        if(response.getStatusCode() == 201) {

        System.out.println(response.body().jsonPath().getString("user.name"));

            UserCreator userCreator = new UserCreator(response);

            user = userCreator.getUser();

            user.setPassword(password);

            userList.add(user);

            userSheetHandler.addUserData(user);
        }

        extentTest.log(Status.PASS,"User registration successfully");

    }


    @Override
    @Test(priority = 1)
    public void statusCodeValidation(){
        super.statusCodeValidation(responseSpecification,201);
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
    public void responseValidation() {
        super.responseValidation(responseSpecification,"user_json_schema.json");
        extentTest.log(Status.PASS,"Response Validation successful");

    }

    @Test(priority = 2)
    public void responseJsonValidation(){
        if(response.statusCode() == 201){
            dataValidate(extentTest,user.getName(),name,"name");
            dataValidate(extentTest,user.getEmail(),email,"email");
            dataValidate(extentTest,user.getAge(),age,"age");
            extentTest.log(Status.PASS,"Response data Validation successful");

        }
    }


    protected String getRandomEmail() {
        return getRandom("abcdefghijklmnopqrstuvwxyz1234567890");
    }
    protected String getRandomName() {
        return getRandom("abcdefghijklmnopqrstuvwxyz");
    }
    protected String getRandomPassword() {
        return getRandom("abcdefghijklmnopqrstuvwxyz1234567890@#$%");
    }

    protected String getRandom(String input) {
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * input.length());
            salt.append(input.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

}
