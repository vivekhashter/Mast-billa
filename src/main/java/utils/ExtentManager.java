package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager implements ITestListener {

//    public static ExtentHtmlReporter html;

    public static ExtentXReporter xReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    String repName;
    public void onStart(ITestContext testContext){
        String timeStamp = new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss").format(new Date());
        repName = "TestReport_" + timeStamp + ".html";
        xReporter = new ExtentXReporter(System.getProperty("user.dir") + "//Reports//" + repName);
        xReporter.config().setReportName("Reports");


        extent = new ExtentReports();
        extent.attachReporter(xReporter);
        extent.setSystemInfo("Nagp", "2023");
        extent.setSystemInfo("Author", "Praver");

    }

    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName());
        test.createNode(result.getName());
        test.log(Status.PASS, " Test Passed");

    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName());
        test.createNode(result.getName());
        test.log(Status.FAIL, " Test Failed");
        test.log(Status.FAIL, result.getThrowable().getMessage());
    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());
        test.createNode(result.getName());
        test.log(Status.SKIP, " Test Skipped");
        test.log(Status.SKIP, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext testContext){
        extent.flush();
    }
}
