/*
 * Copyright 2015 the SiChuan MiaoQu Technology Co., Ltd.
 *
 * 四川妙趣科技有限公司    http://www.miaoqutech.com
 * 
 * Create Date :  2016/8/5
 */
package com.github.hunnylh.structure;

import java.io.InputStream;

/**
 * 枚举类介绍
 */
public class EnumIntroduce {

    /**
     * 最简单的枚举类，就是一些常量的集合
     * 枚举具有值和顺序
     */
    enum Gender {
        MALE, FEMALE;
    }

    /**
     * 枚举类，可以有属性和方法
     * 每一个枚举量，实际上都是该枚举类的一个子类的实例对象
     * 因此每个枚举量，都可以继承枚举类定义的可继承的属性及方法， 并且也可以有自己的独有的属性和方法
     */
    enum Operator {
        STEP1("这是第一步") {
            @Override
            void next() {
                System.out.println(this);
                STEP2.next();
            }
        }, STEP2("这是第二步") {
            @Override
            void next() {
                System.out.println(this);
                STEP3.next();
            }
        }, STEP3("这是第三步") {
            @Override
            void next() {
                System.out.println(this);
                System.out.println("STEP OVER");
            }
        };

        private String msg;

        Operator(String msg) {
            this.msg = msg;
        }

        abstract void next();

        @Override
        public String toString() {
            return "Operator{" +
                    "msg='" + msg + '\'' +
                    '}';
        }
    }

    /**
     * 枚举类可以实现接口
     */
    interface IO {
        void write(byte[] bytes);

        byte[] read(InputStream inputStream);
    }
    enum IOManager implements IO {
        COMPUTER {
            @Override
            public void write(byte[] bytes) {
                System.out.println("COMPUTER 输入");
            }

            @Override
            public byte[] read(InputStream inputStream) {
                System.out.println("COMPUTER 输出");
                return new byte[0];
            }
        };
    }

    /**
     * 因为每一个枚举量都是枚举类的一个子类的实例对象，所以通过枚举类也可以实现单例模式
     */
    enum Singleton {
        //单例对象的引用
        INSTANCE;

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void doSomething() {
            System.out.println("我是一个单例对象");
            System.out.println("我的值是： " + this.value);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        //枚举量可以实现一些输入的限定
        System.out.println(Gender.MALE);
        //枚举量默认可以根据名称索引
        System.out.println(Gender.valueOf("FEMALE"));

        Operator.STEP1.next();

        IOManager.COMPUTER.write(null);
        IOManager.COMPUTER.read(null);

        Singleton a = Singleton.INSTANCE;
        a.setValue("liuhan");
        a.doSomething();

        Singleton b = Singleton.INSTANCE;
        b.doSomething();

        b.setValue("lh");
        b.doSomething();
        a.doSomething();


    }
}
