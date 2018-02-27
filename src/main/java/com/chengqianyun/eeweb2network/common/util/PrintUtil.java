package com.chengqianyun.eeweb2network.common.util;


import java.util.Date;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 18/2/27
 */

public class PrintUtil {

  public static void println(String content) {
    System.out.println(DateUtil.format(new Date()) + ":  " + content);
  }
}