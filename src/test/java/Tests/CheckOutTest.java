package Tests;

import Listeners.ITestListener;
import Listeners.InvokedMethodListener;
import Pages.LoginPage;
import Pages.LandingPage;
import Pages.CartPage;
import Pages.CheckOutPage;
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

public class CheckOutTest {
    private final String firstName = DataUtilies.getJsonData("usersData", "first-name");
    private final String lastName = DataUtilies.getJsonData("usersData", "last-name");
    private final String zipCode = DataUtilies.getJsonData("usersData", "zip-code");

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
    public void CheckoutStepOneTC() throws IOException {
        //TODO :Login step
        new LoginPage(getDriver()).
                EnterUsername(DataUtilies.getJsonData("ValidLogin",
                        "username"))
                .EnterPassword(DataUtilies.getJsonData("ValidLogin",
                        "password"))
                .ClickLoginButton();
        //TODO :Adding product to curt step
        new LandingPage(getDriver())
                .addRandomProductToCart(2, 6)
                .clickOncartUcon();
        //TODO :Go to CheckOut step
        new CartPage(getDriver()).clickOnCheckoutBtn();
        //TODO :Filling information at checkout step
        new CheckOutPage(getDriver()).fillingInformationForm(firstName, lastName, zipCode)
                .clickOnCheckoutBtn();


        Assert.assertTrue(Utilty.verifyURL(getDriver(), DataUtilies.getPropertValue("environment", "CheckOut_URL")));
    }

    @AfterMethod
    public void quit() {
        quitDriver();

    }
}

