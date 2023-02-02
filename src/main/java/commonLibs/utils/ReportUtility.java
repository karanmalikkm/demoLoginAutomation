package commonLibs.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.File;
import java.io.IOException;

public class ReportUtility {
    ExtentHtmlReporter htmlReporter;
    ExtentReports extentReports;
    ExtentTest extentTest;

    public ReportUtility(String htmlReportFilename) {
        htmlReportFilename = htmlReportFilename.trim();
        htmlReporter = new ExtentHtmlReporter(htmlReportFilename);
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }

    public void createATestCase(String testcaseName){
        extentTest = extentReports.createTest(testcaseName);
    }

    public void addTestLog(Status status, String comment){
        extentTest.log(status,comment);
    }

    public void attachScreenshotToReport(String filepath){
        try{
            File relativeFilepath = new File(filepath);
            String absoluteFilepath = relativeFilepath.getAbsolutePath();
            extentTest.addScreenCaptureFromPath(absoluteFilepath);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void flushReport(){
        extentReports.flush();
    }
}
