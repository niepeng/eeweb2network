package com.chengqianyun.eeweb2network.services;

import com.chengqianyun.eeweb2network.dao.mapper.EquipDataDOMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 18/2/28
 */
@Slf4j
public class BaseService {

  @Autowired
  protected EquipDataDOMapper equipDataDOMapper;

}