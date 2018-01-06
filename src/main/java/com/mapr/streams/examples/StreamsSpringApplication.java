package com.mapr.streams.examples;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Log4jAppender which would write to MapR-Streams
 *
 */
@SpringBootApplication
public class StreamsSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(StreamsSpringApplication.class, args);
    }
}