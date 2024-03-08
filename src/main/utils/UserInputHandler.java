package main.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UserInputHandler {
    public static boolean askUserIfOkToSave() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Is it okay to save?  yes/no: ");
        try {
            String input = reader.readLine();
            return input != null && input.trim().equalsIgnoreCase("yes");
        } catch (IOException e) {
            return false;
        }
    }
}
