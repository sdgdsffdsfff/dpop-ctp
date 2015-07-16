 DROP TABLE IF EXISTS `tb_review_ad_task`;
 CREATE TABLE `tb_review_ad_task` (
   `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   `data_type` TINYINT(4) NOT NULL COMMENT '数据类型',
   `ref_ad_id` bigint(20) NOT NULL COMMENT '关联数据ID',
   `group_id` bigint(20) NOT NULL COMMENT '推广组ID',
   `group_id_review` bigint(20) NOT NULL COMMENT '审核groupID',
   `task_id` bigint(20) NOT NULL COMMENT '审核任务ID',
   `wuliao_type` INT(11) NOT NULL DEFAULT 0 COMMENT '物料类型',
   `ad_trade_id_level3` INT(11) NOT NULL COMMENT '已标注创意三级行业',
   `ad_trade_id_level3_review` INT(11) NOT NULL DEFAULT 0 COMMENT '审核注创意三级行业',
   `ad_tag` INT(11) NOT NULL DEFAULT 0 COMMENT '已标注标注信息',
   `ad_tag_review` INT(11) NOT NULL DEFAULT 0 COMMENT '审核标注信息',
   `comment` varchar(255) NOT NULL DEFAULT '' COMMENT '已标注备注',
   `comment_review` varchar(255) NOT NULL DEFAULT '' COMMENT '审核备注',
   `add_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '新增时间',
   `add_user` varchar(64) NOT NULL DEFAULT 'System' COMMENT '新增人',
   `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
   `update_user` varchar(64) NOT NULL DEFAULT 'System' COMMENT '修改人',
   PRIMARY KEY pk_tb_review_ad_task(`id`),
   KEY `idx_ref_ad_id` (`ref_ad_id`),
   KEY `idx_group_id` (`group_id`),
   KEY `idx_group_id_review` (`group_id_review`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='审核物料信息表';
 
 DROP TABLE IF EXISTS `tb_review_group`;
 CREATE TABLE `tb_review_group` (
 	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
 	`tag_group_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '原主键ID',
 	`group_id` BIGINT(20) NOT NULL COMMENT '推广组ID',
 	`data_type` TINYINT(4) NOT NULL DEFAULT 0 COMMENT 'Group类型，0 - 北斗， 1 - 秋实，2-DSP',
 	`task_id` INT(11) NOT NULL COMMENT '所属标注任务id',
 	`task_name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '原任务名称',
 	`task_id_review` INT(11) NOT NULL COMMENT '所属的审核任务id',
 	`task_name_review` VARCHAR(255) NOT NULL COMMENT '审核人物名称',
 	`ad_num` INT(11) NOT NULL COMMENT '组内创意数量',
 	`ad_num_review` INT(11) NOT NULL COMMENT '生成的审核任务所包含的创意数量',
 	`status` TINYINT(4) NOT NULL DEFAULT 0 COMMENT '状态, 0-未开始, 1-处理中, 2-已完成',
 	`modify_user_id` INT(11) NOT NULL DEFAULT -1 COMMENT '标注用户id',
 	`modify_user_id_review` INT(11) NOT NULL DEFAULT -1 COMMENT '正在处理的用户id',
 	`start_time` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '开始标注时间',
 	`done_time` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '提交时间',
 	PRIMARY KEY pk_tb_review_group(`id`),
   	KEY `idx_tag_group_id` (`tag_group_id`),
   	KEY `idx_task_id_review` (`task_id_review`)
  ) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='审核任务推广组表';
  
 DROP TABLE IF EXISTS `tb_review_task`;
 CREATE TABLE `tb_review_task` (
 	`id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
 	`task_name` varchar(255) NOT NULL COMMENT '任务名称',
 	`add_user` varchar(255) NOT NULL DEFAULT 'System' COMMENT '创建人',
 	`add_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
 	`begin_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '选择开始时间',
 	`end_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '选择结束时间',
 	`task_list` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '包含任务条目，使用“,”分隔',
 	`status` TINYINT(4) NOT NULL DEFAULT 0 COMMENT '任务状态，0-未开始，1-进行中，2-已完成',
 	`moduser_level` INT(11) NOT NULL DEFAULT 1 COMMENT '内部/外部任务，0-内部， 1-外部，2-全体',
 	`blind` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '是否盲审，0-盲审，1-非盲审',
 	`group_num` INT(11) NOT NULL DEFAULT 0 COMMENT '设定审核group数量',
 	`group_num_actual` INT(11) NOT NULL DEFAULT 0 COMMENT '实际完成数量',
 	`task_condition` INT(11) NOT NULL COMMENT '任务属性id',
 	PRIMARY KEY pk_tb_review_task (`id`),
   	KEY `idx_task_condition` (`task_condition`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='审核任务表';
 
 DROP TABLE IF EXISTS `tb_review_task_condition`;
 CREATE TABLE `tb_review_task_condition` (
 	`id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
 	`data_type` INT(11) NOT NULL DEFAULT 0 COMMENT '任务数据类型',
 	`ad_tag_condition` INT(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '标签类型',
 	`wuliao_type` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '物料类型，使用逗号',
 	`ad_trade_type` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '三级行业类型，可多选，用逗号分隔',
 	PRIMARY KEY pk_tb_review_task_condition (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='审核任务属性表';