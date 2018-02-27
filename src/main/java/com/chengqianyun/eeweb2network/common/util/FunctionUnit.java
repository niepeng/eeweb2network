package com.chengqianyun.eeweb2network.common.util;


/**
 * @author 聂鹏
 * @version 1.0
 * @date 18/2/27
 */

public class FunctionUnit {

  /**
   * @describe:	把char类型转换成16进制字符串
   * @param bArray  char类型数组
   * @date:2009-11-7
   */
  public static final String bytesToHexString(char[] bArray) {
    if(bArray == null) {
      return "";
    }
    StringBuffer sb = new StringBuffer(bArray.length);
    String sTemp;

    for (int i = 0; i < bArray.length; i++) {

      sTemp = Integer.toHexString(0xFF & bArray[i]);
      if (sTemp.length() < 2) {
        sb.append(0);
      }

      sb.append(sTemp.toUpperCase() + " ");
    }
    return sb.toString();
  }


}