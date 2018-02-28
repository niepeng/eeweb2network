package com.chengqianyun.eeweb2network;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 17/5/16
 */
@EnableAsync
@SpringBootApplication
public class App {

  public static void main(String args []) {
    SpringApplication.run(App.class, args);
  }

}