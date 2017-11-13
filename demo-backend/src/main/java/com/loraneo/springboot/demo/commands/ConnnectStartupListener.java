package com.loraneo.springboot.demo.commands;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import io.vavr.control.Option;
import io.vavr.control.Try;

public class ConnnectStartupListener {

  @EventListener(ContextRefreshedEvent.class)
  public void contextRefreshedEvent() {
    Option.of(new RetryTemplate())
        .map(this::setPolicy)
        .map(p -> p.execute(v -> tryCreatingContext()));
  }

  private RetryTemplate setPolicy(RetryTemplate template) {
    FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
    fixedBackOffPolicy.setBackOffPeriod(2000l);
    template.setBackOffPolicy(fixedBackOffPolicy);

    SimpleRetryPolicy policy = new SimpleRetryPolicy();
    template.setRetryPolicy(policy);
    return template;
  }

  private int tryCreatingContext() {
    return Try.of(Request.Get("http://kafka-connect:8083/connectors")::execute)
        .mapTry(Response::returnResponse)
        .map(HttpResponse::getStatusLine)
        .map(StatusLine::getStatusCode)
        .filter(p -> p == 200)
        .map(this::createConnection)
        .getOrElseThrow(p -> new RuntimeException("Unable to create connector! Code: " + p));
  }

  private int createConnection(int statusCode) {
    return Option.of(Request.Post("http://kafka-connect:8083/connectors"))
        .map(this::setBody)
        .toTry()
        .mapTry(Request::execute)
        .mapTry(Response::returnResponse)
        .map(HttpResponse::getStatusLine)
        .map(StatusLine::getStatusCode)
        .filter(p -> p == 200)
        .getOrElseThrow(p -> new RuntimeException("Unable to create connector! Code: " + p));
  }

  private Request setBody(Request request) {
    return request.bodyStream(getClass().getResourceAsStream("/config/debezium-postgres.json"));
  }
}
