package com.loraneo.springboot.demo.bll;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loraneo.springboot.demo.model.Command;

import io.vavr.control.Try;

@Component
public class ComandConsumer {

  @Autowired CommandService commadnService;

  private CountDownLatch latch = new CountDownLatch(1);

  public CountDownLatch getLatch() {
    return latch;
  }

  @KafkaListener(topics = "commands")
  public void receive(ConsumerRecord<?, ?> command) {
    Try.run(() -> handle(command.value().toString()))
        .onFailure(
            p -> {
              throw new RuntimeException(p.getSuppressed()[0]);
            });
    latch.countDown();
  }

  private void handle(String object) throws IOException, JsonParseException, JsonMappingException {
    commadnService.execute(new ObjectMapper().readValue(object.toString(), Command.class));
  }
}
