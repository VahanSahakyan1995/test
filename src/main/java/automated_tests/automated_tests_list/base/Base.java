package automated_tests.automated_tests_list.base;

import automated_tests.browser.Browser;
import automated_tests.configuration.AppConfig;
import automated_tests.configuration.DriverFactory;
import automated_tests.pages.create.CreatePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.*;
import java.time.Instant;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Log4j2
public class Base {

    private Browser<WebDriver> browser;
    private FileWriter myWriter;
    private Scanner myReader;
    private final Logger logger = Logger.getLogger(Base.class.getName());

    @BeforeTest
    @Parameters(value = {"browser"})
    public void initDriver(@Optional String brow) {
        DriverFactory.getInstance().setDriver(brow);
    }

    @AfterTest
    public static void closeDriver() {
        DriverFactory.getInstance().close();
    }

//    public TimelinePage logIn(String email, String password) {
//        HomePage homePage = new HomePage(getBrowser(), true);
//        if (!homePage.isLogeOut()) {
//            new Header(getBrowser()).clickToProfile().logOut();
//        }
//        homePage.closeIfOpenSignIn();
//        SignInPage signInPage = homePage.clickOnSignIn();
//        signInPage.input(email, password);
//       signInPage.clickRememberMe();
//        signInPage.submit();
//        return TimelinePage.of(getBrowser());
//    }

    public void browserNavigation(String url) {
        browser = getBrowser();
        browser.driver().navigate().to(url);
    }

    public Browser<WebDriver> getBrowser() {
        return DriverFactory.getInstance().getBrowser();
    }

    public String collectRandomName(String name) {
        return name + "_" + UUID.randomUUID();
    }

}