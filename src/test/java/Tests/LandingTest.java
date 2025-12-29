package Tests;

import Listeners.ITestListener;
import Listeners.InvokedMethodListener;
import Pages.LandingPage;
import Utilities.DataUtilies;
import Utilities.LogsUtils;
import Utilities.Utilty;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;

@Listeners({InvokedMethodListener.class, ITestListener.class})

public class LandingTest {
    @BeforeMethod
    public void SetUp() throws IOException {
        setDriver(DataUtilies.getPropertValue(
                "environment",
                "Browser"
        ));
        LogsUtils.info("browser opened");
        getDriver().get(DataUtilies.getPropertValue(
                "environment",
                "Login_URL"
        ));
        LogsUtils.info("URL redirected");
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
        Utilty.login(getDriver(),
                DataUtilies.getJsonData("ValidLogin", "username"),
                DataUtilies.getJsonData("ValidLogin", "password"));
        LogsUtils.info("User : " + DataUtilies.getJsonData("ValidLogin", "username") + "logged in successfully.");
        LogsUtils.info("Redirected URL : " + DataUtilies.getPropertValue(
                "environment",
                "Home_URL"));

    }

    @Test
    public void ComaparingNoOfSelectedProductsTC() {
        new LandingPage(getDriver())
                .AddAllProductsToCart();
        Assert.assertTrue(new LandingPage(getDriver()).comparingNumberOfProductsOnCartIcon());

    }

    @Test
    public void addingRandomProductsToCartTC() {

        new LandingPage(getDriver())
                .addRandomProductToCart(3, 6);
        Assert.assertTrue(new LandingPage(getDriver()).comparingNumberOfProductsOnCartIcon());
    }

    @Test
    public void clickOnCartIconTC() {
        new LandingPage(getDriver())
                .addRandomProductToCart(3, 6)
                .clickOncartUcon();
        try {
            Assert.assertTrue(Utilty.verifyURL(getDriver(), DataUtilies.getPropertValue(
                    "environment",
                    "CartItems_URL")));
        } catch (IOException e) {
            LogsUtils.error(e.getMessage());
        }
    }

    @Test
    public void sortion() {
        LandingPage landingPage = new LandingPage(getDriver());
        landingPage.selectSortOption("Price (low to high)");
        float lowstPrice = landingPage.getFirstProductPrice();
        float highstPrice = landingPage.getLastProductPrice();

        Assert.assertTrue(lowstPrice < highstPrice,
                "Sorting failed! Cheapest: " + lowstPrice + " | Expensive: " + highstPrice);
        Assert.assertTrue(landingPage.isSortedLowToHigh(), "Products not sorted in ascending order");
    }

    @Test
    public void validatePageIdentity() {
        Assert.assertTrue(new LandingPage(getDriver()).isLandingPageValidTittle("app_logo"));
    }


    @AfterMethod
    public void quit() {
        quitDriver();

    }
}

