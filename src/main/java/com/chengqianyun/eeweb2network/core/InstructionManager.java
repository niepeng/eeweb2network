package com.chengqianyun.eeweb2network.core;


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
   * 生成获取设备信息的指令
   * @param address
   * @return
   */
  public static String genGetInfo(String address) {
    return address;
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