package com.loraneo.springboot.demo.commands;

import com.loraneo.springboot.demo.model.Command;

public interface CommandHandler<T extends Command> {

  public boolean canHandle(Class<?> command);

  public boolean handle(T command);
}
