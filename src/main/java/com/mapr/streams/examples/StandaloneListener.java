package com.mapr.streams.examples;
/**
 * Created by aravi on 1/4/18.
 */


import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

public class StandaloneListener {

    public final CountDownLatch countDownLatch = new CountDownLatch(1);

    @KafkaListener(id = "count",
            topics = "topic1",
            groupId = "countGrp")
    public void listen (ConsumerRecord<?, ?> record) {
        System.out.println(record);
        countDownLatch.countDown();
    }
}
