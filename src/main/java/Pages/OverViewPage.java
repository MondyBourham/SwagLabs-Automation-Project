package Pages;

import Utilities.LogsUtils;
import Utilities.Utilty;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverViewPage {
    private final WebDriver driver;
    private final By subTotal = By.className("summary_subtotal_label");
    private final By tax = By.className("summary_tax_label");
    private final By total = By.xpath("//div[contains(@class,'summary_total_label')]");
    private final By finishBtn = By.id("finish");

    public OverViewPage(WebDriver driver) {
        this.driver = driver;
    }

    public Float getSubTotal() {
        return Float.parseFloat(Utilty.getText(driver, subTotal).replace("Item total: $", ""));
    }

    public Float getTax() {
        return Float.parseFloat(Utilty.getText(driver, tax).replace("Tax: $", ""));
    }

    public Float getTotal() {
        LogsUtils.info("Total price before TAX is: " + getSubTotal());

        return Float.parseFloat(Utilty.getText(driver, total).replace("Total: $", ""));
    }

    public String calcTotalPrice() {
        LogsUtils.info("Total price is: " + (getSubTotal() + getTax()));
        return String.valueOf(getSubTotal() + getTax());
    }

    public boolean comparingPrices() {

        return calcTotalPrice().equals(String.valueOf(getTotal()));
    }

    public FinishingOrderPage clickOnFinishBtn() {
        Utilty.clickOnElement(driver, finishBtn);
        return new FinishingOrderPage(driver);
    }

}
