package automated_tests.pages.create;

import automated_tests.browser.Browser;
import automated_tests.configuration.AppConfig;
import automated_tests.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreatePage extends BasePage {

    public CreatePage(Browser<WebDriver> browser) {
        super(browser, AppConfig.getInstance().getBaseUrl(),true);
    }

    public boolean isCookiesFooterShow() {
        return getBrowser().untilIsDisplayed(By.id("onetrust-accept-btn-handler"));
    }

    public boolean isTourWindowShow() {
        return getBrowser().untilIsDisplayed(By.xpath("/html/body/div[11]/div/div/div[2]"));
    }

    public void closeTourWindow() {
        if (isTourWindowShow()) {
            click(getBrowser().untilElementToBeClickable(By.xpath("/html/body/div[11]/div/div/div[2]/button")));
            getBrowser().untilIsNotDisplayed(By.xpath("/html/body/div[11]/div/div/div[2]"));
        }
    }

    public void clickAcceptAllCookies() {
        if (isCookiesFooterShow()) {
            click(getBrowser().untilElementToBeClickable(By.id("onetrust-accept-btn-handler")));
            getBrowser().untilIsNotDisplayed(By.id("onetrust-accept-btn-handler"));
        }
    }
}
