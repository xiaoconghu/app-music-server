package com.app.music.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {
    /**
     * Md5
     * @param plainText Md5
     * @return Md5
     */
    public static String encode(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] bb = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < bb.length; offset++) {
                i = bb[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}

