package lab9;

import config.Lab8Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URL;
import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan(basePackages = "lab9")
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
public class TestConfig {

    private static Logger Log = LogManager.getLogger(TestConfig.class);

    private enum Browser{chrome, firefox};

    @Autowired
    Lab8Config config;

    @Bean
    public Lab8Config getConfig() {
        Lab8Config cfg = ConfigFactory.create(Lab8Config.class);
        return cfg;
    }

    @Bean
    @Scope("prototype")
    public WebDriver getDriver() throws Exception {
        Browser browser;
        try {
            browser = Browser.valueOf(config.browser().toLowerCase());
        } catch (IllegalArgumentException e) {
            Log.error("Unknown browser " + config.browser(), e);
            throw e;
        }

        WebDriver driver = null;
        if (browser == Browser.chrome) {
            WebDriverManager.chromedriver().setup();
        } else if (browser == Browser.firefox) {
            WebDriverManager.firefoxdriver().setup();
        } else {
            Log.error("Unknown browser type " + browser.name());
        }

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName(config.browser());
        caps.setVersion(config.browser_version());
        caps.setCapability("enableVNC", true);
        caps.setCapability("enableVideo", false);

        driver = new RemoteWebDriver(new URL("http://192.168.0.71:4444/wd/hub/"), caps);

        driver.manage().timeouts().implicitlyWait(4L, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }
}
