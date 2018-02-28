package com.chengqianyun.eeweb2network.controllers;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 18/2/28
 */
@RestController
@RequestMapping("/common")
public class CommonController extends BaseController {

  @ApiOperation(value="启动或查看服务", notes="查看当前端口是否监听和启动")
  @RequestMapping(value="/start", method= RequestMethod.GET)
  public Boolean start() {
    return true;
  }
}