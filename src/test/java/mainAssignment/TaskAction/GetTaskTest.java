package mainAssignment.TaskAction;

import api_responses.Task;
import api_responses.User;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import mainAssignment.Interfaces.SingleTestInterface;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetTaskTest extends TaskBaseTest implements SingleTestInterface {
    User user = new User();
    Task newTask = new Task();

    private final ExtentTest extentTest = extent.createTest("Add Task Test");


    @Override
    @BeforeTest
    public void init() {
        log.info("add task test before ");

        user = userList.get(0);

    }

    @Parameters("limit")
    @Test
    public void getTask(String noOfData) {
        log.info("add task test int");
        extentTest.log(Status.PASS,"Get task test setup");

        String data = "{\n" +
                "\"description\":" + newTasksList.get(0) + ",\n" +
                "}";

        requestSpecification = super.getRequestSpecBuilder().setBasePath(getTaskPath + noOfData).addHeader("Authorization",
                "Bearer " + user.getToken()).build();
        responseSpecification = super.getResponseSpecBuilder().build();

        response = given().spec(requestSpecification)
                .when()
                .post().then()
                .spec(responseSpecification).extract().response();

        if (response.getStatusCode() == 200) {

            System.out.println("des " + response.body().jsonPath().getString("data.description"));

            TaskCreator taskCreator = new TaskCreator(response);

            newTask = taskCreator.getTask();

            taskSheetHandler.addTaskData(newTask);

            extentTest.log(Status.PASS,"Get task Test successfully");

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
    public void responseValidation() {
        super.responseValidation(responseSpecification,"task_json_schema.json");
        extentTest.log(Status.PASS,"Response Validation successful");

    }

}