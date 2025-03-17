package com.senne.oneiros.tools.utils;

public class StringUtils {

    public static boolean containsOnlyLetters(String str) {
        return str.matches("^[a-zA-Z]*$");
    }

}
