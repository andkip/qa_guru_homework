package com.andrey.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DemoQAFormTest {
    //test data
    String firstName = "FirstName";
    String lastName = "LastName";
    String userEmail = "user@mail.ru";
    String userNumber = "8005553535";
    String date = "25 December,2021";
    String gender = "Male";
    String firstLetter = "a";
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
        open("https://demoqa.com/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $(byText(gender)).click();
        $("#userNumber").setValue(userNumber);
        fillDate(date);
        chooseSubject(firstLetter, subject);
        $(byText(hobby)).click();
        $("#uploadPicture").uploadFromClasspath("pict.jpg");
        $("#currentAddress").setValue(currentAddress);
        $("#react-select-3-input").setValue(state).pressEnter();
        $("#react-select-4-input").setValue(city).pressEnter();
        $("#submit").click();

        checkField("Student Name", firstName + " " + lastName);
        checkField("Student Email", userEmail);
        checkField("Gender", gender);
        checkField("Mobile", userNumber);
        checkField("Date of Birth", date);
        checkField("Subjects", subject);
        checkField("Hobbies", hobby);
        checkField("Picture", "pict.jpg");
        checkField("Address", currentAddress);
        checkField("State and City", state + " " + city);

    }

    private void fillDate(String date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMMM,u", Locale.ENGLISH);
        LocalDate parsedDate = LocalDate.parse(date, dateFormatter);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue(String.valueOf(parsedDate.getMonthValue()-1));
        $(".react-datepicker__year-select").selectOptionByValue(String.valueOf(parsedDate.getYear()));
        $$(".react-datepicker__day").find(text(String.valueOf(parsedDate.getDayOfMonth()))).click();
    }

    private void chooseSubject(String firstLetter, String subject) {
        $("#subjectsInput").setValue(firstLetter);
        $(byText(subject)).click();
    }

    private void checkField(String field, String value) {
        $x(("//td[text()='" + field + "']/following-sibling::td")).shouldHave(text(value));
    }
}
