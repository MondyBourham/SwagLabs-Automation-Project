package Pages;

import Utilities.LogsUtils;
import Utilities.Utilty;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    static float totalPrice = 0;
    private final WebDriver driver;
    private final By SelectedProductPrices = By.xpath("//button[.=\"Remove\"]/preceding-sibling::div[@class=\"inventory_item_price\"]");
    private final By CheckOutBtn = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductPricesinCart() {
        try {
            List<WebElement> pricesOfSelectedProducts = driver.findElements(SelectedProductPrices);
            for (int i = 0; i <= pricesOfSelectedProducts.size(); i++) {
                By elements = By.xpath("//button[.=\"Remove\"]/preceding-sibling::div[@class=\"inventory_item_price\"][" + i + "]");
                String fulltext = Utilty.getText(driver, elements);
                totalPrice += Float.parseFloat(fulltext.replace("$", ""));
            }
            LogsUtils.info("Total price is = " + totalPrice);
            return String.valueOf(totalPrice);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";

        }
    }

    public boolean comparingPrices(String price) {
        return getProductPricesinCart().equals(price);
    }

    public CheckOutPage clickOnCheckoutBtn() {
        Utilty.clickOnElement(driver, CheckOutBtn);
        return new CheckOutPage(driver);
    }
}
