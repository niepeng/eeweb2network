package com.chengqianyun.eeweb2network.common.bean.req;


import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 18/3/10
 */

@Data
public class CheckDataReq {


  private int addrsss;

  @NotBlank(message = "开始时间不能为空")
  private String startTime;

  @NotBlank(message = "结束时间不能为空")
  private String endTime;
}