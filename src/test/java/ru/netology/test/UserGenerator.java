package ru.netology.test;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Locale;
@Data
@AllArgsConstructor
public class UserGenerator {

        private final Faker faker;

        public UserGenerator() {
            this.faker = new Faker(new Locale("en"));
        }

        public User generateUser() {
            return new User(Faker faker, Status stat;);
        }



    }