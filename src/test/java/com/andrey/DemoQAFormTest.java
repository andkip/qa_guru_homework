package com.andrey;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class DemoQAFormTest {
    //test data
    String firstName = "FirstName";
    String lastName = "LastName";
    String userEmail = "user@mail.ru";
    String userNumber = "8005553535";
    String gender = "Male";
    String subject = "Arts";
    String hobby = "Music";
    String currentAddress = "New York, ulitsa Stroitelei, 21a";
    String state = "NCR";
    String city = "Noida";

    @BeforeAll
    static void setUp() {
        Configuration.startMaximized = true;
    }

    @Test
    void selenideSearchTest() {
        File kek = new File("src/test/java/resources/кек.jpg");
        open("https://demoqa.com/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $(byText(gender)).click();
        $("#userNumber").setValue(userNumber);
        fillDate("25", "9", "2021");
        chooseSubject("a", subject);
        $(byText(hobby)).click();
        $("#uploadPicture").uploadFile(kek);
        $("#currentAddress").setValue(currentAddress);
        $("#react-select-3-input").setValue(state).pressEnter();
        $("#react-select-4-input").setValue(city).pressEnter();
        $("#submit").click();

        checkField("Student Name", firstName + " " + lastName);
        checkField("Student Email", userEmail);
        checkField("Gender", gender);
        checkField("Mobile", userNumber);
        checkField("Date of Birth", "25 October,2021");
        checkField("Subjects", subject);
        checkField("Hobbies", hobby);
        checkField("Picture", "кек.jpg");
        checkField("Address", currentAddress);
        checkField("State and City", state + " " + city);

    }

    private void fillDate(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue(month);
        $(".react-datepicker__year-select").selectOptionByValue(year);
        $$(".react-datepicker__day").find(text(day)).click();
    }

    private void chooseSubject(String firstLetter, String subject) {
        $("#subjectsInput").setValue(firstLetter);
        $(byText(subject)).click();
    }

    private void checkField(String field, String value) {
        $(byXpath("//td[text()='" + field + "']/following-sibling::td")).shouldHave(matchText(value));
    }
}
