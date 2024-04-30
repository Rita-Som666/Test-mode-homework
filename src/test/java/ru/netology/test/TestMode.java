package ru.netology.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;
import static ru.netology.test.UserGenerator.registerUser;

public class TestMode {
@BeforeEach
void setUp(){
    open("http://localhost:9999/");
}
    @Test
void successAuthorization(){
        var registerUs = registerUser("active");
        $("[data-test-id='login'] .input__control").sendKeys(registerUs.getLogin());
        $("[data-test-id='password'] .input__control").sendKeys(registerUs.getPassword());
        $("[data-test-id='action-login'] .button").click();
        $(withText("  Личный кабинет")).shouldBe(Condition.visible);
    }
}