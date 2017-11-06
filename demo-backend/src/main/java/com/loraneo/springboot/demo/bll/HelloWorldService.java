package com.loraneo.springboot.demo.bll;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.loraneo.springboot.demo.commands.TestCommand;
import com.loraneo.springboot.demo.dal.CommandStoreRepository;
import com.loraneo.springboot.demo.db.IdGenerator;
import com.loraneo.springboot.demo.model.CommandStore;

@Component
@Transactional
public class HelloWorldService {

  @Autowired CommandStoreRepository commandStoreRepository;

  @Autowired IdGenerator idGenerator;

  public String getHelloMessage() {
    createTestCommand(idGenerator.next());
    return "Hello world!!!";
  }

  private void createTestCommand(Long id) {
    commandStoreRepository.save(
        CommandStore.builder()
            .id(id)
            .payload(TestCommand.builder().id(id).a("Test2").b("Test2").build())
            .build());
  }
}
