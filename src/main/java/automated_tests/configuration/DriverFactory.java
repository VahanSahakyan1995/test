package automated_tests.configuration;

import automated_tests.browser.Browser;
import automated_tests.browser.ChromeBrowser;
import automated_tests.browser.FirefoxBrowser;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private final Map<String, Browser<WebDriver>> driver = new HashMap<>();

    private static DriverFactory instance;

    public static synchronized DriverFactory getInstance() {
        if (instance == null) {
            instance = new DriverFactory();
        }
        return instance;
    }

//    when use multithreading
//    public static DriverFactory getInstance() {
//        if (instance == null) {
//            synchronized (DriverFactory.class) {
//                if (instance == null) {
//                    instance = new DriverFactory();
//                }
//            }
//        }
//        return instance;
//    }

    private DriverFactory() {
    }

    @SuppressWarnings("unchecked")
    public synchronized void setDriver(String browserName) {
        if (browserName == null)
            browserName = "chrome";
        Browser<WebDriver> browserDriver = null;
        switch (browserName) {
            case "firefox":
                browserDriver = new FirefoxBrowser().browser();
                break;
            case "chrome":
            default:
                browserDriver = new ChromeBrowser().browser();
        }
        if (!driver.containsKey(Thread.currentThread().getName())) {
            driver.put(Thread.currentThread().getName(), browserDriver);
        }
    }

    @SuppressWarnings("unchecked")
    public Browser<WebDriver> getBrowser() {
        return driver.get(Thread.currentThread().getName());
    }

    public synchronized void close() {
        getBrowser().driver().close();
        getBrowser().driver().quit();
        driver.remove(Thread.currentThread().getName());
    }

}