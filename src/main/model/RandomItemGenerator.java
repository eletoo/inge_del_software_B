package main.model;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class RandomItemGenerator {

    public static String generateRandomString(int size) {
        String rand = "";
        String chars = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM_-.";

        for (int i = 0; i < size; i++) {
            rand += chars.toCharArray()[new Random().nextInt(chars.length())];
        }

        return rand;
    }

    public static @NotNull String generateRandomPassword(int len) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghi"
                + "jklmnopqrstuvwxyz!@#$%&£";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
