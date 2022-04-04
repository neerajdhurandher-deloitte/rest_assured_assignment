package mainAssignment;

import Utils.NewTaskSheetHandler;
import Utils.TaskSheetHandler;
import Utils.UserSheetHandler;
import api_responses.Task;
import api_responses.User;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

public class BaseTest {

    public static UserSheetHandler userSheetHandler;
    public ArrayList<User> userList = new ArrayList<>();
    public static TaskSheetHandler taskSheetHandler;
    public ArrayList<Task> taskList = new ArrayList<>();
    public static NewTaskSheetHandler newTaskSheetHandler;
    public ArrayList<String> newTasksList = new ArrayList<>();

    public ExtentSparkReporter extentSparkReporter;
    public ExtentReports extent = new ExtentReports();
    public Logger log = Logger.getLogger(BaseTest.class);


    @BeforeTest
    public void testInit(){
        ExtentTest extentTest = extent.createTest("Base Test");
        extentTest.log(Status.PASS,"Test setup");
        log.info("Base test initialized");
        try {
            // get all users from xlsx sheet
            userSheetHandler = new UserSheetHandler("src/main/java/Utils/UserDetails.xlsx");
            userList = userSheetHandler.readUserFile();
            extentTest.log(Status.PASS,"successfully read user list");
            System.out.println(userList.get(0).getName());

        }catch (IOException e){
            log.error(e.getMessage());
            e.printStackTrace();
            extentTest.log(Status.FAIL,"read user list unsuccessful");
        }

        try {
            // get all tasks from xlsx sheet
            taskSheetHandler = new TaskSheetHandler("src/main/java/Utils/TasksDetails.xlsx");
            taskList = taskSheetHandler.readTaskFile();
            extentTest.log(Status.PASS,"successfully read task list");

        }catch (IOException e){
            log.error(e.getMessage());
            e.printStackTrace();
            extentTest.log(Status.FAIL,"read task list unsuccessful");
        }

        try {
            // get all new tasks from xlsx sheet
            newTaskSheetHandler = new NewTaskSheetHandler("src/main/java/Utils/NewTasks.xlsx");
            newTasksList = newTaskSheetHandler.readNewTaskFile();
            extentTest.log(Status.PASS,"successfully read new tasks list");

        }catch (IOException e){
            log.error(e.getMessage());
            e.printStackTrace();
            extentTest.log(Status.FAIL,"read new task list unsuccessful");
        }

        // extent report setup
        extentSparkReporter = new ExtentSparkReporter("src/test/java/mainAssignment/Reports/Test_Report.html");

        extentSparkReporter.config().setEncoding("utf-8");
        extentSparkReporter.config().setDocumentTitle("Main Assignment Report");
        extentSparkReporter.config().setReportName("Test Reports");
        extentSparkReporter.config().setTheme(Theme.DARK);


        extent.setSystemInfo("Organization","Hashedin By Deloitte");
        extent.setSystemInfo("Created by ","Neeraj Dhurandher");
        extent.attachReporter(extentSparkReporter);

        extentTest.log(Status.PASS,"Test loggg");

    }

    @AfterTest
    public void afterTest(){
        extent.flush();
    }


}
