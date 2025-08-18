package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.Assertions;
import pageObjects.LoginPage;
import utils.DriverManager;

public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;

    public LoginSteps() {
        this.driver = DriverManager.getDriver();
        this.loginPage = new LoginPage(driver);
    }

    @Given("I launch the login page")
    public void launch_browser_with_URI() {
    	
    	  Assertions.assertTrue(driver.getCurrentUrl().contains("login"),"Launch with URL failed: URL does not contain login");
    	
    }
    @When("I login with username {string} password {string} and OTP {string}")
    public void i_login_with_username_and_password(String username, String password, String otp) {
        loginPage.login(username, password, otp);
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        Assertions.assertTrue(driver.getCurrentUrl().contains("dashboard"),
                "Login failed: URL does not contain dashboard");
    }
}
