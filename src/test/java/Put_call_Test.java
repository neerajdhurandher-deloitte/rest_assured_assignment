import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class Put_call_Test {
    Response response ;
    @BeforeTest
    public void initialize(){
        File jsonData = new File("src/test/java/resources/putcall.json");
        response = given().
                baseUri("https://reqres.in/api").
                body(jsonData).
                header("Content-Type","application/json").
                when().
                put("/users")
                .then().extract().response();
    }

    @Test
    public void statusCodeTest(){

        if(response.statusCode()==200){
            System.out.println("Status code is 200");
        }

        System.out.println(response.body());
    }

    @Test
    public void responseValidation(){
        System.out.println("response doesn't have any name & title field ");
    }
}
