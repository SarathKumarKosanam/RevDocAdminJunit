package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class HomePage {

    WebDriver driver;
    WaitUtils wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver); // create wait helper
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[contains(@class, 'ant-spin-dot ant-spin-dot-spin') ]")
    WebElement HomePageLoadingIcon;

    @FindBy(xpath = "//div//span[text()='Procedure Mgmt.']")
    WebElement ProcedureManagementIcon;

    @FindBy(xpath = "//div[span[text()='Procedure Setup']]")
    WebElement ProcedureSetup;

    @FindBy(xpath = "//img[contains(@src, 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACsAAAAr')]")
    WebElement GroupMembershipIcon;

    @FindBy(xpath = "//button//div[text()= '+ Add New']")
    WebElement AddNewButton;

    public void clickingOnProcedureManagementIcon() {
        wait.waitForClickability(ProcedureManagementIcon, 10).click();
    }

    public void clickingOnProcedureSetup() {
        wait.waitForClickability(ProcedureSetup, 10).click();
    }

    public void clickingOnGroupMembership() {
        wait.waitForClickability(GroupMembershipIcon, 10).click();
    }

    public void clickingOnAddNewButton() {
        wait.waitForClickability(AddNewButton, 10).click();
    }

    public String homePageLoading() {
        return wait.waitForVisibility(HomePageLoadingIcon, 10).getText();
    }
}
