package automated_tests.configuration;

import lombok.extern.log4j.Log4j2;

        import java.io.IOException;
        import java.io.InputStream;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Properties;

@Log4j2
public final class AppConfig {

    private String driverUrl;

    private String baseUrl;

    private String restAssuredUrl;

    private String restAssuredJson;

    private boolean headless;

    private long implicitTime;

    private long explicitTime;

    private long pageLoadTime;

    private long scriptTime;

    private final List<FakePassword> fakePasswords = new ArrayList<>();

    private static AppConfig instance;

    public static synchronized AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    private AppConfig() {
        load();
    }

    private void load() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(getConfigName())) {
            Properties prop = new Properties();
            prop.load(input);
            this.fakePasswords.add(new FakePassword());
            this.driverUrl = prop.getProperty("webdriver.path");
            this.restAssuredUrl = prop.getProperty("restAssured.url");
            this.restAssuredJson = prop.getProperty("restAssured.json");
            this.baseUrl = prop.getProperty("test.base.url");
            this.headless = Boolean.parseBoolean(prop.getProperty("webdriver.headless"));
            this.implicitTime = Long.parseLong(prop.getProperty("implicit.wait.time"));
            this.explicitTime = Long.parseLong(prop.getProperty("explicit.wait.time"));
            this.pageLoadTime = Long.parseLong(prop.getProperty("page.load.time"));
            this.scriptTime = Long.parseLong(prop.getProperty("script.time"));
        } catch (IOException ex) {
            log.error("Can not load properties");
        }
    }

    private String getConfigName() {
        String activeProfile = System.getProperty("spring.profiles.active");
        String configName = "application";
        if (activeProfile != null) {
            configName += "-" + activeProfile;
        }
        return configName + ".properties";
    }

    public String getDriverUrl() {
        return driverUrl;
    }

    public String getRestAssuredUrl() {
        return restAssuredUrl;
    }

    public String getRestAssuredJson() {
        return restAssuredJson;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public boolean isHeadless() {
        return headless;
    }

    public String getShortPassword1() {
        return fakePasswords.get(0).getShort1();
    }

    public String getShortPassword2() {
        return fakePasswords.get(0).getShort2();
    }

    public long getImplicitTime() {
        return implicitTime;
    }

    public long getExplicitTime() { return explicitTime; }

    public long getPageLoadTime() {
        return pageLoadTime;
    }

    public long getScriptTime() {
        return scriptTime;
    }
}