package ru.netology.test;

import lombok.Data;

import java.util.List;
import java.util.Random;

@Data
public class Status {
    List<String> status = List.of("active", "blocked");
    Random random = new Random();
    String generateStatus = status.get(random.nextInt(status.size()));
}
