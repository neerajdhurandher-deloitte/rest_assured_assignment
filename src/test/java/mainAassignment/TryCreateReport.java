package mainAassignment;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.*;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TryCreateReport {

    ExtentSparkReporter extentSparkReporter;
    ExtentReports extent;
    ExtentTest extentTest;


    @BeforeTest
    public void startReport() {

        extentSparkReporter = new ExtentSparkReporter("src/test/java/mainAassignment/Reports/TestReport.html");

        extentSparkReporter.config().setEncoding("utf-8");
        extentSparkReporter.config().setDocumentTitle("Main Assignment Report");
        extentSparkReporter.config().setReportName("Test Reports");
        extentSparkReporter.config().setTheme(Theme.DARK);


        extent = new ExtentReports();
        extent.setSystemInfo("Organization","Hashedin By Deloitte");
        extent.setSystemInfo("Created by ","Neeraj Dhurandher");
        extent.attachReporter(extentSparkReporter);

    }

    @AfterTest
    public void afterTest(){
        extent.flush();
    }

    @Test
    public void test(){
       extentTest = extent.createTest("test");
       extentTest.log(Status.PASS,"Test success");
    }

}
