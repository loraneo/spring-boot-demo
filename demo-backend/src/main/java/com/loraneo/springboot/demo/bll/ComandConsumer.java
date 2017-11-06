package com.loraneo.springboot.demo.bll;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ComandConsumer {

  private static final Logger LOGGER = Logger.getLogger(ComandConsumer.class.getName());

  private CountDownLatch latch = new CountDownLatch(1);

  public CountDownLatch getLatch() {
    return latch;
  }

  @KafkaListener(topics = "commands")
  public void receive(ConsumerRecord<?, ?> command) {
    LOGGER.info("received payload=" +  command.toString());
    latch.countDown();
  }
}
