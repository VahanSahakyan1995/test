package automated_tests.automated_tests_list.task;

import automated_tests.automated_tests_list.base.Base;
import automated_tests.pages.base.PicsartBasePage;
import automated_tests.pages.create.CreatePage;
import automated_tests.pages.settings.SettingsPage;
import automated_tests.utils.RetryAnalyzer;
import org.junit.Assert;
import org.testng.annotations.Test;

public class FirstTask extends Base {

    private CreatePage createPage;
    private PicsartBasePage picsartBasePage;
    private SettingsPage settingsPage;

    //Precondition
    //User should be logged out

    @Test(priority = 1)
    public void navigateToCreatePage() {
        createPage = new CreatePage(getBrowser());
        createPage.clickOnAcceptAllCookies();
        Assert.assertTrue(createPage.checkIfCookiesFooterIsClosed());
        createPage.closeTourWindow();
        Assert.assertTrue(createPage.checkIfTourWindowIsClosed());
    }

    //Use RetryAnalyzer because "New project" button did not exist
    @Test(priority = 2, dependsOnMethods = "navigateToCreatePage", retryAnalyzer = RetryAnalyzer.class)
    public void clickOnUnExistableNewProjectButton() {
        createPage.clickOnNewProject();
    }

    @Test(priority = 3, dependsOnMethods = "navigateToCreatePage", retryAnalyzer = RetryAnalyzer.class)
    public void checkIfRegistrationPopupWindowDisplayed() {
        Assert.assertTrue(createPage.checkIfRegistrationPopupWindowDisplayed());
    }

    @Test(priority = 4, dependsOnMethods = "navigateToCreatePage")
    public void clickOnSeeAllTemplates() {
        createPage.openAllTemplates();
        Assert.assertTrue(createPage.checkIfAllTemplatesIsDisplayed());
    }

    @Test(priority = 5, dependsOnMethods = "clickOnSeeAllTemplates")
    public void addAnyTemplate() {
        createPage.clickOnAnyTemplate();
    }

    @Test(priority = 6, dependsOnMethods = "navigateToCreatePage")
    public void changeBackground() {
        createPage.openBackground();
        Assert.assertTrue(createPage.checkIfBackgroundIsDisplayed());
        createPage.changeBackground();
    }

    @Test(priority = 7, dependsOnMethods = "navigateToCreatePage")
    public void addAnySticker() {
        createPage.openStickers();
        Assert.assertTrue(createPage.checkIfStickersIsDisplayed());
        createPage.clickOnAnySticker();
    }

    @Test(priority = 8, dependsOnMethods = "navigateToCreatePage")
    public void addAnyPhoto() {
        createPage.closeUsingInstrumentWindow();
        Assert.assertTrue(createPage.checkIfUsingInstrumentWindowIsClosed());
        createPage.openPhotos();
        Assert.assertTrue(createPage.checkIfPhotosIsDisplayed());
        createPage.clickOnAnyPhoto();
    }

    @Test(priority = 9, dependsOnMethods = "navigateToCreatePage", retryAnalyzer = RetryAnalyzer.class)
    public void checkAgainIfRegistrationPopupWindowDisplayed() {
        Assert.assertTrue(createPage.checkIfRegistrationPopupWindowDisplayed());
    }

    //Precondition
    //User should be in logged in state

    @Test(priority = 10)
    public void fillSignInWindow() {
        picsartBasePage = new PicsartBasePage(getBrowser());
        picsartBasePage.openSignInWindow();
        Assert.assertTrue(picsartBasePage.checkIfSignInWindowDisplayed());
        picsartBasePage.fillSignInWindow();
    }

    //Use RetryAnalyzer because sometimes it shows "Bot behavior detected" error
    @Test(priority = 11, dependsOnMethods = "fillSignInWindow", retryAnalyzer = RetryAnalyzer.class)
    public void signInWithCreateFreeAccount() {
        picsartBasePage.clickOnCreateFreeAccountButton();
        Assert.assertTrue(picsartBasePage.checkIfSignInWindowIsClosed());
    }

    @Test(priority = 12, dependsOnMethods = "signInWithCreateFreeAccount")
    public void navigateToAccountSettings() {
        Assert.assertTrue(picsartBasePage.checkIfAccountAvatarShowed());
        picsartBasePage.hoverToAvatar();
        Assert.assertTrue(picsartBasePage.checkIfAccountPopupWindowShowed());
        settingsPage = picsartBasePage.hoverAndClickOnSettingsButton();
        Assert.assertTrue(settingsPage.checkIfAccountSettingsPageDisplayed());
    }

    @Test(priority = 13, dependsOnMethods = "navigateToAccountSettings")
    public void checkIfUploadContainerIsDisplayedCorrectly() {
        Assert.assertTrue(settingsPage.checkIfUploadButtonShowed());
        Assert.assertTrue(settingsPage.checkIfUploadDescriptionWriteCorrectly());
    }

    @Test(priority = 14, dependsOnMethods = "checkIfUploadContainerIsDisplayedCorrectly")
    public void uploadImageAndSave() {
        settingsPage.uploadImage();
        settingsPage.scrollDown();
        settingsPage.clickOnSaveChangeButton();
    }

    //Use RetryAnalyzer because it needs time to check
    @Test(priority = 15, dependsOnMethods = "uploadImageAndSave", retryAnalyzer = RetryAnalyzer.class)
    public void checkIfImageWasSuccessfullyUploaded() {
        Assert.assertTrue(settingsPage.checkIfUploadedImageIsSaved());
    }
}
