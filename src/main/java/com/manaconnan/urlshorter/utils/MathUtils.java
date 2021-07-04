package com.manaconnan.urlshorter.utils;

import java.util.Stack;


/**
 * @Author mazexiang
 * @CreateDate 2021/7/4
 * @Version 1.0
 */
public class MathUtils {
    private static char[] charSet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();


    /**
     *
     * @param number
     * @return
     */
    public static String decimalToBase62(long number) {
        Long rest = Math.abs(number);
        Stack<Character> stack = new Stack<Character>();
        StringBuilder result = new StringBuilder(0);
        while (rest != 0) {
            stack.add(charSet[new Long((rest - (rest / 62) * 62)).intValue()]);
            rest = rest / 62;
        }
        for (; !stack.isEmpty(); ) {
            result.append(stack.pop());
        }

        return result.toString();

    }


    /**
     * @param ident62
     * @return
     */
    private static String convertBase62ToDecimal(String ident62) {
        int decimal = 0;
        int base = 62;
        int keisu = 0;
        int cnt = 0;

        byte ident[] = ident62.getBytes();
        for (int i = ident.length - 1; i >= 0; i--) {
            int num = 0;
            if (ident[i] > 48 && ident[i] <= 57) {
                num = ident[i] - 48;
            } else if (ident[i] >= 65 && ident[i] <= 90) {
                num = ident[i] - 65 + 10;
            } else if (ident[i] >= 97 && ident[i] <= 122) {
                num = ident[i] - 97 + 10 + 26;
            }
            keisu = (int) Math.pow((double) base, (double) cnt);
            decimal += num * keisu;
            cnt++;
        }
        return String.format("%08d", decimal);
    }

}
