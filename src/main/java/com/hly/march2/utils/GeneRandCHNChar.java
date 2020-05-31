package com.hly.march2.utils;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * 随机生成常见的汉字
 *
 */
public class GeneRandCHNChar {
    public static void main(String[] args) {
        for (int i = 1; i < 24; i++) {
            System.out.print(getRandomChar() + " ");
        }
    }
    private static char getRandomChar() {
        String str = "";
        int hightPos; //
        int lowPos;
        Random random = new Random();
        hightPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = (161 + Math.abs(random.nextInt(93)));
        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(hightPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();
        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("错误");
        }
        return str.charAt(0);
    }
}