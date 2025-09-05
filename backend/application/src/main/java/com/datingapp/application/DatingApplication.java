package com.datingapp.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.datingapp")
public class DatingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatingApplication.class, args);
    }

}
