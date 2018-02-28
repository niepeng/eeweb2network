package com.chengqianyun.eeweb2network.core;


import com.chengqianyun.eeweb2network.common.util.CalcCRC;
import com.chengqianyun.eeweb2network.dao.EquipDataDO;

/**
 * 所有生成和解析指令方法结合
 *
 * @author 聂鹏
 * @version 1.0
 * @date 18/2/10
 */

public class InstructionManager {

  // 读取设备的地址
  private static char[] readAddress = {
      0xFB,0x67,0x06,0x00,0x00,0x00,0x00,0x00,0x00,0x5C,0x0A
  };


  // -------------------------------------------------------------------------------------
  /**
   * 获取设备地址指令
   * @return
   */
  public static char[] genGetAddress() {
    return readAddress;
  }

  /**
   * 解析:获取设备地址指令
   * @param responses
   * @return
   */
  public static int parseGetAddress(char[] responses) {
    // FC 67 09 00 15 00 00 00 00 01 15 08 9C EC
    if (responses == null || responses.length != 14) {
      return 0;
    }
    boolean isSuccess = CalcCRC.checkCrc16(responses);
    if (isSuccess) {
      return (int) responses[9];
    }
    return 0;
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
  public static EquipDataDO parseGetInfo(char[] response, int dataLen, int address) {
    EquipDataDO equipDataDO = new EquipDataDO();
    int humi = 0, temp = 0, dew = 0, power = 0;
    if (dataLen >= 1) {
      humi = (int) ((response[3] << 8) + response[4]); //  equip.getHumiDev() * 100
    }

    if (dataLen >= 2) {
      temp = (int) (((response[5] << 8) + response[6] - 27315)); // equip.getTempDev() * 100
    }

    if (dataLen >= 3) {
      dew = ((response[7] << 8) + response[8] - 27315);
    }

    if (dataLen >= 4) {
      power = (response[9] << 8) + response[10];
    }

    equipDataDO.setAddress(address);
    equipDataDO.setHuimi(humi);
    equipDataDO.setTemp(temp);
    equipDataDO.setDew(dew);
    equipDataDO.setPower(power);

    return equipDataDO;
  }

  // -------------------------------------------------------------------------------------

}