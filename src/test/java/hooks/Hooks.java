package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;
import utils.ExtentTestManager;
import utils.ScreenshotUtil;
import com.aventstack.extentreports.Status;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Hooks {
    WebDriver driver;

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("🔧 Starting Scenario: " + scenario.getName());
        ExtentTestManager.startTest(scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        driver = DriverFactory.getDriver();

        if (scenario.isFailed()) {
            ExtentTestManager.getTest().log(Status.FAIL, "❌ Test Failed: " + scenario.getName());

            String screenshotPath = ScreenshotUtil.takeScreenshot(driver, scenario.getName());
            try {
                byte[] screenshotBytes = Files.readAllBytes(Paths.get(screenshotPath));
                scenario.attach(screenshotBytes, "image/png", scenario.getName());

                ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ExtentTestManager.getTest().log(Status.PASS, "✅ Test Passed: " + scenario.getName());
        }

        ExtentTestManager.endTest();
        DriverFactory.quitDriver();
    }
}
