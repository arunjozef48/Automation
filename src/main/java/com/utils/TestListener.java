package com.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestListener extends TestListenerAdapter {

    String filePath = Constants.SCREENSHOTS_FILE_PATH;

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("*****" + result.getName() + " test has FAILED *****");
        String methodName = result.getName().toString().trim();
        try {
            takeScreenShot(methodName);
            sendTestStatus(result.getName().toString().trim(),"FAILED","test has FAILED","test description FAIL");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void sendTestStatus(String testId, String status, String message, String description) throws IOException {
        String url = String.format("http://localhost:3000/testframework/teststatus?id=%s&status=%s&message=%s&description=%s", testId, status, message, description);

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
//        int responseCode = con.getResponseCode();
    }


    public void takeScreenShot(String methodName) throws Throwable {
        WebDriver driver = DriverProvider.getDriverInstance();
        try {
            File file = new File(filePath + "ScreenShots_for_" + methodName);
            if (!file.exists()) {
                file.mkdir();
                String filePath1 = filePath + "ScreenShots_for_" + methodName;
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File(filePath1 + "\\" + methodName + ".png"));
            }

        } catch (Exception e) {
            System.out.println("An exception occured while taking screenshot " + e.getCause());
            Reporter.log("Couldn't create screenshot");
            Reporter.log(e.getMessage());
        }
    }

    public void onFinish(ITestContext context) {
    }

    public void onTestStart(ITestResult result) {
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("*****" + result.getName() + " test has PASSED *****");
        String methodName = result.getName().toString().trim();
        try {
            takeScreenShot(methodName);
            sendTestStatus(result.getName().toString().trim(),"PASSED","test has PASSED","test description PASS");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onStart(ITestContext context) {
    }
}
