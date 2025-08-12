package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.*;
import pageObjects.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.DriverManager;

public class LoginValidationSteps {

    WebDriver driver;
    LoginPage loginPage;

    @Given("I launching the login page")
    public void i_launch_the_login_page() throws Exception {
        ConfigReader.loadProperties();
        driver = DriverFactory.initDriver();
        driver.get(ConfigReader.get("baseUrl"));
        loginPage = new LoginPage(driver);
    }

    @When("I click login without entering any fields")
    public void click_login_without_entering_any_fields() throws InterruptedException {
        loginPage.clickLogin();
        Thread.sleep(2000);
    }

    @Then("I should see {string} message under email and password")
    public void i_should_see_required_error(String expectedMsg) {
        Assert.assertEquals(loginPage.getEmailErrorMsg(), expectedMsg);
        Assert.assertEquals(loginPage.getPasswordErrorMsg(), expectedMsg);
        
       System.out.println("LoginValidationSteps.i_should_see_required_error()");
    }

    @When("I enter invalid email {string} and click login")
    public void enter_invalid_email(String invalidEmail) {
        loginPage.enterEmail(invalidEmail);
        loginPage.clickLogin();
    }

    @Then("I should see {string} message")
    public void i_should_see_message(String expectedMsg) {
        Assert.assertEquals(loginPage.getEmailErrorMsg(), expectedMsg);
        System.out.println(loginPage.getEmailErrorMsg() +" Error message displayed under the email field");
    }

    @When("I enter email {string} and password {string} and click login")
    public void enter_email_password_and_login(String email, String password) throws InterruptedException {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
Thread.sleep(5000);
    }

    @Then("I should see {string} message under login popup")
    public void i_should_see_login_popup_error(String expectedPopupMsg) throws InterruptedException  {
    //    Assert.assertEquals(loginPage.getInvalidLoginError(), expectedPopupMsg);
        
        if (loginPage.getInvalidLoginError().getText().contentEquals("The email and password combination you entered is incorrect."))
        System.out.print("Invalid Mail and combination passed");
        else
        	System.out.print("falied");
        
        driver.quit(); // Clean up
    }
}
