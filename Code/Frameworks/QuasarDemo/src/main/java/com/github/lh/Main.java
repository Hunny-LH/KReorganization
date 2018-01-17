package com.github.lh;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:393803588@qq.com">HanL(liuhan3)</a>
 * @date 18-1-4
 */
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Fiber<String> fiber = new Fiber<String>() {
            private static final long serialVersionUID = -1334249561607509183L;

            @Override
            protected String run() throws SuspendExecution, InterruptedException {
                sleep(3, TimeUnit.SECONDS);
                return "hello";
            }
        }.start();
        System.out.println(fiber.get());
    }
}
