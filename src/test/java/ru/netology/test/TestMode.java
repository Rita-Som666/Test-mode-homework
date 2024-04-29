package ru.netology.test;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TestMode {

    UserGenerator userGenerator = new UserGenerator();
    User user = userGenerator.generateUser();
    Faker faker = new Faker();
    Status stat = new Status();
    private static RequestSpecification requestSpec;

    @BeforeAll
    public static void setUp() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost:9999") // ваш базовый URL
                .setContentType(ContentType.JSON) // отправка JSON
                .build();
        RestAssured.requestSpecification = requestSpec;
    }

        @Test
         void setUpAll() {
            // сам запрос
            given()
                    .spec(requestSpec)
                    .body(new User(faker, stat)) // создаем объект User с использованием Faker и статуса
                    .when()
                    .post("/api/system/users")
                    .then()
                    .statusCode(200);
        }
}