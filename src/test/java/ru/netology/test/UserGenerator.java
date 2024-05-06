package ru.netology.test;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import lombok.Value;


import java.util.Locale;

import static io.restassured.RestAssured.given;


public class UserGenerator {
    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    private static final Faker faker = new Faker(new Locale("en"));

    private UserGenerator() {
    }

    @Value
    public static class UserDto {
        String login;
        String password;
        String status;


    }

    private static UserDto sendReq(UserDto user) {
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(user) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
        return user;
    }

    public static String randomLogin() {
        return faker.name().firstName();
    }

    public static String randomPassword() {
        return faker.lorem().characters(8);
    }

    public static UserDto getUser(String status) {
        return new UserDto(randomLogin(), randomPassword(), status);
    }

    public static UserDto registerUser(String status) {
        return sendReq(getUser(status));
    }

}