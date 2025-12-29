package Tests;


import Listeners.ITestListener;
import Listeners.InvokedMethodListener;
import Pages.LandingPage;
import Pages.LoginPage;
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
public class LoginTest {
    @BeforeMethod
    public void SetUp() throws IOException {
        setDriver(DataUtilies.getPropertValue(
                "environment",
                "Browser"
        ));
        LogsUtils.info("Edge Browser is opened");
        getDriver().get(DataUtilies.getPropertValue(
                "environment",
                "Login_URL"
        ));
        LogsUtils.info("Page is redirected to URL");
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void validateLogin() throws IOException {

        Utilty.login(getDriver(),
                DataUtilies.getJsonData("ValidLogin", "username"),
                DataUtilies.getJsonData("ValidLogin", "password"));
        String expectedURL = DataUtilies.getPropertValue(
                "environment"
                , "Home_URL");
        Assert.assertTrue(new LandingPage(getDriver()).isOnHomePage(expectedURL), "Expected: " + expectedURL + " | Actual: " + getDriver().getCurrentUrl());

    }

    @Test
    public void loginInValidUsernameWithValidPassword() throws IOException {
        new LoginPage(getDriver())
                .EnterUsername(DataUtilies.getJsonData(
                        "InvalidLogin", "username"))
                .EnterPassword(DataUtilies.getJsonData(
                        "ValidLogin", "password"))
                .ClickLoginButton();
        Assert.assertTrue(new LoginPage(getDriver()).assertLoginUrl(DataUtilies.
                getPropertValue(
                        "environment", "Login_URL")));
        Assert.assertTrue(new LoginPage(getDriver()).invalidLoginErrorMsg("errorMSG"));

        Assert.assertTrue(new LoginPage(getDriver()).errorIconVisiblty());
    }

    @Test
    public void loginInValidPasswordWithValidUsername() throws IOException {
        new LoginPage(getDriver())
                .EnterUsername(DataUtilies.getJsonData(
                        "ValidLogin", "username"))
                .EnterPassword(DataUtilies.getJsonData(
                        "InvalidLogin", "password"))
                .ClickLoginButton();
        Assert.assertTrue(new LoginPage(getDriver()).assertLoginUrl(DataUtilies.
                getPropertValue(
                        "environment", "Login_URL")));
        Assert.assertTrue(new LoginPage(getDriver()).invalidLoginErrorMsg("errorMSG"));

        Assert.assertTrue(new LoginPage(getDriver()).errorIconVisiblty());
    }

    @Test
    public void withEmptyUsernameAndPassword() throws IOException {
        new LoginPage(getDriver())
                .EnterUsername("")
                .EnterPassword("")
                .ClickLoginButton();
        Assert.assertTrue(new LoginPage(getDriver()).assertLoginUrl(DataUtilies.
                getPropertValue(
                        "environment", "Login_URL")));
        Assert.assertTrue(new LoginPage(getDriver()).invalidLoginErrorMsg("emptyUserMSG"));
        Assert.assertTrue(new LoginPage(getDriver()).errorIconVisiblty());
    }

    @Test
    public void withEmptyUsername() throws IOException {
        new LoginPage(getDriver())
                .EnterUsername("")
                .EnterPassword(DataUtilies.getJsonData("ValidLogin", "password"))
                .ClickLoginButton();
        Assert.assertTrue(new LoginPage(getDriver()).assertLoginUrl(DataUtilies.
                getPropertValue(
                        "environment", "Login_URL")));
        Assert.assertTrue(new LoginPage(getDriver()).invalidLoginErrorMsg("emptyUserMSG"));
        Assert.assertTrue(new LoginPage(getDriver()).errorIconVisiblty());
    }

    @Test
    public void withEmptyPassword() throws IOException {
        new LoginPage(getDriver())
                .EnterUsername(DataUtilies.getJsonData("ValidLogin", "username"))
                .EnterPassword("")
                .ClickLoginButton();
        Assert.assertTrue(new LoginPage(getDriver()).assertLoginUrl(DataUtilies.
                getPropertValue(
                        "environment", "Login_URL")));
        Assert.assertTrue(new LoginPage(getDriver()).invalidLoginErrorMsg("emptyPasswordMSG"));
        Assert.assertTrue(new LoginPage(getDriver()).errorIconVisiblty());
    }

    @Test
    public void validatePageIdentity() {
        Assert.assertTrue(new LoginPage(getDriver()).isLoginPageValidTittle("login_logo"));
    }

    @AfterMethod
    public void quit() {

        quitDriver();
    }

}
