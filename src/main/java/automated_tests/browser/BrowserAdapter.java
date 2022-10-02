package automated_tests.browser;

import automated_tests.configuration.AppConfig;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.time.Duration;

@Log4j2
public class BrowserAdapter implements Browser<WebDriver> {

    private final WebDriver driver;
    private final WebDriverWait wait;

    BrowserAdapter(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(AppConfig.getInstance().getExplicitTime()));
    }

    @Override
    public WebDriver driver() {
        return driver;
    }

    @Override
    public WebDriverWait waiting() {
        return wait;
    }

    @Override
    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    @Override
    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    @Override
    public WebElement untilVisibilityOfElementLocated(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    @Override
    public WebElement untilPresenceOfElementLocated(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    @Override
    public WebElement untilElementToBeClickable(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    @Override
    public List<WebElement> untilPresenceOfAllElementLocated(By by) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    @Override
    public List<WebElement> untilNumberOfElementsToBeMoreThan(By by, int number) {
        return wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, number));
    }

    @Override
    public boolean untilTextToBePresentInElementLocated(By by, String text) {
        try {
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(by, text));
        } catch (TimeoutException e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public boolean untilIsDisplayed(By by) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
        } catch (TimeoutException e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public boolean untilIsDisplayed(By by, long time) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(time)).until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
        } catch (TimeoutException e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public boolean untilIsNotDisplayed(By by) {
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (TimeoutException e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public boolean untilIsDisplayedLogOff(By by, long time) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(time)).until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

}