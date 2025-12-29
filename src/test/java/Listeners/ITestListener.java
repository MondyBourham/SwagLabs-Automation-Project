package Listeners;

import Utilities.LogsUtils;
import org.testng.ITestResult;

public class ITestListener implements org.testng.ITestListener {

    public void onTestStart(ITestResult result) {

        LogsUtils.info("▶️ Starting test: " + result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        LogsUtils.info("✅ Test passed: " + result.getName());

    }

    public void onTestSkipped(ITestResult result) {
        LogsUtils.info("⏭️ Test skipped: " + result.getName());

    }
}
