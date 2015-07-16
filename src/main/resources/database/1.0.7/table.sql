-- add_time 改 datetime类型
ALTER TABLE tb_task MODIFY COLUMN add_time DATETIME NOT NULL COMMENT '创建时间';

-- 数据导入信息表
DROP TABLE IF EXISTS `tb_data_load_info`;
CREATE TABLE `tb_data_load_info` (
   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   `file_name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '文件名',
   `file_size` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '文件大小',
   `data_type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '产品线: 0-北斗, 1-秋实, 2DSP',
   `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '状态: 0-处理中, 1-已完成, 2-失败',
   `scan_record` INT(11) NOT NULL DEFAULT '0' COMMENT '物料类型',
   `insert_record` INT(11) NOT NULL DEFAULT '0' COMMENT '物料类型',
   `md5_value` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '文件md5值',
   `add_time` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '新增时间',
   PRIMARY KEY pk_tb_data_load_info(`id`),
   KEY `idx_md5_value` (`md5_value`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='数据导入信息表';