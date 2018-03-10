package com.chengqianyun.eeweb2network.services;

import com.chengqianyun.eeweb2network.common.bean.req.CheckDataReq;
import com.chengqianyun.eeweb2network.common.bean.resp.CheckDataResp;
import com.chengqianyun.eeweb2network.dao.EquipDataDO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author 聂鹏
 * @version 1.0
 * @email lsb@51huadian.cn
 * @date 18/2/28
 */
@Service
public class CommonService extends BaseService {


  public List<CheckDataResp> checkDeviceData(CheckDataReq checkDataReq) {

    int pageNo = 1;
    int pageSize =15;

    Page<EquipDataDO> page = PageHelper.startPage(pageNo, pageSize, false);

    List<EquipDataDO> list = equipDataDOMapper.selectAll();
    System.out.println("list====> "+list.size());
    return new ArrayList<>();
  }

}