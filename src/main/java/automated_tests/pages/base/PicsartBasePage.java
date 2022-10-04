package automated_tests.pages.base;

import automated_tests.browser.Browser;
import automated_tests.configuration.AppConfig;
import automated_tests.pages.settings.SettingsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PicsartBasePage extends BasePage{

    public PicsartBasePage(Browser<WebDriver> browser) {
        super(browser, AppConfig.getInstance().getBaseUrl(), true);
    }

    public void openSignUpWindow() {
        click(getBrowser().untilElementToBeClickable(By.cssSelector("button[data-test='signup-button']")));
    }

    public boolean isSignUpWindowDisplay() {
        return getBrowser().untilIsDisplayed(By.cssSelector("div[data-test='sign_up_form']"));
    }

    public void signUpWithCreateFreeAccount() {
        WebElement accountEmailInputArea = getBrowser().findElement(By.cssSelector("input[aria-label='Enter email address']"));
        accountEmailInputArea.sendKeys(AppConfig.getInstance().getEmail());
        WebElement accountPasswordInputArea = getBrowser().findElement(By.cssSelector("input[data-testid='popper-button']"));
        accountPasswordInputArea.sendKeys(AppConfig.getInstance().getPassword());
    }

    public void clickCreateFreeAccountButton() {
        click(getBrowser().untilElementToBeClickable(By.cssSelector("button[data-test='signup']")));
    }

    public boolean isSignUpWindowClosed() {
        return getBrowser().untilIsNotDisplayed(By.cssSelector("div[data-test='sign_up_form']"));
    }

    public boolean isAccountAvatarShow() {
        return getBrowser().untilIsDisplayed(By.cssSelector("img[data-testid='header-profile-user-avatar']"));
    }

    public void hoverToAvatar() {
        hover(getBrowser().findElement(By.cssSelector("img[data-testid='header-profile-user-avatar']")));
    }

    public boolean isAccountPopupWindowShow() {
        return getBrowser().untilIsDisplayed(By.cssSelector("span[data-test='profile-button']"));
    }

    public SettingsPage hoverAndClickTheSettingsButton() {
        hoverAndClick(getBrowser().untilElementToBeClickable(By.cssSelector("img[data-testid='header-profile-user-avatar']")));
        return new SettingsPage(getBrowser());
    }

}
