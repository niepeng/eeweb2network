package com.chengqianyun.eeweb2network.common.enums;

import lombok.Getter;

@Getter
public enum DataStatusEnum {

  right_frame(1, "正确的侦"),
  lose_frame(2, "丢失的侦"),
  wrong_frame(3, "错误的侦"),
  wrong_address(4,"地址不对的侦"),
  wrong_data_length(5,"长度不对的侦"),
  ;

  private final int id;

  private final String meaning;

  private DataStatusEnum(int id, String meaning) {
    this.id = id;
    this.meaning = meaning;
  }

  public static boolean isSuccess(DataStatusEnum tmp) {
    if (tmp == null) {
      return false;
    }
    return tmp == right_frame;
  }

}
