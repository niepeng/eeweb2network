package com.chengqianyun.eeweb2network.test.dao;


import com.chengqianyun.eeweb2network.dao.EquipDataDO;
import com.chengqianyun.eeweb2network.dao.mapper.EquipDataDOMapper;
import com.chengqianyun.eeweb2network.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 聂鹏
 * @version 1.0
 * @email lsb@51huadian.cn
 * @date 18/2/28
 */

public class CRUDTest extends BaseTest {

  @Autowired
  private EquipDataDOMapper equipDataDOMapper;

  @Test
  public void test() {
    EquipDataDO dataDO = new EquipDataDO();
    dataDO.setAddress(1);
    dataDO.setEquipId(1);
    dataDO.setHuimi(5012);
    dataDO.setTemp(1234);
    dataDO.setDew(1);
    dataDO.setPower(2);
    int result = equipDataDOMapper.insert(dataDO);
    System.out.println("result==>" + result);
  }


}