package com.senne.oneiros.tools.byteUtils;

public class DoubleToByteArray {

    public static byte[] toByteArray(double value) {
        long longBits = Double.doubleToRawLongBits(value);
        byte[] bytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte) ((longBits >> (8 * i)) & 0xFF);
        }
        return bytes;
    }

    public static double convertByteArrayToDouble(byte[] doubleBytes){
        long longBits = 0;
        for (int i = 0; i < 8; i++) {
            longBits |= ((long) doubleBytes[i] & 0xFF) << (8 * i);
        }
        return Double.longBitsToDouble(longBits);
    }
}
