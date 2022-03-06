package org.examples.uidemos;

import com.codeborne.selenide.Browser;
import com.codeborne.selenide.Config;
import com.codeborne.selenide.webdriver.SafariDriverFactory;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.safari.SafariOptions;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.util.HashMap;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;


public class MySafariWebDriverFactory extends SafariDriverFactory {

    @Nonnull
    @CheckReturnValue
    @Override
    public SafariOptions createCapabilities(Config config, Browser browser,
                                            @Nullable Proxy proxy, @Nullable File browserDownloadsFolder) {
        SafariOptions options = new SafariOptions();
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("enableVNC", true);
            put("enableVideo", true);
        }});

        if (config.headless()) {
            throw new IllegalArgumentException("headless browser not supported in Safari. Set headless property to false.");
        }
        if (isNotEmpty(config.browserBinary())) {
            throw new IllegalArgumentException("browser binary path not supported in Safari. Reset browserBinary setting.");
        }
        options.merge(createCommonCapabilities(new SafariOptions(), config, browser, proxy));
        return options;
    }

}
