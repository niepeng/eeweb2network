package com.chengqianyun.eeweb2network.dao.mapper;

import com.chengqianyun.eeweb2network.dao.EquipDataDO;
import java.util.List;

public interface EquipDataDOMapper {

    int deleteByPrimaryKey(Long id);

    int insert(EquipDataDO record);

    EquipDataDO selectByPrimaryKey(Long id);

    List<EquipDataDO> selectAll();

    int updateByPrimaryKey(EquipDataDO record);
}