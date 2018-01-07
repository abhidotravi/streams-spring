package com.mapr.streams.examples;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aravi on 1/6/18.
 */
@Configuration
public class StreamsAdminConfig {

    @Bean
    public KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "10.10.100.189:9092");
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topic1() {
        return new NewTopic("topic1", 10, (short) 1);
    }

    @Bean
    public NewTopic topic2() {
        return new NewTopic("topic2", 10, (short) 1);
    }

    @Bean public NewTopic adminTopic() {
        return new NewTopic("adminTopic", 2, (short) 1);
    }

}
