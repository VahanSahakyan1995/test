package automated_tests.pages.create;

import automated_tests.browser.Browser;
import automated_tests.configuration.AppConfig;
import automated_tests.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreatePage extends BasePage {

    public CreatePage(Browser<WebDriver> browser) {
        super(browser, AppConfig.getInstance().getCreateUrl(), true);
    }

    public boolean checkIfCookiesFooterIsShown() {
        return getBrowser().untilIsDisplayed(By.id("onetrust-accept-btn-handler"));
    }

    public void closeTourWindow() {
        click(getBrowser().untilElementToBeClickable(By.cssSelector("button[data-testid='hints-close']")));
    }

    public boolean checkIfCookiesFooterIsClosed(){
        return getBrowser().untilIsNotDisplayed(By.cssSelector("div[data-testid='hints-main']"));
    }

    public void clickOnAcceptAllCookies() {
        if (checkIfCookiesFooterIsShown()) {
            click(getBrowser().untilElementToBeClickable(By.id("onetrust-accept-btn-handler")));
        }
    }

    public boolean checkIfTourWindowIsClosed(){
        return getBrowser().untilIsNotDisplayed(By.id("onetrust-accept-btn-handler"));
    }

    public void clickOnNewProject() {
        click(getBrowser().untilElementToBeClickable(By.id("newProjectBtn")));
    }

    public boolean checkIfRegistrationPopupWindowDisplayed(){
        return getBrowser().untilIsDisplayed(By.cssSelector("div[data-test='sign_up_form']"));
    }

    public void openAllTemplates() {
        click(getBrowser().untilElementToBeClickable(By.id("templates-category")));
    }

    public boolean checkIfAllTemplatesIsDisplayed(){
        return getBrowser().untilIsDisplayed(By.cssSelector("div[data-testid='template-header']"));
    }

    public void clickOnAnyTemplate() {
        click(getBrowser().untilElementToBeClickable(By.cssSelector("div[data-rbd-drag-handle-context-id='0']")));
    }

    public void openBackground() {
        click(getBrowser().untilElementToBeClickable(By.id("background-category")));
    }

    public boolean checkIfBackgroundIsDisplayed(){
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

    public boolean checkIfStickersIsDisplayed(){
        return getBrowser().untilIsDisplayed(By.cssSelector("div[data-testid='base-sidebar']"));
    }

    public void clickOnAnySticker() {
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

    public boolean checkIfUsingInstrumentWindowIsClosed(){
        return getBrowser().untilIsNotDisplayed(By.cssSelector("div[data-testid='hints-main']"));
    }

    public boolean checkIfPhotosIsDisplayed(){
        return getBrowser().untilIsDisplayed(By.cssSelector("div[data-rbd-drag-handle-context-id='0']"));
    }

    public void clickOnAnyPhoto() {
        click(getBrowser().untilElementToBeClickable(By.cssSelector("div[data-rbd-drag-handle-context-id='0']")));
    }

}
