package ru.netology.test;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    Faker faker = new Faker();
    Status stat = new Status();
    private  String login;
    private  String password;
    private  String status;

    public User(Faker faker, Status stat) {
        this.login = faker.name().firstName();
        this.password = faker.lorem().characters(8);
        this.status = stat.getGenerateStatus();
    }

}
