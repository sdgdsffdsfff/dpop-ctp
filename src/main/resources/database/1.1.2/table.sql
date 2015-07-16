 DROP TABLE IF EXISTS `tb_new_dsp_task`;
 CREATE TABLE `tb_new_dsp_task` (
   `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   `ad_id` bigint(20) NOT NULL COMMENT '创意ID',
   `user_id` bigint(20) NOT NULL COMMENT '账户ID',
   `adver_id` bigint(20) NOT NULL COMMENT '广告ID',
   `wuliao_type` INT(11) NOT NULL COMMENT '物料类型',
   `show_url` varchar(255) NOT NULL DEFAULT '' COMMENT '展示URL',
   `target_url` varchar(2048) NOT NULL DEFAULT '' COMMENT '落地页URL',
   `width` INT(11) NOT NULL COMMENT '创意宽度',
   `height` INT(11) NOT NULL COMMENT '创意高度',
   `title` varchar(255) NOT NULL DEFAULT '' COMMENT '创意标题',
   `description` varchar(255) NOT NULL DEFAULT '' COMMENT '创意描述一',
   `company` varchar(255) NOT NULL DEFAULT '' COMMENT '公司名称',
   `website` varchar(255) NOT NULL DEFAULT '' COMMENT '公司网址',
   `ad_trade_id_level2` INT(11) NOT NULL COMMENT '创意二级行业，此处即三级行业的前两级',
   `ad_trade_id_level3` INT(11) NOT NULL COMMENT '创意三级行业',
   `ad_tag` VARCHAR(64) NOT NULL COMMENT '标注信息',
   `url` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '图片显示url，只保存第一张，作缩略图显示用',
   `tag_version` INT(11) NOT NULL DEFAULT 1 COMMENT '版本号，回传即可', 
   `task_id` INT(11) NOT NULL COMMENT '任务id',
   `task_name` VARCHAR(64) NOT NULL COMMENT '任务名称',
   `create_time` DATETIME NOT NULL DEFAULT '1970-1-1 00:00:00' COMMENT '创建时间',
   `chatime` datetime NOT NULL COMMENT '创意修改时间（回传北斗）',
   `add_time` datetime NOT NULL COMMENT '新增时间',
   `add_user` varchar(64) NOT NULL COMMENT '新增人',
   PRIMARY KEY `pk_tb_new_dsp_task` (`id`),
   KEY `idx_task_id_user_id` (`task_id`, `user_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='百度DSP待标注物料信息表';
 
 ALTER TABLE tb_ad_tag ADD COLUMN `assigned` TINYINT(4) NOT NULL DEFAULT 0 COMMENT '是否入库成功';
 ALTER TABLE tb_review_ad_task ADD COLUMN `assigned` TINYINT(4) NOT NULL DEFAULT 0 COMMENT '是否入库成功';