package com.demo.mybits.utils;

/**
 * @author lichaoshuai
 * Created on 2022-01-26
 */
public class EncryptUtils {
    private static final int PASSWORD = 6;

    public static String decrypt(String encryptData) {
        char[] charArray = encryptData.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] -= PASSWORD;
        }
        return new String(charArray);
    }

    public static String encrypt(String original) {
        char[] charArray = original.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] += PASSWORD;
        }
        return new String(charArray);
    }

}
