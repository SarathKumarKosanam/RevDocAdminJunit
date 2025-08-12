package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pageObjects.LoginPage;
import utils.DriverFactory;
import utils.ConfigReader;
import org.junit.jupiter.api.Assertions;

public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;

    @Given("I launch the login page")
    public void i_launch_the_login_page() throws Exception {
        ConfigReader.loadProperties(); // From your utility class
        driver = DriverFactory.initDriver(); // Launch Chrome
        driver.get(ConfigReader.get("baseUrl"));
        loginPage = new LoginPage(driver);
    }

    @When("I login with username {string} password {string} and OTP {string}")
    public void i_login_with_username_and_password(String username, String password, String otp) {
        loginPage.login(username, password, otp);
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        // Dummy assertion (adjust to real check like dashboard element, profile name, etc.)
        Assertions.assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }
}
