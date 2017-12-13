/*
 * Copyright 2015 the SiChuan MiaoQu Technology Co., Ltd.
 *
 * 四川妙趣科技有限公司    http://www.miaoqutech.com
 *
 * Create Date :  2016/7/15
 */
package com.github.hunnylh.datatype;

/**
 * java 基本类型转换
 *
 * @author <a href="mailto:lh@miaoqutech.com">刘涵</a>
 */
public class DataCast {
    /**
     * 基本数值类型之间, 可以从小到大向上自动转型 或强制转型
     */
    private static void autoCast() {
        byte a = '1';
        //转型到short
        short b = a;
        print(short.class, b);
        //转型到int
        int c = a;
        print(int.class, c);
        //转型到long
        long d = a;
        print(long.class, d);
        //转型到double
        double e = a;
        print(double.class, e);
        //转型到float
        float f = a;
        print(float.class, f);
    }

    /**
     * 数值类型从大到小向下可以强制转型, 但可能损失精度
     */
    private static void forceCast() {
        long a = 1234567890123456789L;
        //转型到int
        int b = (int) a;
        print(int.class, b);
        //转型到short
        short c = (short) a;
        print(short.class, c);
        //转型到byte
        byte d = (byte) a;
        print(byte.class, d);
        //转型到char
        char e = (char) a;
        print(char.class, e);
    }

    /**
     * 通过基本类型的封装类型转型
     */
    private static void basicObjCast() {
        String a = "23";
        int b = Integer.parseInt(a);
        print(int.class, b);
        short c = Short.parseShort(a);
        print(short.class, c);
        //...
    }

    public static void main(String[] args) {
        autoCast();
        System.out.println("===============================");
        forceCast();
        System.out.println("===============================");
        basicObjCast();
    }

    private static <T> void print(Class<T> cls, T t) {
        System.out.println("转型到" + cls + ": " + t);
    }
}
