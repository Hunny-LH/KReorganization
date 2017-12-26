package com.github.lh;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto: 393803588@qq.com">刘涵(Hanl)</a>
 * By 2017/12/25
 */
public class MemberMointer {

    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            fillHeap(100);
            Thread.sleep(500);
            System.gc();
        }

    }
}
