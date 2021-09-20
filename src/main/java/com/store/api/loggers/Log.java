package com.store.api.loggers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
  private String eventDate;
  private Object request;
  private Object response;
  private Object application;
  private Object method;

  public Log(Object request, Object response, String application, String method) {
    this.request = request;
    this.response = response;
    this.application = application;
    this.method = method;
    this.eventDate = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
  }

  public String getEventDate() {
    return this.eventDate;
  }

  public Object getRequest() {
    return this.request;
  }

  public Object getResponse() {
    return this.response;
  }

  public Object getApplication() {
    return this.application;
  }

  public Object getMethod() {
    return this.method;
  }
}
