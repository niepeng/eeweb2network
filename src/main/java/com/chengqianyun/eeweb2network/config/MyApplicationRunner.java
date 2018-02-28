package com.chengqianyun.eeweb2network.config;


import com.chengqianyun.eeweb2network.core.ServerNormal;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 18/2/28
 */
@Order(value = Integer.MIN_VALUE)
@Component
public class MyApplicationRunner implements ApplicationRunner {

  @Override
  public void run(ApplicationArguments var1) throws Exception {
    ServerNormal.start();
  }

}