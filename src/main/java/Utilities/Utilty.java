package Utilities;

import Pages.LandingPage;
import Pages.LoginPage;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;

public class Utilty {

    private static final String screenshootPath = "Test-Outputs/ScreenShoots/";
    WebDriver driver;
    WebDriverWait wait;

    public static void clickOnElement(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
        LogsUtils.info("Element clicked: " + locator);
    }

    public static void sendData(WebDriver driver, By locator, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
        LogsUtils.info(text + " has been written to " + locator);
    }

    public static String getText(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.visibilityOfElementLocated(locator));
        LogsUtils.info(driver.findElement(locator).getText() + " readed from " + locator.toString());
        return driver.findElement(locator).getText();

    }

    public static WebDriverWait generalWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public static WebElement findWebElement(WebDriver driver, By locator) {
        try {
            LogsUtils.info(locator.toString() + " found .");
            return driver.findElement(locator);
        } catch (NoSuchElementException e) {
            LogsUtils.error("Element not found: " + locator.toString());
            throw e;

        }
    }

    public static void scrolling(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);",
                        findWebElement(driver, locator));
        LogsUtils.info(" scrolling to " + locator.toString() + " successfully.");
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat("dd-MM-yyyy -- HH-mm-ssa").format(new Date());
    }

    public static void takeScreenShoot(WebDriver driver, String screenName) {

        try {
            //take snap
            File ss = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            LogsUtils.info("Taking screenshot of: " + screenName);
            //save snap to file if needed
            File screenshotDestination = new File(screenshootPath + screenName + "-" + getTimeStamp() + ".png");
            FileUtils.copyFile(ss, screenshotDestination);
            LogsUtils.info("Screenshot saved at: " + screenshotDestination.getAbsolutePath());
            //attach snap to allure
            Allure.addAttachment(screenName, Files.newInputStream(Path.of(screenshotDestination.getPath())));
            LogsUtils.info("Screenshot attached to Allure successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void selectFromDropDown(WebDriver driver, By locator, String option) {
        new Select(driver.findElement(locator)).selectByVisibleText(option);
    }

    public static int generateRandomNumber(int upperBound) {
        return new Random().nextInt(upperBound) + 1;
    }

    //set >>> unique >> 1,2,3,4,3 >condtion
    public static Set<Integer> generateUniqeNo(int noOfProductsNeeded, int totalNoOfProducts) {
        Set<Integer> genetaedNO = new HashSet<>();
        while (genetaedNO.size() < noOfProductsNeeded) {
            int randomNO = generateRandomNumber(totalNoOfProducts);
            genetaedNO.add(randomNO);
        }

        return genetaedNO;
    }

    public static boolean verifyURL(WebDriver driver, String expectedURL) {
        try {
            generalWait(driver).until(ExpectedConditions.urlToBe(expectedURL));
            LogsUtils.info("Verifying URL: " + driver.getCurrentUrl() + " matches " + "expectedURL: " + expectedURL);

        } catch (Exception e) {
            LogsUtils.error("URL mismatch. Expected: " + expectedURL + ", Actual: " + driver.getCurrentUrl());
            return false;
        }
        return true;


    }

    public static LandingPage login(WebDriver driver, String username, String password) {
        return new LoginPage(driver)
                .EnterUsername(username)
                .EnterPassword(password).ClickLoginButton();
    }

    public static boolean verifyLogoText(WebDriver driver, String expectedText, String logolocator) {
        String actualText = driver.findElement(By.className(logolocator)).getText().trim();
        LogsUtils.info("Logo Text → Expected: '" + expectedText + "' | Actual: '" + actualText + "'");
        return actualText.equals(expectedText);
    }

}

