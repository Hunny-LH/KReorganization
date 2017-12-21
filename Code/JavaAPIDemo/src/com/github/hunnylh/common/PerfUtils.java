package com.github.hunnylh.common;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto: 393803588@qq.com">刘涵(Hanl)</a>
 * By 2017/12/15
 */
public class PerfUtils {

    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void perfInvoke(PerfedProcess process) {
        System.out.println(String.format("-- #%d -----------", counter.incrementAndGet()));
        LocalDateTime start = LocalDateTime.now();
        process.accept();
        LocalDateTime end = LocalDateTime.now();
        System.out.println(String.format("Cost time: %s", Duration.between(start, end)));
    }

    public interface PerfedProcess {
        void accept();
    }
}
