package com.chengqianyun.eeweb2network.test;

import com.chengqianyun.eeweb2network.common.util.SystemClock;
import com.chengqianyun.eeweb2network.core.ServerNormal;
import java.io.IOException;
import java.util.Random;

/**
 * @author 聂鹏
 * @version 1.0
 * @email lsb@51huadian.cn
 * @date 18/2/10
 */

public class TestSocket {

  //测试主方法
  public static void main(String[] args) throws InterruptedException {
    //运行服务器
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          ServerNormal.start();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }).start();
    //避免客户端先于服务器启动前执行代码
    Thread.sleep(100);

    //运行客户端
    char operators[] = {'+','-','*','/'};
    Random random = new Random(System.currentTimeMillis());

    for (int i = 1; i < 4; i++) {
      final String address = String.valueOf(i);
      SystemClock.sleepRandom(2000, 5000);
      new Thread(new Runnable() {
        @SuppressWarnings("static-access")
        @Override
        public void run() {
          Client  client = new Client(address);

          while(true) {
            //随机产生算术表达式
            String expression = random.nextInt(10) + "" + operators[random.nextInt(4)] + (random.nextInt(10) + 1);
            client.send(expression);
          }
        }
      }).start();
    }

    SystemClock.sleep(1000 * 3600);
  }



}