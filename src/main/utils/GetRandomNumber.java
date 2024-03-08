package main.utils;

import java.util.Random;

public class GetRandomNumber {

    public static String getRandomNumber(int length) {
        Random random = new Random();
        StringBuilder randomNumber = new StringBuilder();

        for (int i = 0; i < length; i++) {
            randomNumber.append(random.nextInt(10));
        }
        return randomNumber.toString();
    }
}
