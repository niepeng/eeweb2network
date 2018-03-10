package com.chengqianyun.eeweb2network.controllers;


import com.chengqianyun.eeweb2network.common.bean.req.CheckDataReq;
import com.chengqianyun.eeweb2network.common.bean.resp.CheckDataResp;
import com.chengqianyun.eeweb2network.dao.EquipDataDO;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 18/2/28
 */
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController extends BaseController {

  @ApiOperation(value="启动或查看服务", notes="查看当前端口是否监听和启动")
  @RequestMapping(value="/start", method= RequestMethod.GET)
  public Boolean start() {
//    EquipDataDOMapper equipDataDOMapper = SpringHelper.getBean("equipDataDOMapper", EquipDataDOMapper.class);
//    log.info(equipDataDOMapper.toString());
    return true;
  }

  @ApiOperation("检查设备丢失数据时间点")
  @RequestMapping(value = "/checkDeviceData", method = RequestMethod.POST)
  public List<EquipDataDO> checkDeviceData(@RequestBody @Valid CheckDataReq checkDataReq) {
    return commonService.checkDeviceData(checkDataReq);
  }



}