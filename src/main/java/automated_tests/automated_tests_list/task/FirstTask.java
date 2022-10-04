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
    public void signUpWithCreateFreeAccount() {
        picsartBasePage = new PicsartBasePage(getBrowser());
        picsartBasePage.openSignUpWindow();
        Assert.assertTrue(picsartBasePage.isSignUpWindowDisplay());
        picsartBasePage.signUpWithCreateFreeAccount();
        picsartBasePage.clickCreateFreeAccountButton();
        Assert.assertTrue(picsartBasePage.isSignUpWindowClosed());
    }

    @Test(priority = 9, dependsOnMethods = "signUpWithCreateFreeAccount")
    public void navigateToAccountSettings() {
        Assert.assertTrue(picsartBasePage.isAccountAvatarShow());
        picsartBasePage.hoverToAvatar();
        Assert.assertTrue(picsartBasePage.isAccountPopupWindowShow());
        settingsPage = picsartBasePage.hoverAndClickTheSettingsButton();
        Assert.assertTrue(settingsPage.isAccountSettingsPage());
    }

    @Test(priority = 10, dependsOnMethods = "navigateToAccountSettings")
    public void checkThatUploadContainerIsDisplayCorrect() {
        Assert.assertTrue(settingsPage.isUploadButtonShow());
        Assert.assertTrue(settingsPage.isUploadDescriptionCorrect("You can upload jpg. or png image files. Max size 2mb."));
    }

    @Test(priority = 11, dependsOnMethods = "checkThatUploadContainerIsDisplayCorrect")
    public void uploadImageAndSave() {
        settingsPage.uploadImage();
        settingsPage.clickSaveChangeButton();
    }
}
