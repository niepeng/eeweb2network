package com.chengqianyun.eeweb2network.core;


import com.chengqianyun.eeweb2network.common.util.CalcCRC;

/**
 * 所有生成和解析指令方法结合
 *
 * @author 聂鹏
 * @version 1.0
 * @date 18/2/10
 */

public class InstructionManager {

  // -------------------------------------------------------------------------------------
  /**
   * 获取设备地址指令
   * @return
   */
  public static String genGetAddress() {
    return "我要获取地址";
  }

  /**
   * 解析:获取设备地址指令
   * @param response
   * @return
   */
  public static String parseGetAddress(String response) {
    return response;
  }

  // -------------------------------------------------------------------------------------
  /**
   * 生成获取设备信息的指令(根据仪器地址发送获取 温度,湿度和露点的命令)
   * @param address 仪器地址
   * @param dataLen (3:温度,湿度,露点4个数据)(4:温度,湿度,露点,电压4个数据)
   * @return
   */
  public static char[] genGetInfo(int address, int dataLen) {
    char[] rs = {(char) address, (char) 0x03, (char) 0, (char) 0, (char) 0, (char) dataLen, 0, 0};
    rs = CalcCRC.getCrc16(rs);
    return rs;
  }

  /**
   * 解析:生成获取设备信息的指令
   * @param response
   * @return
   */
  public static String parseGetInfo(String response) {
    return response;
  }

  // -------------------------------------------------------------------------------------

}