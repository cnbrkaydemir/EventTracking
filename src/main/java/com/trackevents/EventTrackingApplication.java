package com.trackevents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.trackevents.repository")
public class EventTrackingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventTrackingApplication.class, args);
    }

}
