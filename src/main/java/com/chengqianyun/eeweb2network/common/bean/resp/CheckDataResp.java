package com.chengqianyun.eeweb2network.common.bean.resp;


import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 18/3/10
 */
@Data
@AllArgsConstructor
public class CheckDataResp {

  private long id;

  private int address;

  private Date time;

}