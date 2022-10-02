package automated_tests.browser;

import org.openqa.selenium.WebDriver;

public interface Adapter<T extends WebDriver> {

    Browser<T> browser();
}