package stepDefinitions;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.*;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ProcedureConfigurationPage;
import utils.*;

public class UploadThePDFInAddNewProcedureScreenSteps {

    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    ProcedureConfigurationPage procedureConfigPage;

    public UploadThePDFInAddNewProcedureScreenSteps() {
        this.driver = DriverManager.getDriver();   // Always fetch driver from DriverManager
        this.loginPage = new LoginPage(driver);
        this.homePage = new HomePage(driver);
        this.procedureConfigPage = new ProcedureConfigurationPage(driver);
    }

    @Given("LogIn to the Admin")
    public void loginToAdmin() {
        loginPage.login(
            ConfigReader.getProperty("username"),
            ConfigReader.getProperty("password"),
            ConfigReader.getProperty("OTP")
        );
        // Cucumber report info can be added via Scenario object in Hooks if needed
    }

    @When("I should be able to navigate to the procedure configuration screen and upload PDF")
    public void navigateToProcedureConfig() {
        homePage.clickingOnProcedureManagementIcon();
        homePage.clickingOnProcedureSetup();
        procedureConfigPage.clickAddNewProcedureButton();
    }

    @Then("I should be able to upload the PDF successfully")
    public void pdfUpload() {
        String pdfPath = ConfigReader.getProperty("pdfFilePath"); // path from config.properties
        procedureConfigPage.uploadPDF(pdfPath);

        boolean isUploaded = procedureConfigPage.isPDFUploadedSuccessfully();

        if (!isUploaded) {
            String screenshotPath = ScreenshotUtil.takeScreenshot(driver, "PDF_Upload_Failed");
            throw new AssertionError("PDF Upload verification failed. Screenshot: " + screenshotPath);
        }
    }
}
