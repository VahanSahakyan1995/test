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

    public void openSignInWindow() {
        click(getBrowser().untilElementToBeClickable(By.cssSelector("button[data-test='login-button']")));
    }

    public boolean isSignInWindowDisplay() {
        return getBrowser().untilIsDisplayed(By.cssSelector("div[data-test='sign_in_form']"));
    }

    public void signInWithCreateFreeAccount() {
        WebElement accountEmailInputArea = getBrowser().findElement(By.cssSelector("input[aria-label='Enter username or email']"));
        accountEmailInputArea.sendKeys(AppConfig.getInstance().getEmail());
        WebElement accountPasswordInputArea = getBrowser().findElement(By.cssSelector("input[aria-label='Enter password']"));
        accountPasswordInputArea.sendKeys(AppConfig.getInstance().getPassword());
    }

    public void clickCreateFreeAccountButton() {
        click(getBrowser().untilElementToBeClickable(By.cssSelector("button[data-test='login']")));
    }

    public boolean isSignInWindowClosed() {
        return getBrowser().untilIsNotDisplayed(By.cssSelector("div[data-test='sign_in_form']"));
    }

    public boolean isAccountAvatarShow() {
        return getBrowser().untilIsDisplayed(By.xpath("//img[@title ='User avatar']"));
    }

    public void hoverToAvatar() {
        hover(getBrowser().findElement(By.xpath("//img[@title ='User avatar']")));
    }

    public boolean isAccountPopupWindowShow() {
        return getBrowser().untilIsDisplayed(By.cssSelector("span[data-test='profile-button']"));
    }

    public SettingsPage hoverAndClickTheSettingsButton() {
        hoverAndClick(getBrowser().untilElementToBeClickable(By.cssSelector("a[data-test='settings-button']")));
        return new SettingsPage(getBrowser());
    }

}
