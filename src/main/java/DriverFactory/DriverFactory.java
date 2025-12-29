package DriverFactory;

import Utilities.LogsUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;


//thread local && parallel exce
public class DriverFactory {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void setDriver(String browser) {
        switch (browser.toLowerCase()) {

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                driverThreadLocal.set(new ChromeDriver());
                LogsUtils.info("Chrome driver has been set");
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                driverThreadLocal.set(new FirefoxDriver());
                LogsUtils.info("Firefox driver has been set");
                break;
            case "safari": // SafariDriver comes with macOS , no setup needed
                driverThreadLocal.set(new SafariDriver());
                LogsUtils.info("Safari driver has been set");
                break;
            default:
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                driverThreadLocal.set(new EdgeDriver());
                LogsUtils.info("Edge driver has been set");
        }
    }

    public static void quitDriver() {
        getDriver().quit();
        driverThreadLocal.remove();
    }
}
