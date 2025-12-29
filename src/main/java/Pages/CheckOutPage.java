package Pages;

import Utilities.Utilty;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutPage {
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By zipCode = By.id("postal-code");
    private final By continueBtn = By.id("continue");
    private final WebDriver driver;


    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
    }

    public CheckOutPage fillingInformationForm(String fName, String lName, String zCode) {
        Utilty.sendData(driver, firstName, fName);
        Utilty.sendData(driver, lastName, lName);
        Utilty.sendData(driver, zipCode, zCode);
        return this;
    }

    public OverViewPage clickOnCheckoutBtn() {
        Utilty.clickOnElement(driver, continueBtn);
        return new OverViewPage(driver);
    }


}