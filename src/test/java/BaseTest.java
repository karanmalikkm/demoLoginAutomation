import com.aventstack.extentreports.Status;
import commonLibs.implementation.CommonDriver;
import commonLibs.utils.ConfigReader;
import commonLibs.utils.ReportUtility;
import commonLibs.utils.ScreenshotUtility;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
    CommonDriver cmnDriver;
    String url;
    WebDriver driver;
    ConfigReader configReader;
    ReportUtility reportUtility;
    String reportFilename;
    ScreenshotUtility screenshotUtility;
    String screenshotFilename;

    @BeforeSuite
    public void preSetup(){
        configReader = new ConfigReader();
        reportFilename = "reports/report_" + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()) + ".html";
        reportUtility = new ReportUtility(reportFilename);
    }

    @BeforeClass
    public void setup(){
        cmnDriver = new CommonDriver(configReader.getProperty("browserType"));
        driver = cmnDriver.getDriver();
        url = configReader.getProperty("url");
        cmnDriver.navigateToUrl(url);
    }

    @AfterMethod
    public void postTestAction(ITestResult result){
        String testcaseName = result.getName();
        String executionTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        if(result.getStatus() == ITestResult.FAILURE){
            reportUtility.addTestLog(Status.FAIL, "One or more test steps failed");
            screenshotUtility = new ScreenshotUtility(driver);
            screenshotFilename = "sc_" + testcaseName;
            screenshotUtility.takeScreenshot(screenshotFilename);
            String screenshotPath = screenshotUtility.getScreenshotPath();
            reportUtility.attachScreenshotToReport(screenshotPath);
        }
        else if(result.getStatus() == ITestResult.SUCCESS){
            reportUtility.addTestLog(Status.PASS, "Test Case passed: "+testcaseName);
        }
    }

    @AfterClass
    public void tearDown(){
        cmnDriver.closeBrowser();
    }

    @AfterSuite
    public void postTearDown(){
        reportUtility.flushReport();
    }
}
