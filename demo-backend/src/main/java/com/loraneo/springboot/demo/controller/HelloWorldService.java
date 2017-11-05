package com.loraneo.springboot.demo.controller;

import org.springframework.stereotype.Component;

@Component
public class HelloWorldService {

	public String getHelloMessage() {
		return "Hello world!!!";
	}

	
}
