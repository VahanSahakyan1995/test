package automated_tests.pages.create;

import automated_tests.browser.Browser;
import automated_tests.configuration.AppConfig;
import automated_tests.pages.base.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreatePage extends BasePage {

    public CreatePage(Browser<WebDriver> browser) {
        super(browser, AppConfig.getInstance().getBaseUrl(), true);
    }

    public boolean isCookiesFooterShow() {
        return getBrowser().untilIsDisplayed(By.id("onetrust-accept-btn-handler"));
    }

    public void closeTourWindow() {
        click(getBrowser().untilElementToBeClickable(By.cssSelector("button[data-testid='hints-close']")));
        Assert.assertTrue(getBrowser().untilIsNotDisplayed(By.cssSelector("div[data-testid='hints-main']")));
    }

    public void clickAcceptAllCookies() {
        if (isCookiesFooterShow()) {
            click(getBrowser().untilElementToBeClickable(By.id("onetrust-accept-btn-handler")));
            getBrowser().untilIsNotDisplayed(By.id("onetrust-accept-btn-handler"));
        }
    }

    public void clickNewProject() {
        click(getBrowser().untilElementToBeClickable(By.id("newProjectBtn")));
    }

    public void openAllTemplates() {
        if (getBrowser().untilIsNotDisplayed(By.cssSelector("div[data-testid='template-header']"))) {
            click(getBrowser().untilElementToBeClickable(By.id("templates-category")));
            getBrowser().untilIsDisplayed(By.cssSelector("div[data-testid='template-header']"));
        }
    }

    public void clickInFirstTemplate() {
        click(getBrowser().untilElementToBeClickable(By.xpath("//*[@id=\"root\"]/div/main/div[1]/section/div[1]/div[2]/div/div[2]/div/div/div/div[2]/div/div/div[2]/div/div/div[1]")));
    }

    public void openBackground() {
        if (getBrowser().untilIsNotDisplayed(By.cssSelector("div[data-testid='background-section-header']"))) {
            click(getBrowser().untilElementToBeClickable(By.id("background-category")));
            getBrowser().untilIsDisplayed(By.cssSelector("div[data-testid='background-section-header']"));
        }
    }

    public void changeBackground() {
        click(getBrowser().untilElementToBeClickable(By.cssSelector("div[data-testid='color-clock-item']")));
    }

    public void openStickers() {
        click(getBrowser().untilElementToBeClickable(By.id("stickers-category")));
        getBrowser().untilIsDisplayed(By.cssSelector("div[data-testid='background-section-header']"));
    }

    public void clickFirstSticker() {
        click(getBrowser().untilElementToBeClickable(By.cssSelector("div[data-testid='color-clock-item']")));
    }
}
