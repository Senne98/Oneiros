package com.senne.oneiros.tools;

public class DoubleUtils {

    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static byte[] toByteArray(double value) {
        long longBits = Double.doubleToRawLongBits(value);
        byte[] bytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte) ((longBits >> (8 * i)) & 0xFF);
        }
        return bytes;
    }

    public static double fromByteArray(byte[] doubleBytes){
        long longBits = 0;
        for (int i = 0; i < 8; i++) {
            longBits |= ((long) doubleBytes[i] & 0xFF) << (8 * i);
        }
        return Double.longBitsToDouble(longBits);
    }
}
