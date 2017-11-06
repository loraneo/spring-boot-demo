package com.loraneo.springboot.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.loraneo.hibernate.type.json.JsonBinaryType;

@Entity
@Table(schema = "demo", name = "command_store")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class CommandStore {

  @Id Long id;

  @Column(name = "payload")
  @Type(type = "jsonb")
  private Command payload;

  public Long getId() {
    return this.id;
  }

  public Command getPayload() {
    return this.payload;
  }

  public static Builder builder() {
    return new CommandStore().new Builder();
  }

  public class Builder {
    public Builder id(Long id) {
      CommandStore.this.id = id;
      return this;
    }

    public Builder payload(Command payload) {
      CommandStore.this.payload = payload;
      return this;
    }

    public CommandStore build() {
      return CommandStore.this;
    }
  }
}
