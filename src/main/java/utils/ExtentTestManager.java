package utils;

import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    private static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    private static ExtentTest test;

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized void endTest() {
        ExtentManager.getInstance().flush();
    }

    public static synchronized ExtentTest startTest(String testName) {
        test = ExtentManager.getInstance().createTest(testName);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }
}
