package ru.netology.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.test.UserGenerator.getUser;
import static ru.netology.test.UserGenerator.registerUser;

public class TestMode {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    void successAuthorization() {
        var registerUs = registerUser("active");
        $("[data-test-id='login'] .input__control").sendKeys(registerUs.getLogin());
        $("[data-test-id='password'] .input__control").sendKeys(registerUs.getPassword());
        $("[data-test-id='action-login']").click();
        $(withText("Личный кабинет")).shouldBe(Condition.visible);
    }

    @Test
    void notSuccessAuthorization() {
        var user = getUser("active");
        $("[data-test-id='login'] .input__control").sendKeys(user.getLogin());
        $("[data-test-id='password'] .input__control").sendKeys(user.getPassword());
        $("[data-test-id='action-login']").click();
        $(withText("Неверно указан логин или пароль")).shouldBe(Condition.visible);
    }

    @Test
    void blockedUser() {
        var registerUs = registerUser("blocked");
        $("[data-test-id='login'] .input__control").sendKeys(registerUs.getLogin());
        $("[data-test-id='password'] .input__control").sendKeys(registerUs.getPassword());
        $("[data-test-id='action-login']").click();
        $(withText("Пользователь заблокирован")).shouldBe(Condition.visible);
    }
    @Test
    void wrongLogin(){
        var user = registerUser("active");
        $("[data-test-id='login'] .input__control").sendKeys("Kolya");
        $("[data-test-id='password'] .input__control").sendKeys(user.getPassword());
        $("[data-test-id='action-login']").click();
        $(withText("Неверно указан логин или пароль")).shouldBe(Condition.visible);
    }

    @Test
    void wrongPassword(){
        var user = registerUser("active");
        $("[data-test-id='login'] .input__control").sendKeys(user.getLogin());
        $("[data-test-id='password'] .input__control").sendKeys("password");
        $("[data-test-id='action-login']").click();
        $(withText("Неверно указан логин или пароль")).shouldBe(Condition.visible);
    }

}

