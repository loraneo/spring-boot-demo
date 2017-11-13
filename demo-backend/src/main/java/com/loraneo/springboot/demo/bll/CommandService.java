package com.loraneo.springboot.demo.bll;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.loraneo.springboot.demo.commands.CommandHandler;
import com.loraneo.springboot.demo.handlers.TestCommandHandler;
import com.loraneo.springboot.demo.model.Command;

@Component
public class CommandService {
  private static final Logger LOG = Logger.getLogger(TestCommandHandler.class.getName());

  @Autowired private List<CommandHandler<?>> handlers;

  @SuppressWarnings("unchecked")
  public boolean execute(Command command) {
    LOG.fine("Received: " + command.getClass().getName());
    return handlers
        .stream()
        .map(p -> (CommandHandler<? super Command>) p)
        .filter(p -> p.canHandle(command.getClass()))
        .findAny()
        .map(p -> p.handle(command))
        .orElse(false);
  }
}
