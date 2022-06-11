package com.example.zmusic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ZmusicApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZmusicApplication.class, args);
    }
}
