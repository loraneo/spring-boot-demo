package com.loraneo.springboot.demo.commands;

import com.loraneo.springboot.demo.model.Command;

public class TestCommand implements Command {

  private Long id;

  private String a;

  private String b;

  public Long getId() {
    return this.id;
  }

  public String getA() {
    return this.a;
  }

  public String getB() {
    return this.b;
  }

  public static Builder builder() {
    return new TestCommand().new Builder();
  }

  public class Builder {
    public Builder id(Long id) {
      TestCommand.this.id = id;
      return this;
    }

    public Builder a(String a) {
      TestCommand.this.a = a;
      return this;
    }

    public Builder b(String b) {
      TestCommand.this.b = b;
      return this;
    }

    public TestCommand build() {
      return TestCommand.this;
    }
  }
}
