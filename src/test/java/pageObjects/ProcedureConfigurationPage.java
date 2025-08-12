package pageObjects;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProcedureConfigurationPage {

    WebDriver driver;
    WebDriverWait wait;

    public ProcedureConfigurationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 sec wait
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[span[text()='+ Add New Procedure']]") 
    WebElement AddNewProcedureButton;

    @FindBy(xpath = "//input[@id = \"procedurePdf\"]") 
    WebElement UploadPDFButton;

    /**
     * Clicks on Add New Procedure Button with wait
     */
    public void clickingOnAddNewProcedrueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(AddNewProcedureButton)).click();
    }

    /**
     * Uploads PDF file using sendKeys
     */
    public void clickingOnUploadPDFButton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("procedurePdf")));
        UploadPDFButton.sendKeys("C:\\Users\\LENOVO\\Desktop\\Eclipse\\Input Test Files\\Test PDF file.pdf");
    }

    /**
     * Checks if PDF was uploaded successfully
     */
    public boolean isPDFUploadedSuccessfully() {
        WebElement uploadedPDF = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//span[text() = \" File selected\"]")
        ));
        return uploadedPDF.isDisplayed();
    }
}
