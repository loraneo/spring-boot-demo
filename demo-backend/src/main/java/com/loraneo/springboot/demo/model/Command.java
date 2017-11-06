package com.loraneo.springboot.demo.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonAutoDetect(
  fieldVisibility = JsonAutoDetect.Visibility.ANY,
  getterVisibility = JsonAutoDetect.Visibility.NONE,
  setterVisibility = JsonAutoDetect.Visibility.NONE,
  creatorVisibility = JsonAutoDetect.Visibility.NONE
)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")
public interface Command {}
