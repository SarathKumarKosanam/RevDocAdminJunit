package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void initDriver() {
        if (driver.get() == null) {
            String browser = System.getProperty("browser", "chrome").toLowerCase();
            boolean isCI = System.getenv("CI") != null; // Detect if running in GitHub Actions

            switch (browser) {
                case "chrome":
                default:
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();

                    if (isCI) {
                        // Headless mode for CI/CD
                        options.addArguments("--headless=new");
                        options.addArguments("--disable-gpu");
                        options.addArguments("--no-sandbox");
                        options.addArguments("--disable-dev-shm-usage");
                    }

                    driver.set(new ChromeDriver(options));
                    break;
            }
        }
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
