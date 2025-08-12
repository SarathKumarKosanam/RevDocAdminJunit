package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.gherkin.model.Scenario;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ProcedureConfigurationPage;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.DriverManager;

public class UploadThePDFInAddNewProcedureScreenSteps {
	
	 WebDriver driver;
	    LoginPage loginPage;
	    HomePage HomePage;
	    ProcedureConfigurationPage ProcedureConfigurationPage;

	    @Given("LogIn to the Admin")
	    public void ILoginToTheAdmin() throws Exception {
	        ConfigReader.loadProperties();
	        driver = DriverFactory.initDriver();
	        driver.get(ConfigReader.get("baseUrl"));
	        loginPage = new LoginPage(driver);
	        loginPage.login(ConfigReader.get("username"),ConfigReader.get("password"),ConfigReader.get("OTP"));
	    }
	    
	   @When("I should be able to navigate to the procedure configuration screen and upload PDF")
	   public void NavigatingToProcedureConfiguration() {
		   HomePage = new  HomePage(driver);
		   ProcedureConfigurationPage = new ProcedureConfigurationPage(driver);
		   HomePage.clickingOnProcedureManagementIcon();
		   HomePage.clickingOnProcedureSetup();
		   ProcedureConfigurationPage.clickingOnAddNewProcedrueButton();	  
	   }
	   @Then("I should be able to upload the PDF successfully")
	   public void pdfUpload() {
	       ProcedureConfigurationPage.clickingOnUploadPDFButton();

	       boolean isUploaded = ProcedureConfigurationPage.isPDFUploadedSuccessfully();

	       // 🔥 Force failure so TestNG recognizes it
	       if (!isUploaded) {
	           throw new AssertionError("PDF Upload verification failed.");
	       }
	   }
}