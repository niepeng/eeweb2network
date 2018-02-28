

CREATE TABLE `t_equip_data` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
`address` int(11) NOT NULL default 0 COMMENT '设备地址编号',
`equip_id` bigint(20) NOT NULL default 0 COMMENT '外部设备id',
`huimi` int(11) NOT NULL default 0 COMMENT '湿度',
`temp` int(11) NOT NULL default 0 COMMENT '温度',
`dew` int(11) NOT NULL default 0 COMMENT '露点',
`power` int(11) NOT NULL default 0 COMMENT '电压',
`created_at` datetime DEFAULT NULL comment '创建时间',
`updated_at` datetime DEFAULT NULL comment '修改时间',
`created_by` varchar(45) DEFAULT NULL comment '创建人',
`updated_by` varchar(45) DEFAULT NULL comment '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 comment='设备获取原始数据记录';


-- `subject_name` varchar(32) DEFAULT NULL COMMENT '标的名称',
-- `raise_status` SMALLINT NOT NULL default 0 COMMENT '募集状态:待募集,募集中,募集结束',

