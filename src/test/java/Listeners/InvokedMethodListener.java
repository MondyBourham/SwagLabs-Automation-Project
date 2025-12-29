package Listeners;

import Utilities.LogsUtils;
import Utilities.Utilty;
import org.testng.*;

import static DriverFactory.DriverFactory.getDriver;

public class InvokedMethodListener implements IInvokedMethodListener {

    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext testContext) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            LogsUtils.info("Test case" + testResult.getName() + " Failed ");

            Utilty.takeScreenShoot(getDriver(), testResult.getName());
        }
    }
}
