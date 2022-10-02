package automated_tests.browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public interface Browser<T extends WebDriver> {

    T driver();

    WebDriverWait waiting();

    WebElement findElement(By by);

    List<WebElement> findElements(By by);

    WebElement untilVisibilityOfElementLocated(By by);

    WebElement untilPresenceOfElementLocated(By by);

    WebElement untilElementToBeClickable(By by);

    List<WebElement> untilPresenceOfAllElementLocated(By by);

    List<WebElement> untilNumberOfElementsToBeMoreThan(By by, int number);

    boolean untilTextToBePresentInElementLocated(By by, String text);

    boolean untilIsDisplayed(By by);

    boolean untilIsNotDisplayed(By by);

    boolean untilIsDisplayed(By by, long time);

    boolean untilIsDisplayedLogOff(By by, long time);

}