package hooks;

import io.cucumber.java.*;
import org.openqa.selenium.*;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.DriverManager;
import utils.ExtentManager;
import utils.ScreenshotUtil;
import com.aventstack.extentreports.ExtentTest;

import java.util.Base64;

public class Hooks {

    private WebDriver driver;
    private static ExtentTest currentTest;

    @Before
    public void setUp(Scenario scenario) {
        // Initialize driver and store in DriverManager
        driver = DriverFactory.initializeDriver();
        DriverManager.setDriver(driver);

        // Navigate to base URL
        String baseUrl = ConfigReader.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            driver.get(baseUrl);
        } else {
            throw new RuntimeException("Base URL is not set in config.properties!");
        }

        // Start a new Extent test for this scenario
        currentTest = ExtentManager.getExtentReports().createTest(scenario.getName() + " " + scenario.getSourceTagNames());
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                // Capture screenshot once
                byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                String screenshotBase64 = Base64.getEncoder().encodeToString(screenshotBytes);

                // Attach to Cucumber report
                scenario.attach(screenshotBytes, "image/png", "Failed Step Screenshot");

                // Attach to Extent report
                currentTest.fail("Scenario failed: " + scenario.getName())
                        .addScreenCaptureFromBase64String(screenshotBase64);
            } else {
                currentTest.pass("Scenario passed successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Quit driver after each scenario
            DriverFactory.quitDriver();
        }
    }

    // Flush Extent report once after all scenarios
    @AfterAll
    public static void afterAll() {
        ExtentManager.getExtentReports().flush();
    }
}
