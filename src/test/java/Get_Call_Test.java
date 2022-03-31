import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Get_Call_Test {


    Response response ;
    @BeforeTest
    public void initialize(){
        response = given().
                baseUri("https://jsonplaceholder.typicode.com").
                header("Content-Type","application/json").
                when().
                get("/posts")
                .then().extract().response();
    }

    @Test
    public void statusCodeTest(){

        if(response.statusCode()==200){
            System.out.println("Status code is 200");
        }
    }

    @Test
    public void userIdValidation(){

        assertThat(response.path("[39].userId"),is(equalTo(4)));
    }

    @Test
    public void titleTypeValidation(){
        assertThat(response.path("[]"),hasProperty("title"));
    }
}
