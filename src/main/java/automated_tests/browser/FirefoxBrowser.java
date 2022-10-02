package automated_tests.browser;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxBrowser implements Adapter<FirefoxDriver> {

    @Override
    public Browser browser() {
        return new BrowserAdapter(new FirefoxDriver());
    }
}