package automated_tests.pages.base;

import automated_tests.browser.Browser;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.springframework.util.ObjectUtils;

@Log4j2
@Getter
public class BasePage {

    private Browser<WebDriver> browser;
    private String pageUrl;

    public void waitForPageLoad() {
        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            try {
                return ((Long) ((JavascriptExecutor) getBrowser().driver()).executeScript("return jQuery.active") == 0);
            } catch (Exception e) {
                // no jQuery present
                return true;
            }
        };
        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) getBrowser().driver()).executeScript("return document.readyState")
                .toString().equals("complete");
        browser.waiting().until(jQueryLoad);
        browser.waiting().until(jsLoad);
    }

    public BasePage(Browser<WebDriver> browser, String pageUrl, boolean isNavigate) {
        if (!ObjectUtils.isEmpty(pageUrl)) {
            this.pageUrl = pageUrl;
        } else {
            this.pageUrl = browser.driver().getCurrentUrl();
        }
        if (isNavigate) {
            browser.driver().navigate().to(this.pageUrl);
        }
        this.browser = browser;
        waitForPageLoad();
        PageFactory.initElements(new AjaxElementLocatorFactory(browser.driver(), 30), this);
    }

    protected void click(WebElement element) {
        element.click();
        waitForPageLoad();
    }

    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            log.error(e);
            Thread.currentThread().interrupt();
        }
    }

}
