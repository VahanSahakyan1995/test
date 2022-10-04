package automated_tests.automated_tests_list.task;

import automated_tests.automated_tests_list.base.Base;
import automated_tests.pages.create.CreatePage;
import automated_tests.utils.RetryAnalyzer;
import org.junit.Assert;
import org.testng.annotations.Test;

public class FirstTask extends Base {

    private CreatePage createPage;

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
}
