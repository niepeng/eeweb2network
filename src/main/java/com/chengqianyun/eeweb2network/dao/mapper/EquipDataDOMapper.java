package com.chengqianyun.eeweb2network.dao.mapper;

import com.chengqianyun.eeweb2network.dao.EquipDataDO;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipDataDOMapper {

    int deleteByPrimaryKey(Long id);

    int insert(EquipDataDO record);

    EquipDataDO selectByPrimaryKey(Long id);

    List<EquipDataDO> selectAll();

    List<EquipDataDO> getByTimes(@Param("address") int address, @Param("startTime")Date startTime, @Param("endTime") Date endTime);

    int updateByPrimaryKey(EquipDataDO record);
}