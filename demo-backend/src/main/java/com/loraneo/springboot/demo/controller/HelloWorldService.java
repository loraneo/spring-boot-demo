package com.loraneo.springboot.demo.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.loraneo.springboot.demo.dal.CommandStoreRepository;
import com.loraneo.springboot.demo.model.CommandStore;

@Component
@Transactional
public class HelloWorldService {

  @Autowired CommandStoreRepository commandStoreRepository;

  public String getHelloMessage() {
    commandStoreRepository.save(
        CommandStore.builder()
            .payload(TestCommand.builder().a("Test2").b("Test2").build())
            .build());
    return "Hello world!!!";
  }
}
