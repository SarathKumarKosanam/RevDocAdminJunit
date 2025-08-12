package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class LoginPage {

    WebDriver driver;
    WaitUtils wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver); // ✅ Use wait helper
        PageFactory.initElements(driver, this);
    }

    // ------------------ Locators ------------------
    @FindBy(id = "email")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginButton;

    @FindBy(id = "user_input_code")
    WebElement OTPField;

    @FindBy(xpath = "//button[@class='ant-btn css-5uvb3z ant-btn-default ant-btn-color-default ant-btn-variant-outlined revdocButton filled']")
    WebElement verifyOTPButton;

    @FindBy(id = "email_help")
    WebElement emailError;

    @FindBy(id = "password_help")
    WebElement passwordError;

    @FindBy(xpath = "//div[contains(text(),'email and password combination')]")
    WebElement loginPopupError;

    // ------------------ Actions ------------------

    public void login(String uname, String pwd, String otp) {
        wait.waitForVisibility(username, 10).sendKeys(uname);
        wait.waitForVisibility(password, 10).sendKeys(pwd);

        wait.waitForClickability(loginButton, 10).click();

        // Wait until OTP field is visible
        wait.waitForVisibility(OTPField, 10).sendKeys(otp);

        wait.waitForClickability(verifyOTPButton, 10).click();
    }

    public void clickLogin() {
        wait.waitForClickability(loginButton, 10).click();
    }

    public void enterEmail(String email) {
        wait.waitForVisibility(username, 10).sendKeys(Keys.CONTROL + "a");
        username.sendKeys(Keys.DELETE);
        username.sendKeys(email);
    }

    public void enterPassword(String pwd) {
        wait.waitForVisibility(password, 10).sendKeys(Keys.CONTROL + "a");
        password.sendKeys(Keys.DELETE);
        password.sendKeys(pwd);
    }

    public String getEmailErrorMsg() {
        return wait.waitForVisibility(emailError, 10).getText();
    }

    public String getPasswordErrorMsg() {
        return wait.waitForVisibility(passwordError, 10).getText();
    }

    public WebElement getInvalidLoginError() {
        return wait.waitForVisibility(loginPopupError, 10);
    }
}
