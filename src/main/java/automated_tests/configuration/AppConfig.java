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

    private String createUrl;

    private String settingsUrl;

    private String imagePath;

    private String accountEmail;

    private String accountPassword;

    private String restAssuredUrl;

    private boolean headless;

    private long implicitTime;

    private long explicitTime;

    private long pageLoadTime;

    private long scriptTime;

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
            this.accountEmail = prop.getProperty("account.email");
            this.accountPassword = prop.getProperty("account.password");
            this.driverUrl = prop.getProperty("webdriver.path");
            this.restAssuredUrl = prop.getProperty("restAssured.url");
            this.baseUrl = prop.getProperty("picsart.base.url");
            this.createUrl = prop.getProperty("picsart.create.url");
            this.settingsUrl = prop.getProperty("picsart.settings.url");
            this.imagePath = prop.getProperty("image.path");
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

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getCreateUrl() {
        return createUrl;
    }

    public String getSettingsUrl() {
        return settingsUrl;
    }

    public String getImagePath() {
        return imagePath;
    }

    public boolean isHeadless() {
        return headless;
    }

    public String getEmail() {
        return accountEmail;
    }

    public String getPassword() {
        return accountPassword;
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