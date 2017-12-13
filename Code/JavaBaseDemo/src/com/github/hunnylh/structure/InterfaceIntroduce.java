/*
 * Copyright 2015 the SiChuan MiaoQu Technology Co., Ltd.
 *
 * 四川妙趣科技有限公司    http://www.miaoqutech.com
 * 
 * Create Date :  2016/7/15
 */
package com.github.hunnylh.structure;

/**
 * 接口介绍
 *
 * @author <a href="mailto:lh@miaoqutech.com">刘涵</a>
 */
public interface InterfaceIntroduce {
    /**
     * 接口: 用来定义一些行为, 或者也可以添加一些共享的静态数据
     * 并且接口中访问控制默认为public, 属性默认为static
     * 接口不能实例化, 必须依赖其实现类, 或者匿名实现类才能实现其功能
     */
    String SOME_FIELD = "DATA_TO_SHARE";

    void doSomething(String something);


    class AImpl implements InterfaceIntroduce {
        /**
         * AImpl 实现了接口, 就要重写接口中的所有方法
         *
         * @param something
         */
        @Override
        public void doSomething(String something) {
            System.out.println("do " + something);
        }
    }

    interface Archive {
        void archive(String something);
    }

    class BImpl implements InterfaceIntroduce, Archive {
        /**
         * 一个类可以实现多个接口, 只要将所有接口的方法都重写
         */

        @Override
        public void archive(String something) {
            System.out.println("archive " + something);
        }

        @Override
        public void doSomething(String something) {
            System.out.println("do " + something);
        }
    }

    class CImpl extends AImpl implements Archive {
        /**
         * 一个类可以同时继承于其他类并且实现某个接口
         */

        @Override
        public void archive(String something) {
            System.out.println("archive " + something);
        }
    }

    static void main(String[] args) {
        System.out.println(InterfaceIntroduce.SOME_FIELD);
        InterfaceIntroduce a = new AImpl();
        a.doSomething("上班");

        BImpl b = new BImpl();
        b.doSomething("上班");
        b.archive("工资");

        CImpl c = new CImpl();
        c.doSomething("上班");
        c.archive("工资");
    }
}
