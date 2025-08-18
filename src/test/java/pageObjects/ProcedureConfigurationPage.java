package pageObjects;

import java.nio.file.Paths;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

public class ProcedureConfigurationPage {

    WebDriver driver;
    WebDriverWait wait;

    public ProcedureConfigurationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[span[text()='+ Add New Procedure']]")
    WebElement addNewProcedureButton;

    @FindBy(id = "procedurePdf")
    WebElement uploadPDFInput;

    public void clickAddNewProcedureButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addNewProcedureButton)).click();
    }

    public void uploadPDF(String relativePath) {
        String absolutePath = Paths.get(relativePath).toAbsolutePath().toString();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("procedurePdf")));
        uploadPDFInput.sendKeys(absolutePath);
    }

    public boolean isPDFUploadedSuccessfully() {
        WebElement uploadedPDF = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//span[text()=' File selected']")
        ));
        return uploadedPDF.isDisplayed();
    }
}
