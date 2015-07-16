-- 标注明细表新增add_time索引
ALTER TABLE tb_beidou_task ADD INDEX `idx_add_time`(`add_time`);
ALTER TABLE tb_dsp_task ADD INDEX `idx_add_time`(`add_time`);
ALTER TABLE tb_qiushi_task ADD INDEX `idx_add_time`(`add_time`);

-- 新增维度统计信息历史表
DROP TABLE IF EXISTS `tb_statistics_dim_his`;
CREATE TABLE `tb_statistics_dim_his` (
   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   `task_id` int(11) NOT NULL COMMENT '任务ID',
   `high_beauty_num` int(11) NOT NULL DEFAULT '0' COMMENT '美观度-高',
   `normal_beauty_num` int(11) NOT NULL DEFAULT '0' COMMENT '美观度-中',
   `low_beauty_num` int(11) NOT NULL DEFAULT '0' COMMENT '美观度-低',
   `black_vulgar_num` int(11) NOT NULL DEFAULT '0' COMMENT '低俗度-黑',
   `gray_vulgar_num` int(11) NOT NULL DEFAULT '0' COMMENT '低俗度-灰',
   `white_vulgar_num` int(11) NOT NULL DEFAULT '0' COMMENT '低俗度-白',
   `is_cheat_num` int(11) NOT NULL DEFAULT '0' COMMENT '欺诈度-是',
   `not_cheat_num` int(11) NOT NULL DEFAULT '0' COMMENT '欺诈度-否',
   `is_high_danger_num` int(11) NOT NULL DEFAULT '0' COMMENT '高危度-是',
   `not_high_danger_num` int(11) NOT NULL DEFAULT '0' COMMENT '高危度-否',
   PRIMARY KEY pk_tb_statistics_dim_his(`id`),
   KEY `idx_task_id` (`task_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='维度统计信息历史表';

-- 新增人员标注统计历史表
DROP TABLE IF EXISTS `tb_statistics_user_his`;
CREATE TABLE `tb_statistics_user_his` (
   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   `task_id` int(11) NOT NULL COMMENT '任务ID',
   `user_name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '标注人员账户名',
   `is_done_groups` int(11) NOT NULL DEFAULT '0' COMMENT '已标注推广组数',
   `is_done_ads` int(11) NOT NULL DEFAULT '0' COMMENT '已标注创意数',
   PRIMARY KEY pk_tb_statistics_user_his(`id`),
   KEY `idx_task_id` (`task_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='人员标注统计历史表';

-- 新增趋势统计历史表
DROP TABLE IF EXISTS `tb_statistics_trend_his`;
CREATE TABLE `tb_statistics_trend_his` (
   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   `task_id` int(11) NOT NULL COMMENT '任务ID',
   `user_name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '标注人员账户名',
   `time_6` int(11) NOT NULL DEFAULT '0' COMMENT '6点前已标注的ads',
   `time_7` int(11) NOT NULL DEFAULT '0' COMMENT '6点-7点已标注的ads',
   `time_8` int(11) NOT NULL DEFAULT '0' COMMENT '7点-8点已标注的ads',
   `time_9` int(11) NOT NULL DEFAULT '0' COMMENT '8点-9点已标注的ads',
   `time_10` int(11) NOT NULL DEFAULT '0' COMMENT '9点-10点已标注的ads',
   `time_11` int(11) NOT NULL DEFAULT '0' COMMENT '10点-11点已标注的ads',
   `time_12` int(11) NOT NULL DEFAULT '0' COMMENT '11点-12点已标注的ads',
   `time_13` int(11) NOT NULL DEFAULT '0' COMMENT '12点-13点已标注的ads',
   `time_14` int(11) NOT NULL DEFAULT '0' COMMENT '13点-14点已标注的ads',
   `time_15` int(11) NOT NULL DEFAULT '0' COMMENT '14点-15点已标注的ads',
   `time_16` int(11) NOT NULL DEFAULT '0' COMMENT '15点-16点已标注的ads',
   `time_17` int(11) NOT NULL DEFAULT '0' COMMENT '16点-17点已标注的ads',
   `time_18` int(11) NOT NULL DEFAULT '0' COMMENT '17点-18点已标注的ads',
   `time_19` int(11) NOT NULL DEFAULT '0' COMMENT '18点-19点已标注的ads',
   `time_20` int(11) NOT NULL DEFAULT '0' COMMENT '19点-20点已标注的ads',
   PRIMARY KEY pk_tb_statistics_trend_his(`id`),
   KEY `idx_task_id` (`task_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='趋势统计历史表';


