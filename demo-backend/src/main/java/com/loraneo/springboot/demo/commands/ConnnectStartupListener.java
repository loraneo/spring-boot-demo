package com.loraneo.springboot.demo.commands;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

public class ConnnectStartupListener {

  @EventListener(ContextRefreshedEvent.class)
  public void contextRefreshedEvent() {
      // Here configure kafka connect
  }
}
