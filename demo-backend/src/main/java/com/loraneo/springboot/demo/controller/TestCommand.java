package com.loraneo.springboot.demo.controller;

import com.loraneo.springboot.demo.model.Command;

public class TestCommand implements Command {

  private String a;

  private String b;

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
