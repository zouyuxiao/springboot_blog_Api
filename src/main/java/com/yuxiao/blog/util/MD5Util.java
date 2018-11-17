package com.yuxiao.blog.util;

import java.security.MessageDigest;

/**
 * Created by ${邹} on 2018/10/10.
 */
public class MD5Util {
    private static final String SALT = "yuxiao";
    private static final String ZHUOYUE = "zhuoyue";
    private static final String SHU = "123456789zxcvbnmasdfghjklqwertyuiop;'p]";

    public static String encode(String password) {
        password = password + SALT + ZHUOYUE + SHU;
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        char[] charArray = password.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }

            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static void main(String[] args) {
        System.out.println( MD5Util.encode("123456"));
    }
}
