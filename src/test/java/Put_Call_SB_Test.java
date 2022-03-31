import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Put_Call_SB_Test {

    Response response ;
    RequestSpecification requestSpecification;
    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    ResponseSpecification responseSpecification;
    ResponseSpecBuilder responseSpecBuilder;


    @BeforeTest
    public void initialize(){

        String data = "{\n" +
                "    \"name\":\"Arun\",\n" +
                "    \"job\":\"Manager\"\n" +
                "}";

        requestSpecBuilder.setBaseUri("https://reqres.in/api").
                addHeader("Content-Type","application/json").
                setBody(data);

        requestSpecification = RestAssured.with().spec(requestSpecBuilder.build());

        response = requestSpecification.put("/users");

        responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecification = requestSpecBuilder.build().response();

    }

    @Test
    public void statusCodeValidation(){
        responseSpecBuilder.expectStatusCode(200);
    }

    @Test
    public void contentTypeValidation(){
        responseSpecBuilder.expectContentType(ContentType.JSON);
    }

    // TODO response doesn't has job field
//    @Test
//    public void titleValidation(){
//        responseSpecification.body("job", Matchers.hasToString("Manager"));
//    }
}
