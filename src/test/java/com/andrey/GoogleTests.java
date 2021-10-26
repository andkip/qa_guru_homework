package com.andrey;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GoogleTests {

    @Test
    void selenideSearchTest() {
        open("https://google.com");
        $(byName("q")).setValue("Selenide").pressEnter();
        $("#search").shouldHave(Condition.text("selenide.org"));
    }

}
