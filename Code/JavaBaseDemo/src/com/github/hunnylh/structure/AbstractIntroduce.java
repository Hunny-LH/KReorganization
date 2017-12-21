/*
 * Copyright 2015 the SiChuan MiaoQu Technology Co., Ltd.
 *
 * 四川妙趣科技有限公司    http://www.miaoqutech.com
 * 
 * Create Date :  2016/7/15
 */
package com.github.hunnylh.structure;

/**
 * 抽象类介绍
 *
 * @author <a href="mailto:lh@miaoqutech.com">刘涵</a>
 */
public class AbstractIntroduce {

    static abstract class Parent {

        /**
         * 抽象类: 首先它是一个类, 所以它可以有属性, 有方法
         */
        public String someField;

        public void someMethod() {
            System.out.println(someField);
        }

        /**
         * 此外，抽象类还可以具有抽象方法
         * 抽象方法是： 以abstract标识的没有方法体的方法声明，用来定义标准化的方法供其子类重写
         */
        abstract void doOverride();

    }

    static class Child extends Parent {

        /**
         * 子类继承抽象类，必须重写其父类的所有抽象方法
         */
        @Override
        void doOverride() {
            System.out.println("我覆盖了我爹的");
        }
    }

    public static void main(String[] args) {
        /**
         * 抽象类不能被实例化
         * Parent parent = new Parent();
         * 会编译报错
         * 在使用时需要使用其子类来实现它的功能
         */
        Parent a = new Child();
        a.doOverride();
    }
}
