package com.carrot.hanghae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableJpaAuditing
@SpringBootApplication
public class HanghaeApplication {
    public static void main(String[] args) {
        SpringApplication.run(HanghaeApplication.class, args);
    }

    @PostConstruct
    public void started(){ TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul")); }
}
