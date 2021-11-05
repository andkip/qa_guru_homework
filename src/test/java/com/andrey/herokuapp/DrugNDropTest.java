package com.andrey.herokuapp;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DrugNDropTest {

    @BeforeAll
    static void setUp() {
        Configuration.startMaximized = true;
    }

    @Test
    void drugNDropTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");

        $("#columns div").shouldHave(text("A"));
        $("#column-a").dragAndDropTo($("#column-b"));
        $("#columns div").shouldHave(text("B"));
    }
}
