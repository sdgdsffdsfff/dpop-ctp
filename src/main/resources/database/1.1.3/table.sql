 DROP TABLE IF EXISTS `tb_download_info`;
 CREATE TABLE `tb_download_info` (
   `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   `file_name` VARCHAR(64) NOT NULL COMMENT '文件名',
   `file_type` INT(11) NOT NULL COMMENT '文件类型',
   `line_num` INT(11) NOT NULL DEFAULT 0 COMMENT '文件行数',
   `file_size` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '文件大小',
   `start_time` DATETIME NOT NULL DEFAULT '1970-1-1 00:00:00' COMMENT '开始时间',
   `done_time` DATETIME NOT NULL DEFAULT '1970-1-1 00:00:00' COMMENT '结束时间',
   `start_user` VARCHAR(64) NOT NULL COMMENT '执行人',
   `percentage` INT(11) NOT NULL DEFAULT 0 COMMENT '完成度', 
   PRIMARY KEY `pk_tb_download_info` (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='下载信息表';
 
ALTER TABLE tb_task ADD COLUMN `task_type` TINYINT(4) NOT NULL DEFAULT 1 COMMENT '任务类型';
ALTER TABLE tb_review_task ADD COLUMN `task_type` TINYINT(4) NOT NULL DEFAULT 1 COMMENT '任务类型';

ALTER TABLE tb_beidou_task ADD COLUMN `task_type` TINYINT(4) NOT NULL DEFAULT 1 COMMENT '任务类型';
ALTER TABLE tb_qiushi_task ADD COLUMN `task_type` TINYINT(4) NOT NULL DEFAULT 1 COMMENT '任务类型';
ALTER TABLE tb_dsp_task ADD COLUMN `task_type` TINYINT(4) NOT NULL DEFAULT 1 COMMENT '任务类型';
ALTER TABLE tb_new_dsp_task ADD COLUMN `task_type` TINYINT(4) NOT NULL DEFAULT 1 COMMENT '任务类型';
ALTER TABLE tb_review_ad_task ADD COLUMN `task_type` TINYINT(4) NOT NULL DEFAULT 1 COMMENT '任务类型';
ALTER TABLE tb_new_trade_type
ADD COLUMN `level2_id` INT(11) NOT NULL DEFAULT 0 COMMENT '对应二级行业id',
ADD COLUMN `level2_name` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '对应二级行业名称';