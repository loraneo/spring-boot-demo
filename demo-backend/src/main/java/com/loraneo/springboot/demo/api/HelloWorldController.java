package com.loraneo.springboot.demo.api;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loraneo.springboot.demo.bll.HelloWorldService;

@RestController
public class HelloWorldController {

	@Autowired
	private HelloWorldService helloWorldService;

	@GetMapping("/")
	public String helloWorld() {
		return this.helloWorldService.getHelloMessage();
	}

	@GetMapping("/async")
	public Callable<String> helloWorldAsync() {
		return new Callable<String>() {

			@Override
			public String call() throws Exception {
				return "async: " + HelloWorldController.this.helloWorldService.getHelloMessage();
			}

		};

	}

}
