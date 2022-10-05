package automated_tests.pages.settings;

import automated_tests.browser.Browser;
import automated_tests.configuration.AppConfig;
import automated_tests.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SettingsPage extends BasePage {

    public SettingsPage(Browser<WebDriver> browser) {
        super(browser, AppConfig.getInstance().getSettingsUrl(), true);
    }

    public boolean checkIfAccountSettingsPageDisplayed() {
        return getBrowser().untilIsNotDisplayed(By.cssSelector("div[data-testid='settings']"));
    }

    public boolean checkIfUploadButtonShowed() {
        return getBrowser().untilIsDisplayed(By.cssSelector("label[for='upload-avatar']"));
    }

    public boolean checkIfUploadDescriptionWriteCorrectly() {
        return pContains("You can upload jpg. or png image files. Max size 2mb.");
    }

    public void uploadImage(){
        WebElement uploadAvatarImage = getBrowser().findElement(By.xpath("//input[@id='upload-avatar']"));
        uploadAvatarImage.sendKeys(AppConfig.getInstance().getImagePath());
    }

    public void clickOnSaveChangeButton() {
        click(getBrowser().untilElementToBeClickable(By.cssSelector("button[data-test='save-changes-button']")));
    }

    public boolean checkIfUploadedImageIsSaved() {
        sleep(1500);
        return !getBrowser().findElement(By.cssSelector("button[data-test='save-changes-button']")).isEnabled();
    }
}
