package com.chengqianyun.eeweb2network.controllers;


import java.util.Date;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 18/3/12
 */
@Controller
@RequestMapping("/web")
public class WebController {

  @RequestMapping("/hi1")
  public String hi1(Map<String, Object> model) {
    model.put("time", new Date());
    model.put("message", "这是测试的内容1。。。");
    model.put("toUserName", "张三1");
    model.put("fromUserName", "老许1");
    return "welcome1";
  }

  @RequestMapping("/hi2")
  public String hi2(Map<String, Object> model) {
    model.put("time", new Date());
    model.put("message", "这是测试的内容1。。。");
    model.put("toUserName", "张三1");
    model.put("fromUserName", "老许1");
    return "welcome2";
  }


}