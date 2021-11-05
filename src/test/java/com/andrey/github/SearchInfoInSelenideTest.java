package com.andrey.github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SearchInfoInSelenideTest {

    @BeforeAll
    static void setUp() {
        Configuration.startMaximized = true;
    }

    @Test
    void selenideSearchSoftAssertionsTest() {
        open("https://github.com/selenide/selenide");
        $("h1").shouldHave(text("selenide / selenide"));

        $("#wiki-tab").click();
        $(".js-wiki-more-pages-link").click();
        $(".wiki-rightbar").$(byText("SoftAssertions")).click();
        $(".markdown-body ol").$$("li").findBy(text("JUnit5"))
                .shouldHave(text("com.codeborne.selenide.junit5.SoftAssertsExtension"));
    }
}
