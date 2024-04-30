package ru.netology.test;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openqa.selenium.devtools.v85.serviceworker.model.RegistrationID;

import java.util.Locale;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

@Data

public class UserGenerator {
private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    private static final Faker faker = new Faker(new Locale("en"));
    private UserGenerator(){}
@Data
@AllArgsConstructor
@NoArgsConstructor
    public static class UserDto {
        private  String login;
        private  String password;
        private  String status;


    }

private static UserDto sendReq(UserDto user){
    given() // "дано"
            .spec(requestSpec) // указываем, какую спецификацию используем
            .body(user) // передаём в теле объект, который будет преобразован в JSON
            .when() // "когда"
            .post("/api/system/users") // на какой путь относительно BaseUri отправляем запрос
            .then() // "тогда ожидаем"
            .statusCode(200); // код 200 OK
    return user;
}
public static String RandomLogin(){
        return faker.name().firstName();
}

public static String RandomPassword(){
        return faker.lorem().characters(8);
}

public static UserDto getUser(String status){
        return new UserDto(RandomLogin(), RandomPassword(), status);
}

public static UserDto registerUser(String status){
        return sendReq(getUser(status));
}

}