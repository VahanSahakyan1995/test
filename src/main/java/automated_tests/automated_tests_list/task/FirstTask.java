package automated_tests.automated_tests_list.task;

import automated_tests.automated_tests_list.base.Base;
import automated_tests.pages.create.CreatePage;
import automated_tests.utils.RetryAnalyzer;
import org.testng.annotations.Test;

public class FirstTask extends Base {

    private CreatePage createPage;

    @Test(priority = 1)
    public void navigateToTheCreatePage() {
        createPage = new CreatePage(getBrowser());
        createPage.clickAcceptAllCookies();
        createPage.closeTourWindow();
    }

    @Test(priority = 2, dependsOnMethods = "navigateToTheCreatePage", retryAnalyzer = RetryAnalyzer.class)
    public void clickUnExcitableNewProjectButton() {
        createPage.clickNewProject();
    }

    @Test(priority = 3, dependsOnMethods = "navigateToTheCreatePage")
    public void clickSeeAllTemplates() {
        createPage.openAllTemplates();
    }

    @Test(priority = 4, dependsOnMethods = "clickSeeAllTemplates")
    public void addAnyTemplate() {
        createPage.clickInAnyTemplate();
    }

    @Test(priority = 5, dependsOnMethods = "navigateToTheCreatePage")
    public void changeBackground() {
        createPage.openBackground();
        createPage.changeBackground();
    }

    @Test(priority = 6, dependsOnMethods = "navigateToTheCreatePage")
    public void addAnySticker() {
        createPage.openStickers();
        createPage.clickInAnySticker();
    }

    @Test(priority = 7, dependsOnMethods = "navigateToTheCreatePage")
    public void addAnyPhoto() {
        createPage.closeUsingInstrumentWindow();
        createPage.openPhotos();
        createPage.clickInAnyPhoto();
    }
}
