package com.chengqianyun.eeweb2network.services;

import com.chengqianyun.eeweb2network.dao.mapper.EquipDataDOMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 18/2/28
 */

public class BaseService {

  @Autowired
  protected EquipDataDOMapper equipDataDOMapper;

}