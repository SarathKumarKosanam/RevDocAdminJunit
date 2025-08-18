package utils;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

    private WebDriver driver;
    private static final int DEFAULT_TIMEOUT = 10; // default 10 seconds

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
    }

    /** Wait for element to be visible (with default timeout) */
    public WebElement waitForVisibility(WebElement element) {
        return waitForVisibility(element, DEFAULT_TIMEOUT);
    }

    /** Wait for element to be visible (custom timeout) */
    public WebElement waitForVisibility(WebElement element, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOf(element));
    }

    /** Wait for element to be clickable (default timeout) */
    public WebElement waitForClickability(WebElement element) {
        return waitForClickability(element, DEFAULT_TIMEOUT);
    }

    /** Wait for element to be clickable (custom timeout) */
    public WebElement waitForClickability(WebElement element, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    /** Wait for element to be present in DOM by locator (optional convenience method) */
    public WebElement waitForPresence(By locator, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public WebElement waitForElementVisible(WebElement element) {
        return waitForVisibility(element, DEFAULT_TIMEOUT);
    }
}
