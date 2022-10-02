package automated_tests.utils;

import automated_tests.browser.Browser;
import automated_tests.configuration.DriverFactory;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j2
public class Listener implements ITestListener {

    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    DateFormat dateFormat2 = new SimpleDateFormat("dd.MM.yyyy");
    Date date;
    private Browser<WebDriver> browser;

    @Override
    public synchronized void onStart(ITestContext context) {
        log.info(String.format(" %s test case is start."));
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        log.info(String.format(" %s test case is finish."));
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        log.info(String.format(" %s test is start ", result.getName()));
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        log.info(String.format(" %s test is success ", result.getName()));
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        log.error(String.format("***** Error %s test has failed *****", result.getName()), result.getThrowable());
        try {
            getScreenshotAndErrorLogsPages(result.getTestClass().getRealClass().getSimpleName(), result.getMethod().getMethodName());
        } catch (Exception c) {
            log.error(c);
        }
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        log.warn(String.format(" %s test skiped ", result.getName()));
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.warn(String.format(" %s test is failed but within success percentage ", result.getName()));
    }

    private void getScreenshotAndErrorLogsPages(String testClassName, String testMethodName) throws InterruptedException {
        try {
            Thread.sleep(1500);
            browser = getBrowser();
            date = new Date();
            String currentDate = dateFormat.format(date);
            String currentDate2 = dateFormat2.format(date);
            TakesScreenshot scrShot = ((TakesScreenshot) browser.driver());
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("file:///home/Error_Page_Screenshot_And_Logs/" + currentDate2 + "/" + testClassName + "/" + testMethodName + "_" + currentDate + ".jpg");
            File newFile = new File("file:///home/Error_Page_Screenshot_And_Logs/" + currentDate2 + "/" + testClassName + "/" + testMethodName + "_" + currentDate + ".txt");
            FileUtils.copyFile(SrcFile, DestFile);
            try (FileWriter fw = new FileWriter(newFile, true)) {
                LogEntries logEntries = browser.driver().manage().logs().get("browser");
                for (LogEntry entry : logEntries) {
                    fw.write(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage() + "\n");
                    String errorLogType = entry.getLevel().toString();
                    String errorLog = entry.getMessage().toString();
                    if (errorLog.contains("404")) {
                        fw.write("Error LogType: " + errorLogType + " Error Log message: " + errorLog + "\n");
                    }
                }
            }
        } catch (IOException e) {
            log.error(e);
        }
        Thread.sleep(1500);
    }

    private Browser<WebDriver> getBrowser() {
        return DriverFactory.getInstance().getBrowser();
    }

}