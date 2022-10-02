package automated_tests.utils;

import lombok.extern.log4j.Log4j2;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.reporters.FailedReporter;
import org.testng.reporters.SuiteHTMLReporter;
import org.testng.reporters.XMLReporter;
import org.testng.reporters.jq.Main;

import java.util.List;

@Log4j2
public class CustomListener implements ISuiteListener {
    private static final XMLReporter xmlReporter = new XMLReporter();
    private static final SuiteHTMLReporter suiteHTMLReporter = new SuiteHTMLReporter();
    private static final Main main = new Main();
    private static final FailedReporter failedReporter = new FailedReporter();

    private static final String OUTPUT = "file:///home/";

    @Override
    public void onFinish(ISuite suite) {
        log.info("finish suit");
        suiteHTMLReporter.generateReport(null, List.of(suite), OUTPUT);
        main.generateReport(null, List.of(suite), OUTPUT);
        failedReporter.generateReport(null, List.of(suite), OUTPUT);
        xmlReporter.generateReport(null, List.of(suite), OUTPUT);
    }
}