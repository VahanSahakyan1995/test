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
    public void navigateToTheCreatePage() {
        createPage = new CreatePage(getBrowser());
        createPage.clickAcceptAllCookies();
        Assert.assertTrue(createPage.isCookiesFooterClose());
        createPage.closeTourWindow();
        Assert.assertTrue(createPage.isTourWindowClose());
    }

    //Use RetryAnalyzer because "New project" button did not exist
    @Test(priority = 2, dependsOnMethods = "navigateToTheCreatePage", retryAnalyzer = RetryAnalyzer.class)
    public void clickUnExcitableNewProjectButton() {
        createPage.clickNewProject();
    }

    @Test(priority = 3, dependsOnMethods = "navigateToTheCreatePage")
    public void clickSeeAllTemplates() {
        createPage.openAllTemplates();
        Assert.assertTrue(createPage.isAllTemplatesDisplay());
    }

    @Test(priority = 4, dependsOnMethods = "clickSeeAllTemplates")
    public void addAnyTemplate() {
        createPage.clickInAnyTemplate();
    }

    @Test(priority = 5, dependsOnMethods = "navigateToTheCreatePage")
    public void changeBackground() {
        createPage.openBackground();
        Assert.assertTrue(createPage.isBackgroundDisplay());
        createPage.changeBackground();
    }

    @Test(priority = 6, dependsOnMethods = "navigateToTheCreatePage")
    public void addAnySticker() {
        createPage.openStickers();
        Assert.assertTrue(createPage.isStickersDisplay());
        createPage.clickInAnySticker();
    }

    @Test(priority = 7, dependsOnMethods = "navigateToTheCreatePage")
    public void addAnyPhoto() {
        createPage.closeUsingInstrumentWindow();
        Assert.assertTrue(createPage.isUsingInstrumentWindowClose());
        createPage.openPhotos();
        Assert.assertTrue(createPage.isPhotosDisplay());
        createPage.clickInAnyPhoto();
    }

    //Precondition
    //User should be in logged in state

    @Test(priority = 8)
    public void fillSignInWindow() {
        picsartBasePage = new PicsartBasePage(getBrowser());
        picsartBasePage.openSignInWindow();
        Assert.assertTrue(picsartBasePage.isSignInWindowDisplay());
        picsartBasePage.signInWithCreateFreeAccount();
    }

    //Use RetryAnalyzer because sometimes show "Bot behavior detected" error
    @Test(priority = 9, dependsOnMethods = "fillSignInWindow", retryAnalyzer = RetryAnalyzer.class)
    public void signInWithCreateFreeAccount() {
        picsartBasePage.clickCreateFreeAccountButton();
        Assert.assertTrue(picsartBasePage.isSignInWindowClosed());
    }

    @Test(priority = 10, dependsOnMethods = "signInWithCreateFreeAccount")
    public void navigateToAccountSettings() {
        Assert.assertTrue(picsartBasePage.isAccountAvatarShow());
        picsartBasePage.hoverToAvatar();
        Assert.assertTrue(picsartBasePage.isAccountPopupWindowShow());
        settingsPage = picsartBasePage.hoverAndClickTheSettingsButton();
        Assert.assertTrue(settingsPage.isAccountSettingsPage());
    }

    @Test(priority = 11, dependsOnMethods = "navigateToAccountSettings")
    public void checkThatUploadContainerIsDisplayCorrect() {
        Assert.assertTrue(settingsPage.isUploadButtonShow());
        Assert.assertTrue(settingsPage.isUploadDescriptionCorrect());
    }

    @Test(priority = 12, dependsOnMethods = "checkThatUploadContainerIsDisplayCorrect")
    public void uploadImageAndSave() {
        settingsPage.uploadImage();
        settingsPage.scrollDown();
        settingsPage.clickSaveChangeButton();
    }

    //Use RetryAnalyzer because need time for correct work
    @Test(priority = 13, dependsOnMethods = "uploadImageAndSave", retryAnalyzer = RetryAnalyzer.class)
    public void checkThatImageIsUpload() {
        Assert.assertTrue(settingsPage.isSaveUploadImage());
    }
}
