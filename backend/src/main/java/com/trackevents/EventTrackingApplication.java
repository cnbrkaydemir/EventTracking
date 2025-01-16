package com.trackevents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScans({ @ComponentScan("com.trackevents.controller"), @ComponentScan("com.trackevents.config")})
@EnableCaching
@EnableTransactionManagement
@EnableJpaRepositories("com.trackevents.repository")
public class EventTrackingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventTrackingApplication.class, args);
    }

}
