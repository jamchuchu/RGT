package com.rgt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.rgt.entity")
@SpringBootApplication
public class RgtApplication {

    public static void main(String[] args) {
        SpringApplication.run(RgtApplication.class, args);
    }

}
