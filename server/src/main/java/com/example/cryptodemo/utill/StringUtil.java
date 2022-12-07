package com.example.cryptodemo.utill;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class StringUtil {
    public static String toString(byte[] text) {
        return new String(text, StandardCharsets.UTF_8);
    }

    public static String encode(byte[] text) {
        return Base64.getEncoder().encodeToString(text);
    }

    public static byte[] decode(String text) {
        return Base64.getDecoder().decode(text.getBytes(StandardCharsets.UTF_8));
    }
}
