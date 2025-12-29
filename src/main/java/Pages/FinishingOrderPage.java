package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Utilities.Utilty.findWebElement;

public class FinishingOrderPage {
    private final WebDriver driver;
    private final By thanksMSG = By.tagName("h2");

    public FinishingOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean checkVisabiltyOfThanksMsg() {
        return findWebElement(driver, thanksMSG).isDisplayed();
    }
}
