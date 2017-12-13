/*
 * Copyright 2015 the SiChuan MiaoQu Technology Co., Ltd.
 *
 * 四川妙趣科技有限公司    http://www.miaoqutech.com
 * 
 * Create Date :  2016/7/15
 */
package com.github.hunnylh.structure;

import java.util.Map;

/**
 * 类的介绍
 *
 * @author <a href="mailto:lh@miaoqutech.com">刘涵</a>
 */
public class ClassIntroduce { //开放访问的类,  常见的方式
    /**
     * 类定义: 访问控制符[private, protect, public] 状态限定符[static, final, abstract] class关键字 className
     */
    public static class A {
    } //静态类, 仅仅出现再内部类中, 使类可以在外部静态访问而不依赖宿主类的实例进行访问

    public final class B {
    } //最终类, 标识类不可被继承

    public abstract class C {
    } //抽象类, 标识类是抽象的, 不可实例化


    /**
     * 类的作用
     */
    public static class Human {
        /**
         * 一系列具有相同特性行为的对象, 将其共性提取抽象, 得到的特征行为集合就是一个类
         * 人类为例:
         * 人共同具有的属性: name-姓名;gender-性别;age-年龄;family-家庭;...
         * 人共同具有的行为: eat-吃饭;sleep-睡觉;walk-行走;study-学习;...
         */
        //姓名
        public String name;
        //性别
        public Gender gender;
        //年龄
        public int age;
        //家庭
        private Map<String, Human> family;
        //...

        //构造方法, 定义怎么样可以成为该类的实例对象
        public Human() {
        } //默认的无参构造方法, 生成一个默认的对象

        public Human(String name, Gender gender, int age) { //带参数的构造方法, 生成一个有特定属性的对象
            this.name = name;
            this.gender = gender;
            this.age = age;
        }

        /**
         * 吃饭
         */
        public void eat() {
            System.out.println(this.name + "吃饭");
        }

        /**
         * 睡觉
         */
        public void sleep() {
            System.out.println(this.name + "睡觉");
        }

        /**
         * 行走
         */
        public void walk() {
            System.out.println(this.name + "行走");
        }

        /**
         * 学习
         */
        public void study() {
            System.out.println(this.name + "学习");
        }
        //..


        @Override
        public String toString() {
            return "Human{" +
                    "name='" + name + '\'' +
                    ", gender=" + gender +
                    ", age=" + age +
                    ", family=" + family +
                    '}';
        }
    }

    //类的实例化
    private static void instance() {
        //常见的类的实例化方式, 类型 引用 = 关键字new 类的构造方法
        ClassIntroduce introduce = new ClassIntroduce();
        System.out.println(introduce);
        //静态内部类实例化方式, 类型 (外部类.内部类) 引用 = 关键字new 类的构造方法
        ClassIntroduce.A a = new ClassIntroduce.A();
        System.out.println(a);
        //普通内部类实例化方式, 类型 (外部类.内部类) 引用 = 外部类对象引用.关键字new 内部类构造方法
        ClassIntroduce.B b = introduce.new B();
        //抽象类C无法实例化, 编码时IDE会提示错误
    }

    //类的使用
    private static void useHuman() {
        //一个类的使用, 首先要对其实例化, 得到一个具体的对象
        Human liuhan = new Human("liuhan", Gender.MAN, 0);
        System.out.println("出生:" + liuhan);
        //对象属性的使用
        liuhan.age += 28;
        System.out.println("长大:" + liuhan);
        //对象方法的使用
        liuhan.eat();
        //对象的销毁
        //并不是直接的习销毁一个对象, 而是将该对象的引用从该对象上移除, jvm会在适当的时候将没有引用的对象实体回收从而销毁
        liuhan = null;
        System.out.println(liuhan);
    }

    public static void main(String[] args) {
        //类的实例化
        instance();
        System.out.println("========================");
        //类的使用
        useHuman();
    }

    enum Gender {
        MAN, WOMAN
    }
}
