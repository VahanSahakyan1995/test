package automated_tests.pages.create;

import automated_tests.browser.Browser;
import automated_tests.configuration.AppConfig;
import automated_tests.pages.base.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreatePage extends BasePage {

    public CreatePage(Browser<WebDriver> browser) {
        super(browser, AppConfig.getInstance().getCreateUrl(), true);
    }

    public boolean isCookiesFooterShow() {
        return getBrowser().untilIsDisplayed(By.id("onetrust-accept-btn-handler"));
    }

    public void closeTourWindow() {
        click(getBrowser().untilElementToBeClickable(By.cssSelector("button[data-testid='hints-close']")));
    }

    public boolean isCookiesFooterClose(){
        return getBrowser().untilIsNotDisplayed(By.cssSelector("div[data-testid='hints-main']"));
    }

    public void clickAcceptAllCookies() {
        if (isCookiesFooterShow()) {
            click(getBrowser().untilElementToBeClickable(By.id("onetrust-accept-btn-handler")));
        }
    }

    public boolean isTourWindowClose(){
        return getBrowser().untilIsNotDisplayed(By.id("onetrust-accept-btn-handler"));
    }

    public void clickNewProject() {
        click(getBrowser().untilElementToBeClickable(By.id("newProjectBtn")));
    }

    public void openAllTemplates() {
        click(getBrowser().untilElementToBeClickable(By.id("templates-category")));
    }

    public boolean isAllTemplatesDisplay(){
        return getBrowser().untilIsDisplayed(By.cssSelector("div[data-testid='template-header']"));
    }

    public void clickInAnyTemplate() {
        click(getBrowser().untilElementToBeClickable(By.cssSelector("div[data-rbd-drag-handle-context-id='0']")));
    }

    public void openBackground() {
        click(getBrowser().untilElementToBeClickable(By.id("background-category")));
    }

    public boolean isBackgroundDisplay(){
        return getBrowser().untilIsDisplayed(By.cssSelector("div[data-testid='background-section-header']"));
    }

    public void changeBackground() {
        click(getBrowser().untilElementToBeClickable(By.id("Texture")));
        getBrowser().untilIsDisplayed(By.cssSelector("div[data-testid='texture-item-y2k_patterns']"));
        click(getBrowser().untilElementToBeClickable(By.cssSelector("div[data-testid='texture-item-y2k_patterns']")));
    }

    public void openStickers() {
        click(getBrowser().untilElementToBeClickable(By.id("stickers-category")));
    }

    public boolean isStickersDisplay(){
        return getBrowser().untilIsDisplayed(By.cssSelector("div[data-testid='base-sidebar']"));
    }

    public void clickInAnySticker() {
        getBrowser().untilIsDisplayed(By.cssSelector("div[data-rbd-drag-handle-context-id='0']"));
        click(getBrowser().untilElementToBeClickable(By.cssSelector("div[data-rbd-drag-handle-context-id='0']")));
    }

    public void openPhotos() {
        click(getBrowser().untilElementToBeClickable(By.id("photos-category")));
    }

    public void closeUsingInstrumentWindow() {
        getBrowser().untilIsDisplayed(By.cssSelector("button[data-testid='hints-close']"));
        click(getBrowser().untilElementToBeClickable(By.cssSelector("button[data-testid='hints-close']")));
    }

    public boolean isUsingInstrumentWindowClose(){
        return getBrowser().untilIsNotDisplayed(By.cssSelector("div[data-testid='hints-main']"));
    }

    public boolean isPhotosDisplay(){
        return getBrowser().untilIsDisplayed(By.cssSelector("div[data-rbd-drag-handle-context-id='0']"));
    }

    public void clickInAnyPhoto() {
        click(getBrowser().untilElementToBeClickable(By.cssSelector("div[data-rbd-drag-handle-context-id='0']")));
    }

}
