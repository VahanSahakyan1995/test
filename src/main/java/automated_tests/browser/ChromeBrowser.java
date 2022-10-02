package automated_tests.browser;

import automated_tests.configuration.AppConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.time.Duration;

public class ChromeBrowser implements Adapter<ChromeDriver> {
    @Override
    public Browser browser() {
        System.setProperty("webdriver.chrome.driver", AppConfig.getInstance().getDriverUrl());
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(AppConfig.getInstance().isHeadless());
        //Start of additional options
        chromeOptions.addArguments("start-maximized"); // open Browser in maximized mode
        chromeOptions.addArguments("disable-infobars"); // disabling infobars
        chromeOptions.addArguments("--disable-extensions"); // disabling extensions
        chromeOptions.addArguments("--disable-gpu"); // applicable to windows os only
        chromeOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        chromeOptions.addArguments("--no-sandbox"); // Bypass OS security model
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

        if (AppConfig.getInstance().isHeadless()) {
            chromeOptions.addArguments("--allow-insecure-localhost");
            chromeOptions.addArguments("window-size=1920x1080");
        }
        //End of additional options
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        if (AppConfig.getInstance().isHeadless()) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(AppConfig.getInstance().getImplicitTime()));
        }
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(AppConfig.getInstance().getPageLoadTime()));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(AppConfig.getInstance().getScriptTime()));
        return new BrowserAdapter(driver);
    }
}