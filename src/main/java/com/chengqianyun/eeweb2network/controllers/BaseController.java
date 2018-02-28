package com.chengqianyun.eeweb2network.controllers;


import com.chengqianyun.eeweb2network.services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 18/2/28
 */
@Component
public class BaseController {

  @Autowired
  protected CommonService commonService;
}