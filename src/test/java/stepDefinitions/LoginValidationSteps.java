package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.*;
import pageObjects.LoginPage;
import utils.DriverManager;
import utils.ScreenshotUtil;
import utils.WaitUtils;

public class LoginValidationSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private WaitUtils wait;

    private void initPageObjects() {
        if (loginPage == null) {
            this.driver = DriverManager.getDriver();
            this.wait = new WaitUtils(driver);
            this.loginPage = new LoginPage(driver);
        }
    }

    @Given("I open the login screen for validation")
    public void i_launch_the_login_page() {
        initPageObjects();
        // Hooks already navigates to base URL
    }

    @When("I click login without entering any fields")
    public void click_login_without_entering_any_fields() {
        initPageObjects();
        loginPage.clickLogin();
    }

    @Then("I should see {string} message under email and password")
    public void i_should_see_required_error(String expectedMsg) {
        initPageObjects();
        assertEquals(expectedMsg, loginPage.getEmailErrorMsg());
        assertEquals(expectedMsg, loginPage.getPasswordErrorMsg());
    }

    @When("I enter invalid email {string} and click login")
    public void enter_invalid_email(String invalidEmail) {
        initPageObjects();
        loginPage.enterEmail(invalidEmail);
        loginPage.clickLogin();
    }

    @Then("I should see {string} message")
    public void i_should_see_message(String expectedMsg) {
        initPageObjects();
        assertEquals(expectedMsg, loginPage.getEmailErrorMsg());
    }

    @When("I enter email {string} and password {string} and click login")
    public void enter_email_password_and_login(String email, String password) {
        initPageObjects();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    @Then("I should see {string} message under login popup")
    public void i_should_see_login_popup_error(String expectedPopupMsg) {
        initPageObjects();
        String actualPopupMsg = wait.waitForElementVisible(loginPage.getInvalidLoginError()).getText();

        // If failed, take screenshot
        if (!actualPopupMsg.equals(expectedPopupMsg)) {
            ScreenshotUtil.takeScreenshot(driver, "login_popup_error");
        }

        assertEquals(expectedPopupMsg, actualPopupMsg);
    }
}
