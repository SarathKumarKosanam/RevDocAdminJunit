package utils;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static synchronized ExtentTest startTest(String testName) {
        ExtentTest test = ExtentManager.getExtentReports().createTest(testName);
        extentTest.set(test);
        return test;
    }

    public static synchronized ExtentTest getTest() {
        return extentTest.get();
    }

    public static synchronized void endTest() {
        extentTest.remove(); // remove from ThreadLocal
    }
}
