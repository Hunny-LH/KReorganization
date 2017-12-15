package com.github.hunnylh.stream;

import java.util.stream.Stream;

import static com.github.hunnylh.common.PerfUtils.perfInvoke;

/**
 * @author <a href="mailto: 393803588@qq.com">刘涵(Hanl)</a>
 * By 2017/12/15
 */
public class StreamApi {

    public static void main(String[] args) {
        perfInvoke(() -> {
            Stream.of(1)
                    .limit(10)
                    .forEach(System.out::println);
        });
//        perfInvoke(() -> {
//            Stream.generate(() -> 1)
//                    .limit(100000)
//                    .map(integer -> integer + 1)
//                    .forEach(System.out::println);
//        });
    }
}
