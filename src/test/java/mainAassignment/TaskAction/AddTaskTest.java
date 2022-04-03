package mainAassignment.TaskAction;

import api_responses.Task;
import mainAassignment.Interfaces.SingleTestInterface;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddTaskTest extends TaskBaseTest implements SingleTestInterface {

    @Override
    @BeforeTest
    public void init(){
        log.info("add task test before ");
    }


    @Test
    public void addTask(){
        log.info("add task test int");

        String data = "{\n" +
                    "\"description\":\"task description\",\n" +
                "}";

        requestSpecification = super.getRequestSpecBuilder().setBasePath(addTaskPath).addHeader(  "Authorization",
                "Bearer " + authToken).setBody(data).build();
        responseSpecification = super.getResponseSpecBuilder().build();

        response =   given().spec(requestSpecification)
                .when()
                .post().then()
                .spec(responseSpecification).extract().response();

        if(response.getStatusCode() == 201) {

            System.out.println("des "+response.body().jsonPath().getString("data.description"));

            TaskCreator taskCreator = new TaskCreator(response);

            Task newTask = taskCreator.getTask();

            taskSheetHandler.addTaskData(newTask);
        }


    }


    @Override
    @Test(priority = 1)
    public void statusCodeValidation() {
        super.statusCodeValidation(responseSpecification,201);
    }

    @Override
    @Test(priority = 1)
    public void contentTypeValidation(){
        super.contentTypeValidation(responseSpecification);
    }

    @Override
    public void responseValidation() {
        super.responseValidation(responseSpecification,"task_json_schema.json");
    }

}
