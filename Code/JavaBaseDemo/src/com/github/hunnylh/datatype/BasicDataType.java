/*
 * Copyright 2015 the SiChuan MiaoQu Technology Co., Ltd.
 *
 * 四川妙趣科技有限公司    http://www.miaoqutech.com
 * 
 * Create Date :  2016/7/15
 */
package com.github.hunnylh.datatype;

/**
 * java 基本数据类型
 *
 * @author <a href="mailto:lh@miaoqutech.com">刘涵</a>
 */
public class BasicDataType {

    public static void main(String[] args) {
        //1. 基本数据类型, byte, short, int, long, double, float, boolean, char(八种)

        //byte, 字节, 数据的基本单位, 对象物理大小为8位
        byte a = 2;
        System.out.println(a);
        //范围 -128~127 (-2^(8-1)-1 ~ 2^(8-1)-1)
        System.out.println("byte max: " + Byte.MAX_VALUE);
        System.out.println("byte min: " + Byte.MIN_VALUE);

        //short, 短整型, 对象物理大小为2个字节,16位
        short b = 1;
        System.out.println(b);
        //范围 -32768~32767 (-2^(16-1)-1 ~ 2^(16-1)-1)
        System.out.println("short max: " + Short.MAX_VALUE);
        System.out.println("short min: " + Short.MIN_VALUE);

        //int, 整型, 对象物理大小为4个字节, 32位
        int c = 1111111111;
        System.out.println(c);
        //范围 -2147483648~2147483647 (-2^(32-1)-1 ~ 2^(32-1)-1)
        System.out.println("int max: " + Integer.MAX_VALUE);
        System.out.println("int min: " + Integer.MIN_VALUE);

        //long, 长整型, 对象物理大小为8个字节,64位
        long d = 111111111111111111l;
        System.out.println(d);
        //范围 -9223372036854775808~9223372036854775807 (-2^(64-1)-1 ~ 2^(64-1)-1)
        System.out.println("long max: " + Long.MAX_VALUE);
        System.out.println("long min: " + Long.MIN_VALUE);

        //double, 双精度浮点型, 对象物理大小为8个字节,64位
        double e = 1111111.11111111;
        System.out.println(e);
        //范围 4.9E-324~1.7976931348623157E308 (-2^1024 ~ +2^1024, -1.79E+308 ~ +1.79E+308)
        System.out.println("double max: " + Double.MAX_VALUE);
        System.out.println("double min: " + Double.MIN_VALUE);
        //指数范围, -1023~+1024
        System.out.println(Double.MAX_EXPONENT);
        System.out.println(Double.MIN_EXPONENT);

        System.out.println(Double.MIN_NORMAL);

        //float, 单精度浮点型, 对象物理大小为4个字节, 32位
        float f = 11111.11111f;
        System.out.println(f);
        //范围 1.4E-45~3.4028235E38 (-2^128 ~ +2^128, -3.40E+38 ~ +3.40E+38 )
        System.out.println("float max: " + Float.MAX_VALUE);
        System.out.println("float min: " + Float.MIN_VALUE);
        //指数范围, -127~128
        System.out.println(Float.MAX_EXPONENT);
        System.out.println(Float.MIN_EXPONENT);

        System.out.println(Float.MIN_NORMAL);

        //char, 字符类型, 对象物理大小2个字节, 16位
        char g = '字';
        System.out.println(g);
        //范围 0~65535, 0 ~ 2^16-1
        System.out.println("char max: " + (long) Character.MAX_VALUE);
        System.out.println("char min: " + (long) Character.MIN_VALUE);

        //String, 字符串, java中除以上八中之外的基础数据类型
        String h = "hha";
        System.out.println(h);

        //boolean, 布尔型, 对象物理大小1个字节, 8位
        boolean i = true;
        System.out.println(i);
    }
}
