package Pages;

import Utilities.DataUtilies;
import Utilities.LogsUtils;
import Utilities.Utilty;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Objects;

import static Utilities.Utilty.generalWait;

public class LoginPage {
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginBtn = By.id("login-button");
    private final By inValidLoginErrorMSG = By.xpath("//h3[contains(text(),'Epic sadface')]");
    private final By usernameErrorIcon = By.cssSelector("input[data-test='username'] + svg[data-icon='times-circle']");
    private final By passwordErrorIcon = By.cssSelector("input[data-test='password'] + svg[data-icon='times-circle']");
    private final By loginLogo = By.className("login_logo");
    private WebDriver driver;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public static boolean verifyLogo(WebDriver driver, By logoLocator, String expectedLogoTextKey) {
        try {
            WebElement logo = generalWait(driver)
                    .until(ExpectedConditions.visibilityOfElementLocated(logoLocator));

            if (!logo.isDisplayed()) {
                LogsUtils.warn("⚠️ Logo is not displayed");
                return false;
            }

            String expectedText = "Swag Labs";
            String actualText = logo.getText().trim();

            LogsUtils.info("🔍 Verifying Logo | Expected: '" + expectedText + "' | Actual: '" + actualText + "'");

            return actualText.equals(expectedText);

        } catch (Exception e) {
            LogsUtils.error("❌ Logo verification failed: " + e.getMessage());
            return false;
        }
    }

    public LoginPage EnterUsername(String usernameText) {
        Utilty.sendData(driver, username, usernameText);
        return this;
    }

    public LoginPage EnterPassword(String passwordText) {
        Utilty.sendData(driver, password, passwordText);
        return this;
    }

    public LandingPage ClickLoginButton() {
        Utilty.clickOnElement(driver, loginBtn);
        return new LandingPage(driver);
    }

    public boolean assertLoginUrl(String expectedValue) {
        return driver.getCurrentUrl().equals(expectedValue);

    }

    public boolean invalidLoginErrorMsg(String fieldName) {
        try {
            generalWait(driver)
                    .until(ExpectedConditions.visibilityOfElementLocated(inValidLoginErrorMSG));

            String actualErrorMsg = Utilty.findWebElement(driver, inValidLoginErrorMSG).getText();
            LogsUtils.info("✅ Actual Error Message: " + actualErrorMsg);

            String expectedErrorMsg = DataUtilies.getJsonData("InvalidLogin", fieldName);
            LogsUtils.info("✅ Expected Error Message: " + expectedErrorMsg);

            return expectedErrorMsg.equals(actualErrorMsg);

        } catch (Exception e) {
            LogsUtils.error("❌ Failed to validate error message: " + e.getMessage());
            return false;
        }
    }

    public boolean errorIconVisiblty() {
        try {
            boolean isUsernameIconVisible = Objects.requireNonNull(generalWait(driver)
                            .until(ExpectedConditions.visibilityOfElementLocated(usernameErrorIcon)))
                    .isDisplayed();
            boolean isPasswordIconVisible = Objects.requireNonNull(generalWait(driver)
                            .until(ExpectedConditions.visibilityOfElementLocated(passwordErrorIcon)))
                    .isDisplayed();
            if (isUsernameIconVisible && isPasswordIconVisible) {
                LogsUtils.info("✅ Both error icons (username & password) are visible");
                return true;
            } else {
                LogsUtils.warn("❌ One or both error icons are NOT visible");
                return false;
            }
        } catch (Exception e) {
            LogsUtils.error("Error checking icons visibility: " + e.getMessage());
            return false;
        }


    }

    public boolean isLoginPageValidTittle(String logoLocator) {
        return Utilty.verifyLogoText(driver, "Swag Labs", logoLocator);
    }
}