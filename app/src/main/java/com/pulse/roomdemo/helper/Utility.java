package com.pulse.roomdemo.helper;


import java.util.Random;

/**
 * Created by nikhil.vadoliya on 22-11-2018.
 */


public class Utility {

    public static String getRandomName() {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder(6);
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
}
