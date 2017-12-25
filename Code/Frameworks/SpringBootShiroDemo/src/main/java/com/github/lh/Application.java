package com.github.lh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author <a href="mailto:393803588@qq.com">HanL(liuhan3)</a>
 * @date 17-12-25
 */
@SpringBootApplication
@EnableWebMvc
@ComponentScan(basePackages = {"com.github.lh"})
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
