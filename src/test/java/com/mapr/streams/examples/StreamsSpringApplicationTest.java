package com.mapr.streams.examples;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

/**
 * Unit tests for StreamsSpringApplication
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StreamsSpringApplicationTest {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private StandaloneListener standaloneListener;

    @Test
    public void testKafkaTemplateSendAsync() throws InterruptedException {

        for(int i = 0; i < 1000; i++) {
            ListenableFuture<SendResult<String, String>> future =
                    kafkaTemplate.send(Thread.currentThread().getName(), RandomStringUtils.randomAlphabetic(10));

            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onSuccess(SendResult<String, String> result) {
                    RecordMetadata recordMetadata = result.getRecordMetadata();
                    String info = "RecordMetadata(topic=" + recordMetadata.topic() +
                            ", partition=" + recordMetadata.partition() +
                            ", keySize=" + recordMetadata.serializedKeySize() +
                            ", valueSize=" + recordMetadata.serializedValueSize() +
                            ", hasOffset=" + recordMetadata.hasOffset() +
                            ", offset=" + recordMetadata.offset() +
                            ", hasTimestamp=" + recordMetadata.hasTimestamp() +
                            ", timestamp=" + recordMetadata.timestamp() + ")";
                    System.out.println("Success: \n" + result.getProducerRecord().toString() + "\n" + info);
                }

                @Override
                public void onFailure(Throwable ex) {
                    System.out.println("Failed");
                    ex.printStackTrace();

                }
            });
        }
        System.out.println(Thread.currentThread().getId());

    }

    @Test
    public void testStreamsListener() throws InterruptedException {
        testKafkaTemplateSendAsync();
        System.out.println(Thread.currentThread().getId());
        assertThat(this.standaloneListener.countDownLatch.await(60, TimeUnit.SECONDS)).isTrue();

    }


}
