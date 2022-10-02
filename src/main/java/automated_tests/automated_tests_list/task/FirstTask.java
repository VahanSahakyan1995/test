package automated_tests.automated_tests_list.task;

import automated_tests.automated_tests_list.base.Base;
import automated_tests.pages.create.CreatePage;
import org.testng.annotations.Test;

public class FirstTask extends Base {

    private CreatePage createPage;

    //@Test(priority = 1, retryAnalyzer = RetryAnalyzer.class)
    @Test(priority = 1)
    public void navigateToTheCreatePage() {
        createPage = new CreatePage(getBrowser());
        createPage.clickAcceptAllCookies();
        createPage.closeTourWindow();
    }
}
