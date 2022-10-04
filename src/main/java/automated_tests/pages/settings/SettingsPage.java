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

    public boolean isAccountSettingsPage() {
        return getBrowser().untilIsNotDisplayed(By.cssSelector("div[data-testid='settings']"));
    }

    public boolean isUploadButtonShow() {
        return getBrowser().findElement(By.cssSelector("label[for='upload-avatar']")).isDisplayed();
    }

    public boolean isUploadDescriptionCorrect(String uploadDescription) {
        return getBrowser().findElement(By.className("user-avatar-description-")).getText().equals(uploadDescription);
    }

    public void uploadImage(){
        WebElement uploadAvatarImage = getBrowser().findElement(By.xpath("//input[@id='upload-avatar']"));
        uploadAvatarImage.sendKeys(AppConfig.getInstance().getImagePath());
    }

    public void clickSaveChangeButton() {
        click(getBrowser().untilElementToBeClickable(By.cssSelector("div[data-testid='save-changes-button']")));
    }
}
