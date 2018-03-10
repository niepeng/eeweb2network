package com.chengqianyun.eeweb2network.services;

import com.chengqianyun.eeweb2network.common.bean.req.CheckDataReq;
import com.chengqianyun.eeweb2network.common.bean.resp.CheckDataResp;
import com.chengqianyun.eeweb2network.common.util.DateUtil;
import com.chengqianyun.eeweb2network.core.ServerNormal;
import com.chengqianyun.eeweb2network.dao.EquipDataDO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 聂鹏
 * @version 1.0
 * @email lsb@51huadian.cn
 * @date 18/2/28
 */
@Service
@Slf4j
public class CommonService extends BaseService {


  public List<EquipDataDO> checkDeviceData(CheckDataReq req) {

//    int pageNo = 1;
//    int pageSize =15;
//    Page<EquipDataDO> page = PageHelper.startPage(pageNo, pageSize, false);

    Date startTime = DateUtil.parseNoException(req.getStartTime(), DateUtil.DEFAULT_DATE_FMT);
    Date endTime = DateUtil.parseNoException(req.getEndTime(), DateUtil.DEFAULT_DATE_FMT);

    List<EquipDataDO> list = equipDataDOMapper.getByTimes(req.getAddrsss(), startTime, endTime);
    int size = list.size();
    List<EquipDataDO> result = new ArrayList<>();

    if (size < 2) {
      return result;
    }

    log.info("list==>{}", list.size());

    EquipDataDO tmp = null;
    for (int j = 1; j < size; j++) {
      if (!isDataContinuous(list.get(j-1).getCreatedAt(), list.get(j).getCreatedAt())) {
        tmp = list.get(j-1);
        tmp.setCreatedAtStr(DateUtil.format(tmp.getCreatedAt()));
        result.add(tmp);

        tmp = list.get(j);
        tmp.setCreatedAtStr(DateUtil.format(tmp.getCreatedAt()));
        result.add(tmp);
      }
    }

    return result;
  }

  /**
   * 数据是否连续: 本次获取数据时间t1, 下次获取数据时间t2
   */
  public boolean isDataContinuous(Date t1, Date t2) {
    long distance = Math.abs(t2.getTime() - t1.getTime())/1000;
    return distance < (long)(ServerNormal.GET_DATA_CYCLE * (1.5d));
  }

}