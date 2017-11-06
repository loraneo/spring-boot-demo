package com.loraneo.springboot.demo.handlers;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import com.loraneo.springboot.demo.commands.CommandHandler;
import com.loraneo.springboot.demo.commands.TestCommand;

@Component
public class TestCommandHandler implements CommandHandler<TestCommand> {

  private static final Logger LOG = Logger.getLogger(TestCommandHandler.class.getName());

  @Override
  public boolean canHandle(Class<?> command) {
      LOG.fine("Checking canHandle:  " + command.getName());
    return TestCommand.class.isAssignableFrom(command);
  }

  @Override
  public boolean handle(TestCommand command) {
    LOG.info(command.getId().toString());
    return false;
  }
}
