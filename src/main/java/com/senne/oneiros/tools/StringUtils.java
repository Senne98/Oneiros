package com.senne.oneiros.tools;

public class StringUtils {

    public static boolean containsOnlyLetters(String str) {
        return str.matches("^[a-zA-Z]*$");
    }

}
