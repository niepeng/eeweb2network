package com.chengqianyun.eeweb2network.dao;

import java.util.Date;
import lombok.Data;

@Data
public class EquipDataDO {

    private long id;

    /**
     * 设备地址
     */
    private int address;

    /**
     * 外部设备id
     */
    private long equipId;

    /**
     * 湿度
     */
    private int huimi;

    /**
     * 温度
     */
    private int temp;

    /**
     * 露点
     */
    private int dew;

    /**
     * 电压
     */
    private int power;

    /**
     */
    private Date createdAt;

    /**
     */
    private Date updatedAt;

    /**
     */
    private String createdBy;

    /**
     */
    private String updatedBy;

}