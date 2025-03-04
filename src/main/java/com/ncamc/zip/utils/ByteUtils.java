package com.ncamc.zip.utils;

public class ByteUtils {

    public static byte[] subBytes(byte[] source, int start, int length) {
        byte[] data = new byte[length];
        System.arraycopy(source, start, data, 0, length);
        return data;
    }

}
