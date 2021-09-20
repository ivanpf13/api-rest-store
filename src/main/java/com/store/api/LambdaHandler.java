package com.store.api;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import org.springframework.web.WebApplicationInitializer;

import java.io.*;

public class LambdaHandler implements RequestStreamHandler {
  static {
    try {
      initHandler(SpringBootMongoDbApplication.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
  @Override
  public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
    startHandler(inputStream, outputStream, context);
  }
  public static void initHandler(Class<? extends WebApplicationInitializer> applicationClass) throws Exception {
    try {
      handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(applicationClass);
    } catch (ContainerInitializationException var3) {
      String errMsg = "Could not initialize Spring Boot application";
      System.out.println(errMsg);
      throw new RuntimeException("Could not initialize Spring Boot application", var3);
    }
  }
  protected ByteArrayOutputStream cloneInputStream(InputStream inputStream) {
    try {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      byte[] buffer = new byte[1024];

      int len;
      while((len = inputStream.read(buffer)) > -1) {
        baos.write(buffer, 0, len);
      }

      baos.flush();
      return baos;
    } catch (Exception var5) {
      var5.printStackTrace();
      return null;
    }
  }
  public void startHandler(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
    ByteArrayOutputStream baos = this.cloneInputStream(inputStream);
    handler.proxyStream(new ByteArrayInputStream(baos.toByteArray()), outputStream, context);
    outputStream.close();
  }
}
