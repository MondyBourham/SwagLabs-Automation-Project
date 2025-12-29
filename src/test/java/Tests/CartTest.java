package Tests;

import Listeners.ITestListener;
import Listeners.InvokedMethodListener;
import Pages.LandingPage;
import Pages.LoginPage;
import Pages.CartPage;
import Utilities.DataUtilies;
import Utilities.LogsUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;

@Listeners({InvokedMethodListener.class, ITestListener.class})

public class CartTest {
    @BeforeMethod
    public void SetUp() throws IOException {
        setDriver(DataUtilies.getPropertValue(
                "environment",
                "Browser"
        ));
        LogsUtils.info("browser opened");
        getDriver().get(DataUtilies.getPropertValue(
                "environment",
                "Home_URL"
        ));
        LogsUtils.info("URL redirected");
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

    }

    @Test
    public void comparingPricesTC() {
        String totalPrice = new LoginPage(getDriver()).
                EnterUsername(DataUtilies.getJsonData("ValidLogin",
                        "username"))
                .EnterPassword(DataUtilies.getJsonData("ValidLogin",
                        "password"))
                .ClickLoginButton()
                .addRandomProductToCart(3, 6)
                .getProductPricesOfSelectedProducts();
        new LandingPage(getDriver())
                .clickOncartUcon();
        Assert.assertTrue(new CartPage(getDriver()).comparingPrices(totalPrice));
    }

    @AfterMethod
    public void quit() {
        quitDriver();

    }
}

