package Pages;

import Utilities.LogsUtils;
import Utilities.Utilty;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class LandingPage {
    static float totalPrice = 0;
    private static List<WebElement> allProducts;
    private static List<WebElement> SelectedProducts;
    private static String currentURL;
    private static List<WebElement> prices;
    private final WebDriver driver;
    private final By AddToCartBtnForAllProducts = By.xpath("//button[@class]");
    private final By TotalProductsOnCartIcon = By.className("shopping_cart_badge");
    private final By NoOfSelectedProducts = By.xpath("//button[.='Remove']");
    private final By CartIcon = By.className("shopping_cart_link");
    private final By SelectedProductPrices = By.xpath("//button[.=\"Remove\"]/preceding-sibling::div[@class=\"inventory_item_price\"]");
    private final By sortDropDown = By.className("product_sort_container");
    private final By inventroyItemPrice = By.className("inventory_item_price");

    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOnHomePage(String expectedURL) {
        currentURL = driver.getCurrentUrl().trim().toLowerCase();
        LogsUtils.info("Current URL: " + currentURL);
        expectedURL = expectedURL.trim().toLowerCase();
        return currentURL.equals(expectedURL);
    }

    public LandingPage AddAllProductsToCart() {
        allProducts = driver.findElements(AddToCartBtnForAllProducts);
        LogsUtils.info("NO of all product is = " + allProducts.size());//all elements
        for (int i = 1; i < allProducts.size(); i++) {
            By AddToCartBtnForAllProducts = By.xpath("(//button[@class])[" + i + "]");// dymn dynamic locator b7oto fe function
            Utilty.clickOnElement(driver, AddToCartBtnForAllProducts);
        }
        return this;
    }

    // if theres no product there's no such element will be sent so we neeed to add try and catch to throw exp
    public String getNoOfProductsOnCartIcon() {
        try {
            LogsUtils.info("No Of prodcucts on cart is = " + Utilty.getText(driver, TotalProductsOnCartIcon));
            return Utilty.getText(driver, TotalProductsOnCartIcon);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }
    }

    public String getNoOfSelectedProducts() {
        try {
            SelectedProducts = driver.findElements(NoOfSelectedProducts);
            LogsUtils.info("No of selected products is = " + SelectedProducts.size());
            return String.valueOf(SelectedProducts.size());
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }

    }

    public boolean comparingNumberOfProductsOnCartIcon() {
        return getNoOfProductsOnCartIcon().equals(getNoOfSelectedProducts());
    }

    public LandingPage addRandomProductToCart(int noOfProductsNeeded, int totalNoOfProducts) {
        Set<Integer> randomNO = Utilty.generateUniqeNo(noOfProductsNeeded, totalNoOfProducts);
        for (int random : randomNO) {
            LogsUtils.info("Adding random product to cart is = " + random);
            By AddToCartBtnForAllProducts = By.xpath("(//button[@class])[" + random + "]");// dymn dynamic locator b7oto fe function
            Utilty.clickOnElement(driver, AddToCartBtnForAllProducts);
        }
        return this;
    }

    public CartPage clickOncartUcon() {
        Utilty.clickOnElement(driver, CartIcon);
        return new CartPage(driver);
    }


    public String getProductPricesOfSelectedProducts() {
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

    public LandingPage selectSortOption(String option) {
        Utilty.selectFromDropDown(driver, sortDropDown, option);
        return this;

    }

    public float getFirstProductPrice() {
        try {
            prices = driver.findElements(inventroyItemPrice);
            String fproduct = prices.get(0).getText().replace("$", "").trim();
            float firstPrice = Float.parseFloat(fproduct);
            LogsUtils.info("💰 First product price: $" + firstPrice);
            return firstPrice;
        } catch (Exception e) {
            LogsUtils.error("Failed to get first product price: " + e.getMessage());
            return 0.0f;
        }
    }

    public float getLastProductPrice() {
        try {
            prices = driver.findElements(inventroyItemPrice);
            String lproduct = prices.get(prices.size() - 1).getText().replace("$", "").trim();
            float lastPrice = Float.parseFloat(lproduct);
            LogsUtils.info("💰 last product price: $" + lastPrice);
            return lastPrice;
        } catch (Exception e) {
            LogsUtils.error("Failed to get last product price: " + e.getMessage());
            return 0.0f;
        }
    }

    public boolean isSortedLowToHigh() {
        prices = driver.findElements(inventroyItemPrice);
        float prevPrice = 0.0f;

        for (WebElement priceEl : prices) {
            float currentPrice = Float.parseFloat(
                    priceEl.getText().replace("$", "").trim()
            );

            if (currentPrice < prevPrice) {
                LogsUtils.warn("❌ Sorting failed at price: $" + currentPrice);
                return false;
            }
            prevPrice = currentPrice;
        }
        LogsUtils.info("✅ Products sorted correctly from low to high");
        return true;
    }

    public boolean isLandingPageValidTittle(String logoLocator) {
        return Utilty.verifyLogoText(driver, "Swag Labs", logoLocator);
    }

}


