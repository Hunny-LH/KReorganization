/*
 * Copyright 2015 the SiChuan MiaoQu Technology Co., Ltd.
 *
 * 四川妙趣科技有限公司    http://www.miaoqutech.com
 * 
 * Create Date :  2016/7/15
 */
package com.github.hunnylh.structure;

/**
 * 类的继承
 *
 * @author <a href="mailto:lh@miaoqutech.com">刘涵</a>
 */
public class ExtendIntroduce {
    /**
     * 继承: 从一个原始的类中扩展出更为具象的特性而形成新的类  -- 多态的表现
     */
    public static class Computer {
        /**
         * 计算机:
         * 基础属性 : 主机, 输出, 输入
         * 基础行为 : 开机, 关机, 计算
         */
        public String main;
        public String input;
        public String output;

        public void on() {
            System.out.println("开机");
        }

        public void off() {
            System.out.println("关机");
        }

        public void compute() {
            System.out.println("计算");
        }
    }

    public static class NoteBook extends Computer {
        /**
         * 笔记本
         * 基础属性: 比计算机多了可拆卸电源
         * 基础行为: 比计算机多了充电, 电池用尽
         */
        public String battery;

        @Override
        public void on() { //子类继承了父类的行为, 同时也可以有一些父类不具有的特性, 这时即为重写 -- 多态的表现
            super.on();
            recharge();
        }

        public void recharge() { //子类特有的行为
            System.out.println("充电");
            System.out.println("充满");
        }

        public void chargeOut() {
            System.out.println("电量耗尽");
            off();
        }
    }

    /**
     * final 标记的类不能被继承
     */


    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.on();
        computer.off();
        System.out.println("====================");
        NoteBook noteBook = new NoteBook();
        noteBook.on();
        noteBook.chargeOut();
        System.out.println("====================");

        //子类转型成父类
        Computer computer1 = noteBook;
        computer1.on();
        computer1.off();

        //父类向子类转型
        try {
            NoteBook noteBook1 = (NoteBook) computer;
        } catch (ClassCastException e) {
            System.out.println("父类不能直接或强制转成子类");
        }

    }
}
