 -- 待标注物料信息表
 DROP TABLE IF EXISTS `tb_qiushi_task`;
 CREATE TABLE `tb_qiushi_task` (
   `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   `ad_id` bigint(20) NOT NULL COMMENT '创意ID',
   `group_id` bigint(20) NOT NULL COMMENT '推广组ID',
   `plan_id` bigint(20) NOT NULL COMMENT '推广计划ID',
   `user_id` bigint(20) NOT NULL COMMENT '账户ID',
   `wuliao_type` INT(11) NOT NULL COMMENT '物料类型',
   `company` varchar(255) NOT NULL DEFAULT '' COMMENT '账户公司名称',
   `website` varchar(255) NOT NULL DEFAULT '' COMMENT '账户公司网站',
   `show_url` varchar(255) NOT NULL DEFAULT '' COMMENT '展现URL',
   `target_url` varchar(2048) NOT NULL DEFAULT '' COMMENT '落地页URL',
   `width` INT(11) NOT NULL COMMENT '创意宽度',
   `height` INT(11) NOT NULL COMMENT '创意高度',
   `title` varchar(255) NOT NULL DEFAULT '' COMMENT '创意标题',
   `description1` varchar(255) NOT NULL DEFAULT '' COMMENT '创意描述一',
   `description2` varchar(255) NOT NULL DEFAULT '' COMMENT '创意描述二',
   `ad_trade_id_level2` INT(11) NOT NULL COMMENT '创意二级行业',
   `ad_trade_id_level3` INT(11) NOT NULL COMMENT '创意三级行业',
   `ad_tag` INT(11) NOT NULL COMMENT '标注信息',
   `mc_id` INT(11) NOT NULL COMMENT '物料在UBMC中对应ID',
   `mc_version_id` INT(11) NOT NULL COMMENT '物料在UBMC中的版本ID',
   `priority` INT(11) NOT NULL COMMENT '创意在平台上展现标注的优先级',
   `moduser_level` INT(11) NOT NULL COMMENT '创意的可标注人（内部or外部）',
   `task_name` varchar(50) NOT NULL DEFAULT '' COMMENT '标注任务名称（区分不同数据对应的标注任务）',
   `task_id` INT(11) NOT NULL COMMENT '标注任务id',
   `chatime` datetime NOT NULL COMMENT '创意修改时间（回传北斗）',
   `comment` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
   `add_time` datetime NOT NULL COMMENT '新增时间',
   `add_user` varchar(64) NOT NULL COMMENT '新增人',
   `update_time` datetime NOT NULL COMMENT '修改时间',
   `update_user` varchar(64) NOT NULL COMMENT '修改人',
   PRIMARY KEY `pk_tb_beidou_task` (`id`),
   KEY `idx_ad_id` (`ad_id`),
   KEY `idx_user_id` (`user_id`),
   KEY `idx_task_id` (`task_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秋实待标注物料信息表';
 
 DROP TABLE IF EXISTS `tb_dsp_task`;
 CREATE TABLE `tb_dsp_task` (
   `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   `ad_id` bigint(20) NOT NULL COMMENT '创意ID',
   `user_id` bigint(20) NOT NULL COMMENT '账户ID，此处作为group_id使用',
   `dsp_id` bigint(20) NOT NULL COMMENT 'DSPid',
   `wuliao_type` INT(11) NOT NULL COMMENT '物料类型',
   `nickname` varchar(255) NOT NULL DEFAULT '' COMMENT '账户公司名称',
   `website` varchar(255) NOT NULL DEFAULT '' COMMENT '账户公司网站',
   `landing_page` varchar(2048) NOT NULL DEFAULT '' COMMENT '落地页URL',
   `dspname` varchar(255) NOT NULL DEFAULT '' COMMENT 'DSP名称',
   `width` INT(11) NOT NULL COMMENT '创意宽度',
   `height` INT(11) NOT NULL COMMENT '创意高度',
   `ad_trade_id_level2` INT(11) NOT NULL COMMENT '创意二级行业，此处即三级行业的前两级',
   `ad_trade_id_level3` INT(11) NOT NULL COMMENT '创意三级行业',
   `ad_tag` INT(11) NOT NULL COMMENT '标注信息',
   `mc_id` INT(11) NOT NULL COMMENT '物料在UBMC中对应ID',
   `mc_version_id` INT(11) NOT NULL COMMENT '物料在UBMC中的版本ID',
   `priority` INT(11) NOT NULL COMMENT '创意在平台上展现标注的优先级',
   `moduser_level` INT(11) NOT NULL COMMENT '创意的可标注人（内部or外部）',
   `task_name` varchar(50) NOT NULL DEFAULT '' COMMENT '标注任务名称（区分不同数据对应的标注任务）',
   `task_id` INT(11) NOT NULL COMMENT '标注任务id',
   `chatime` datetime NOT NULL COMMENT '创意修改时间（回传北斗）',
   `comment` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
   `add_time` datetime NOT NULL COMMENT '新增时间',
   `add_user` varchar(64) NOT NULL COMMENT '新增人',
   `update_time` datetime NOT NULL COMMENT '修改时间',
   `update_user` varchar(64) NOT NULL COMMENT '修改人',
   PRIMARY KEY `pk_tb_beidou_task` (`id`),
   KEY `idx_ad_id` (`ad_id`),
   KEY `idx_user_id` (`user_id`),
   KEY `idx_task_id` (`task_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='DSP待标注物料信息表';
 
 DROP TABLE IF EXISTS `tb_beidou_task`;
 CREATE TABLE `tb_beidou_task` (
   `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   `ad_id` bigint(20) NOT NULL COMMENT '创意ID',
   `group_id` bigint(20) NOT NULL COMMENT '推广组ID',
   `plan_id` bigint(20) NOT NULL COMMENT '推广计划ID',
   `user_id` bigint(20) NOT NULL COMMENT '账户ID',
   `wuliao_type` INT(11) NOT NULL COMMENT '物料类型',
   `company` varchar(255) NOT NULL DEFAULT '' COMMENT '账户公司名称',
   `website` varchar(255) NOT NULL DEFAULT '' COMMENT '账户公司网站',
   `show_url` varchar(255) NOT NULL DEFAULT '' COMMENT '展现URL',
   `target_url` varchar(2048) NOT NULL DEFAULT '' COMMENT '落地页URL',
   `width` INT(11) NOT NULL COMMENT '创意宽度',
   `height` INT(11) NOT NULL COMMENT '创意高度',
   `title` varchar(255) NOT NULL DEFAULT '' COMMENT '创意标题',
   `description1` varchar(255) NOT NULL DEFAULT '' COMMENT '创意描述一',
   `description2` varchar(255) NOT NULL DEFAULT '' COMMENT '创意描述二',
   `ad_trade_id_level2` INT(11) NOT NULL COMMENT '创意二级行业',
   `ad_trade_id_level3` INT(11) NOT NULL COMMENT '创意三级行业',
   `ad_tag` INT(11) NOT NULL COMMENT '标注信息',
   `mc_id` INT(11) NOT NULL COMMENT '物料在UBMC中对应ID',
   `mc_version_id` INT(11) NOT NULL COMMENT '物料在UBMC中的版本ID',
   `priority` INT(11) NOT NULL COMMENT '创意在平台上展现标注的优先级',
   `moduser_level` INT(11) NOT NULL COMMENT '创意的可标注人（内部or外部）',
   `task_name` varchar(50) NOT NULL DEFAULT '' COMMENT '标注任务名称（区分不同数据对应的标注任务）',
   `task_id` INT(11) NOT NULL COMMENT '标注任务id',
   `ad_type` INT(11) NOT NULL COMMENT '智能创意or普通创意',
   `chatime` datetime NOT NULL COMMENT '创意修改时间（回传北斗）',
   `comment` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
   `add_time` datetime NOT NULL COMMENT '新增时间',
   `add_user` varchar(64) NOT NULL COMMENT '新增人',
   `update_time` datetime NOT NULL COMMENT '修改时间',
   `update_user` varchar(64) NOT NULL COMMENT '修改人',
   PRIMARY KEY `pk_tb_beidou_task` (`id`),
   KEY `idx_ad_id` (`ad_id`),
   KEY `idx_user_id` (`user_id`),
   KEY `idx_task_id` (`task_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='北斗待标注物料信息表';
 
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
   `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
   `user_name` varchar(255) NOT NULL UNIQUE COMMENT '用户名称',
   `role_type` TINYINT(4) NOT NULL COMMENT '用户类别',
   `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
   `add_time` datetime NOT NULL COMMENT '添加时间',
   `update_time` datetime NOT NULL COMMENT '更新时间',
   `update_user` INT(11) NOT NULL DEFAULT '-1' COMMENT '更改人',
   `delete_flag` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-删除',
   PRIMARY KEY pk_tb_user (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员信息表';
 
 DROP TABLE IF EXISTS `tb_task`;
 CREATE TABLE `tb_task` (
 	`id` INT(11) NOT NULL COMMENT '主键，由上游给出',
 	`task_name` varchar(255) NOT NULL COMMENT '任务名称',
 	`add_user` varchar(255) NOT NULL DEFAULT 'System' COMMENT '创建人',
 	`add_time` date NOT NULL COMMENT '创建时间',
 	`status` TINYINT(4) NOT NULL DEFAULT 0 COMMENT '任务状态，0-未开始，1-进行中，2-已完成，3-已关闭',
 	`moduser_level` INT(11) NOT NULL DEFAULT 1 COMMENT '内部/外部任务，0-内部， 1-外部',
 	PRIMARY KEY pk_tb_task (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务表';
 
 DROP TABLE IF EXISTS `tb_group`;
 CREATE TABLE `tb_group` (
 	`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
 	`group_id` bigint(20) NOT NULL COMMENT '推广组ID，不同task下的推广组id可能不同',
 	`data_type` TINYINT(4) NOT NULL DEFAULT 0 COMMENT 'Group类型，0 - 北斗， 1 - 秋实',
 	`task_name` varchar(255) NOT NULL COMMENT '所属任务名称',
 	`task_id` INT(11) NOT NULL COMMENT '所属任务id',
 	`ad_num` INT(11) NOT NULL COMMENT '组内创意数量',
 	`status` TINYINT(4) NOT NULL DEFAULT 0 COMMENT '状态, 0-未开始, 1-处理中, 2-已完成',
 	`priority` INT(11) NOT NULL DEFAULT 0 COMMENT '优先级',
 	`modify_user_id` INT(11) COMMENT '正在处理的用户id',
 	`start_time` datetime COMMENT '开始标注时间',
 	`done_time` datetime COMMENT '提交时间',
 	PRIMARY KEY pk_tb_group(`id`),
 	UNIQUE INDEX(`group_id`, `data_type`, `task_id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='推广组表';
  
 DROP TABLE IF EXISTS `tb_operation_record`;
 CREATE TABLE `tb_operation_record` (
 	`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
 	`operator` bigint(20) NOT NULL COMMENT '操作者id',
 	`update_time` datetime NOT NULL COMMENT '操作时间',
 	`operation_type` INT(11) COMMENT '操作类型',
 	`operation` varchar(255) NOT NULL COMMENT '操作内容',
 	PRIMARY KEY pk_tb_operation_record(`id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作记录表';
  
 DROP TABLE IF EXISTS `tb_new_trade_type`;
 CREATE TABLE `tb_new_trade_type` (
 	`first_id` INT(11) NOT NULL COMMENT '第一级行业id',
 	`first_name` varchar(255) NOT NULL COMMENT '第一级行业名称',
 	`second_id` INT(11) NOT NULL COMMENT '第二级行业id',
 	`second_name` varchar(255) NOT NULL COMMENT '第二级行业名称',
 	`third_id` INT(11) NOT NULL COMMENT '第三级行业id',
 	`third_name` varchar(255) NOT NULL COMMENT '第三级行业名称',
 	`full_id` INT(11) NOT NULL COMMENT '完整id',
 	`full_name` varchar(255) NOT NULL COMMENT '完整名称',
 	PRIMARY KEY pk_tb_new_industry_type(`full_id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='三级行业表';
  
 DROP TABLE IF EXISTS `tb_trade_type`;
 CREATE TABLE `tb_trade_type` (
 	`first_id` INT(11) NOT NULL COMMENT '第一级行业id',
 	`first_name` varchar(255) NOT NULL COMMENT '第一级行业名称',
 	`second_id` INT(11) NOT NULL COMMENT '第二级行业id',
 	`second_name` varchar(255) NOT NULL COMMENT '第二级行业名称',
 	`full_id` INT(11) NOT NULL COMMENT '完整id',
 	`full_name` varchar(255) NOT NULL COMMENT '完整名称',
 	PRIMARY KEY pk_tb_industry_type(`full_id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='二级行业表';
 