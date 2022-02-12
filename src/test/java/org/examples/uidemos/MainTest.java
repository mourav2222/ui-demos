package org.examples.uidemos;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.nio.channels.SelectableChannel;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class MainTest {

    @BeforeEach
    void setUp() {
        Configuration.browser = "firefox";
        Configuration.remote = "http://192.168.0.158:4444/wd/hub";
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("enableVNC", "true");
        Configuration.browserCapabilities = cap;
    }

    @Test
    void todoMvc() {

        open("https://todomvc.com/examples/angularjs/");

        $("input.new-todo").sendKeys("Show Selenide");
        $("input.new-todo").pressEnter();

        $(withText("Show Selenide"))
                .shouldBe(visible);
    }
}
