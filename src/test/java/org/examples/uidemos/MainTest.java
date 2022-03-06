package org.examples.uidemos;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

class MainTest {

    @BeforeEach
    void setUp() throws Exception {
//        Configuration.browser = "chrome";
//        Configuration.browser = "firefox";
//        Configuration.browser = "edge";
        Configuration.browser = "safari";

        Configuration.remote = "http://localhost:4444/wd/hub";

//        Configuration.remote = "http://192.168.0.158:4444/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("enableVNC", true);
        Map<String, Object> map = new HashMap<>();
        map.put("enableVNC", true);
        map.put("enableVideo", true);
        capabilities.setCapability("selenoid:options", map);

        if (Configuration.browser.equals("edge")) {
            capabilities.setBrowserName("MicrosoftEdge");
        }

        Configuration.browserCapabilities = capabilities;
        if (Configuration.browser.equals("safari")) {

            Configuration.browser = MySafariWebDriverFactory.class.getName();

//            SafariOptions safariOptions = getSafariCaps();
//            Configuration.browserCapabilities = safariOptions;
//            URL remote = new URL("http://localhost:4444/wd/hub");
//            RemoteWebDriver driver = new RemoteWebDriver(remote, safariOptions);
//            WebDriverRunner.setWebDriver(driver);
        }

    }

    @Test
    void todoMvc() {

        open("https://todomvc.com/examples/angularjs/");

        RemoteWebDriver wd = (RemoteWebDriver) WebDriverRunner.getWebDriver();

        Capabilities cap = wd.getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        String browserVersion = cap.getBrowserVersion();
        System.out.println("browser = " + browserName);
        System.out.println("browser version = " + browserVersion);
        $("input.new-todo").sendKeys("Show Selenide");
        $("input.new-todo").pressEnter();

        $(withText("Show Selenide"))
                .shouldBe(visible);
    }

    // @Test
    private SafariOptions getSafariCaps() throws Exception {
        SafariOptions safariOptions = new SafariOptions();
        safariOptions.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("enableVNC", true);
        }});
        return safariOptions;
        //RemoteWebDriver driver = new RemoteWebDriver(new URL("http://moon.aerokube.local:4444/wd/hub/"), safariOptions);
        //driver.quit();
    }

    @AfterEach
    void tear() {

        if (Configuration.browser.equals("safari")) {
//            WebDriverRunner.closeWebDriver();
        }

    }
}
