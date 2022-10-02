package automated_tests;

import automated_tests.automated_tests_list.base.OpenGoogle;
import automated_tests.utils.CustomListener;
import automated_tests.utils.Listener;
import automated_tests.utils.RetryAnalyzer;
import lombok.extern.log4j.Log4j2;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class UATestApp {

    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        testNG.setVerbose(10);
        testNG.setTestClasses(new Class[]{OpenGoogle.class});
        testNG.setListenerClasses(List.of(Listener.class, CustomListener.class, RetryAnalyzer.class));
        testNG.run();
        if (!testNG.hasFailure()) {
            testNG = new TestNG();
            testNG.setUseDefaultListeners(false);
            List<String> suite = new ArrayList<>();
            suite.add(args[0]);
            testNG.setTestSuites(suite);
            testNG.run();
        }
    }

}