DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `demo_name` varchar(256) DEFAULT NULL,
    `add_time` datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `user_name` varchar(255) NOT NULL UNIQUE,
    `role_type` TINYINT(4) NOT NULL,
    `password` varchar(255) DEFAULT NULL,
    `add_time` datetime NOT NULL,
    `update_time` datetime NOT NULL,
    `update_user` int(11) NOT NULL DEFAULT '-1',
    `delete_flag` TINYINT(1) NOT NULL DEFAULT '0',
    `login_try_times` int(11) NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`)
 );
 
DROP TABLE IF EXISTS `tb_task`;
CREATE TABLE `tb_task` (
    `id` INT(11) NOT NULL,
    `task_name` varchar(255) NOT NULL,
    `add_user` varchar(255) NOT NULL DEFAULT 'System',
    `add_time` datetime NOT NULL,
    `status` TINYINT(4) NOT NULL DEFAULT 0,
    `moduser_level` INT(11) NOT NULL DEFAULT 1,
    `task_type` TINYINT(4) NOT NULL DEFAULT 1,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
DROP TABLE IF EXISTS `tb_group`;
CREATE TABLE `tb_group` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `group_id` bigint(20) NOT NULL,
    `data_type` TINYINT(4) NOT NULL DEFAULT 0,
    `task_name` varchar(255) NOT NULL,
    `task_id` INT(11) NOT NULL,
    `ad_num` INT(11) NOT NULL,
    `status` TINYINT(4) NOT NULL DEFAULT 0,
    `priority` INT(11) NOT NULL DEFAULT 0,
    `modify_user_id` INT(11),
    `start_time` datetime,
    `done_time` datetime,
    PRIMARY KEY (`id`),
    UNIQUE INDEX(`group_id`, `data_type`, `task_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_beidou_task`;
CREATE TABLE `tb_beidou_task` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `ad_id` bigint(20) NOT NULL,
    `group_id` bigint(20) NOT NULL,
    `plan_id` bigint(20) NOT NULL,
    `user_id` bigint(20) NOT NULL,
    `wuliao_type` INT(11) NOT NULL,
    `company` varchar(255) NOT NULL DEFAULT '',
    `website` varchar(255) NOT NULL DEFAULT '',
    `show_url` varchar(255) NOT NULL DEFAULT '',
    `target_url` varchar(2048) NOT NULL DEFAULT '',
    `width` INT(11) NOT NULL,
    `height` INT(11) NOT NULL,
    `title` varchar(255) NOT NULL DEFAULT '',
    `description1` varchar(255) NOT NULL DEFAULT '',
    `description2` varchar(255) NOT NULL DEFAULT '',
    `ad_trade_id_level2` INT(11) NOT NULL,
    `ad_trade_id_level3` INT(11) NOT NULL,
    `ad_tag` VARCHAR(64) NOT NULL,
    `mc_id` INT(11) NOT NULL,
    `mc_version_id` INT(11) NOT NULL,
    `priority` INT(11) NOT NULL,
    `second_priority` INT(11) NOT NULL,
    `moduser_level` INT(11) NOT NULL,
    `task_name` varchar(50) NOT NULL DEFAULT '',
    `task_id` INT(11) NOT NULL,
    `ad_type` INT(11) NOT NULL,
    `chatime` datetime NOT NULL,
    `add_time` datetime NOT NULL,
    `add_user` varchar(64) NOT NULL,
    `task_type` TINYINT(4) NOT NULL DEFAULT 1,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
DROP TABLE IF EXISTS `tb_dsp_task`;
CREATE TABLE `tb_dsp_task` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `ad_id` bigint(20) NOT NULL,
    `user_id` bigint(20) NOT NULL,
    `dsp_id` bigint(20) NOT NULL,
    `wuliao_type` INT(11) NOT NULL,
    `nickname` varchar(255) NOT NULL DEFAULT '',
    `website` varchar(255) NOT NULL DEFAULT '',
    `landing_page` varchar(2048) NOT NULL DEFAULT '',
    `dspname` varchar(255) NOT NULL DEFAULT '',
    `width` INT(11) NOT NULL,
    `height` INT(11) NOT NULL,
    `ad_trade_id_level2` INT(11) NOT NULL,
    `ad_trade_id_level3` INT(11) NOT NULL,
    `ad_tag` VARCHAR(64) NOT NULL,
    `mc_id` INT(11) NOT NULL,
    `mc_version_id` INT(11) NOT NULL,
    `priority` INT(11) NOT NULL,
    `second_priority` INT(11) NOT NULL,
    `moduser_level` INT(11) NOT NULL,
    `task_name` varchar(50) NOT NULL DEFAULT '',
    `task_id` INT(11) NOT NULL,
    `chatime` datetime NOT NULL,
    `add_time` datetime NOT NULL,
    `add_user` varchar(64) NOT NULL,
    `task_type` TINYINT(4) NOT NULL DEFAULT 1,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
DROP TABLE IF EXISTS `tb_qiushi_task`;
CREATE TABLE `tb_qiushi_task` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `ad_id` bigint(20) NOT NULL,
    `group_id` bigint(20) NOT NULL,
    `plan_id` bigint(20) NOT NULL,
    `user_id` bigint(20) NOT NULL,
    `wuliao_type` INT(11) NOT NULL,
    `company` varchar(255) NOT NULL DEFAULT '',
    `website` varchar(255) NOT NULL DEFAULT '',
    `show_url` varchar(255) NOT NULL DEFAULT '',
    `target_url` varchar(2048) NOT NULL DEFAULT '',
    `width` INT(11) NOT NULL,
    `height` INT(11) NOT NULL,
    `title` varchar(255) NOT NULL DEFAULT '',
    `description1` varchar(255) NOT NULL DEFAULT '',
    `description2` varchar(255) NOT NULL DEFAULT '',
    `ad_trade_id_level2` INT(11) NOT NULL,
    `ad_trade_id_level3` INT(11) NOT NULL,
  	`ad_tag` VARCHAR(64) NOT NULL,
    `mc_id` INT(11) NOT NULL,
    `mc_version_id` INT(11) NOT NULL,
    `priority` INT(11) NOT NULL,
    `second_priority` INT(11) NOT NULL,
    `moduser_level` INT(11) NOT NULL,
    `task_name` varchar(50) NOT NULL DEFAULT '',
    `task_id` INT(11) NOT NULL,
    `chatime` datetime NOT NULL,
    `add_time` datetime NOT NULL,
    `add_user` varchar(64) NOT NULL,
    `task_type` TINYINT(4) NOT NULL DEFAULT 1,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_new_dsp_task`;
CREATE TABLE `tb_new_dsp_task` (
   `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
   `ad_id` bigint(20) NOT NULL,
   `user_id` bigint(20) NOT NULL,
   `adver_id` bigint(20) NOT NULL,
   `wuliao_type` INT(11) NOT NULL,
   `show_url` varchar(255) NOT NULL DEFAULT '',
   `target_url` varchar(2048) NOT NULL DEFAULT '',
   `width` INT(11) NOT NULL,
   `height` INT(11) NOT NULL,
   `title` varchar(255) NOT NULL DEFAULT '',
   `description` varchar(255) NOT NULL DEFAULT '',
   `company` varchar(255) NOT NULL DEFAULT '',
   `website` varchar(255) NOT NULL DEFAULT '',
   `ad_trade_id_level2` INT(11) NOT NULL,
   `ad_trade_id_level3` INT(11) NOT NULL,
   `ad_tag` VARCHAR(64) NOT NULL,
   `url` VARCHAR(2048) NOT NULL DEFAULT '',
   `tag_version` INT(11) NOT NULL DEFAULT 1, 
   `task_id` INT(11) NOT NULL,
   `task_name` VARCHAR(64) NOT NULL,
   `create_time` DATETIME NOT NULL DEFAULT '1970-1-1 00:00:00',
   `chatime` datetime NOT NULL,
   `add_time` datetime NOT NULL,
   `add_user` varchar(64) NOT NULL,
   `task_type` TINYINT(4) NOT NULL DEFAULT 1,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
DROP TABLE IF EXISTS `tb_review_group`;
CREATE TABLE `tb_review_group` (
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `tag_group_id` BIGINT(20) UNSIGNED NOT NULL,
    `group_id` BIGINT(20) NOT NULL,
    `data_type` TINYINT(4) NOT NULL DEFAULT 0,
    `task_id` INT(11) NOT NULL,
    `task_name` VARCHAR(255) NOT NULL DEFAULT '',
    `task_id_review` INT(11) NOT NULL,
    `task_name_review` VARCHAR(255) NOT NULL,
    `ad_num` INT(11) NOT NULL,
    `ad_num_review` INT(11) NOT NULL,
    `status` TINYINT(4) NOT NULL DEFAULT 0,
    `modify_user_id` INT(11) NOT NULL DEFAULT -1,
    `modify_user_id_review` INT(11) NOT NULL DEFAULT -1,
    `start_time` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00',
    `done_time` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00',
    PRIMARY KEY(`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;
  
DROP TABLE IF EXISTS `tb_review_task`;
CREATE TABLE `tb_review_task` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `task_name` varchar(255) NOT NULL,
    `add_user` varchar(255) NOT NULL,
    `add_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
    `begin_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
    `end_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
    `task_list` VARCHAR(255) NOT NULL DEFAULT '',
    `status` TINYINT(4) NOT NULL DEFAULT 0,
    `moduser_level` INT(11) NOT NULL DEFAULT 1,
    `blind` TINYINT(1) NOT NULL DEFAULT 1,
    `group_num` INT(11) NOT NULL DEFAULT 0,
    `group_num_actual` INT(11) NOT NULL,
    `task_condition` INT(11) NOT NULL,
    `task_type` TINYINT(4) NOT NULL DEFAULT 1,
    PRIMARY KEY (`id`),
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
DROP TABLE IF EXISTS `tb_review_task_condition`;
CREATE TABLE `tb_review_task_condition` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `data_type` INT(11) NOT NULL DEFAULT 0,
    `ad_tag_condition` INT(11) UNSIGNED NOT NULL DEFAULT 0,
    `wuliao_type` VARCHAR(255) NOT NULL DEFAULT '',
    `ad_trade_type` VARCHAR(255) NOT NULL DEFAULT '',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_review_ad_task`;
CREATE TABLE `tb_review_ad_task` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `data_type` TINYINT(4) NOT NULL,
    `ref_ad_id` bigint(20) NOT NULL,
    `group_id` bigint(20) NOT NULL,
    `group_id_review` bigint(20) NOT NULL,
    `task_id` bigint(20) NOT NULL,
    `wuliao_type` INT(11) NOT NULL DEFAULT 0,
    `ad_trade_id_level3` INT(11) NOT NULL,
    `ad_trade_id_level3_review` INT(11) NOT NULL,
    `ad_tag` INT(11) NOT NULL,
    `ad_tag_review` INT(11) NOT NULL,
    `comment` varchar(255) NOT NULL DEFAULT '',
    `comment_review` varchar(255) NOT NULL DEFAULT '',
    `add_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
    `add_user` varchar(64) NOT NULL DEFAULT 'System',
    `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
    `update_user` varchar(64) NOT NULL DEFAULT 'System',
    `assigned` TINYINT(4) NOT NULL DEFAULT 0,
    `task_type` TINYINT(4) NOT NULL DEFAULT 1,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
DROP TABLE IF EXISTS `tb_data_load_info`;
CREATE TABLE `tb_data_load_info` (
   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
   `file_name` VARCHAR(255) NOT NULL DEFAULT '',
   `file_size` BIGINT(20) NOT NULL DEFAULT '0',
   `data_type` TINYINT(4) NOT NULL DEFAULT '0',
   `status` TINYINT(4) NOT NULL DEFAULT '0',
   `scan_record` INT(11) NOT NULL DEFAULT '0',
   `insert_record` INT(11) NOT NULL DEFAULT '0',
   `md5_value` VARCHAR(255) NOT NULL DEFAULT '',
   `add_time` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00',
   PRIMARY KEY (`id`),
)ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_ad_tag`;
CREATE TABLE `tb_ad_tag` (
   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
   `ref_id` BIGINT(20) UNSIGNED NOT NULL,
   `ref_group_id` BIGINT(20) NOT NULL,
   `task_id` INTEGER(11) NOT NULL,
   `data_type` INTEGER(11) NOT NULL,
   `ad_tag` VARCHAR(64) NOT NULL DEFAULT '',
   `ad_trade_id_level3` INTEGER(11) NOT NULL DEFAULT 0,
   `comment` VARCHAR(255) NOT NULL DEFAULT '',
   `update_time` DATETIME NOT NULL,
   `update_user` VARCHAR(64) NOT NULL,
   `assigned` TINYINT(4) NOT NULL DEFAULT 0,
   `general_wuliao_type` INTEGER(11) NOT NULL,
   PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_tag_type`;
CREATE TABLE `tb_tag_type` (
   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
   `trade_id` INTEGER(11) UNSIGNED NOT NULL,
   `tag_type` VARCHAR(50) NOT NULL,
   PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_statistics_dim_his`;
CREATE TABLE `tb_statistics_dim_his` (
   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
   `task_id` int(11) NOT NULL,
   `high_beauty_num` int(11) NOT NULL DEFAULT '0',
   `normal_beauty_num` int(11) NOT NULL DEFAULT '0',
   `low_beauty_num` int(11) NOT NULL DEFAULT '0',
   `black_vulgar_num` int(11) NOT NULL DEFAULT '0',
   `gray_vulgar_num` int(11) NOT NULL DEFAULT '0',
   `white_vulgar_num` int(11) NOT NULL DEFAULT '0',
   `is_cheat_num` int(11) NOT NULL DEFAULT '0',
   `not_cheat_num` int(11) NOT NULL DEFAULT '0',
   `is_high_danger_num` int(11) NOT NULL DEFAULT '0',
   `not_high_danger_num` int(11) NOT NULL DEFAULT '0',
   PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
 
DROP TABLE IF EXISTS `tb_statistics_trend_his`;
CREATE TABLE `tb_statistics_trend_his` (
   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
   `task_id` int(11) NOT NULL,
   `user_name` VARCHAR(255) NOT NULL DEFAULT '',
   `time_6` int(11) NOT NULL DEFAULT '0',
   `time_7` int(11) NOT NULL DEFAULT '0',
   `time_8` int(11) NOT NULL DEFAULT '0',
   `time_9` int(11) NOT NULL DEFAULT '0',
   `time_10` int(11) NOT NULL DEFAULT '0',
   `time_11` int(11) NOT NULL DEFAULT '0',
   `time_12` int(11) NOT NULL DEFAULT '0',
   `time_13` int(11) NOT NULL DEFAULT '0',
   `time_14` int(11) NOT NULL DEFAULT '0',
   `time_15` int(11) NOT NULL DEFAULT '0',
   `time_16` int(11) NOT NULL DEFAULT '0',
   `time_17` int(11) NOT NULL DEFAULT '0',
   `time_18` int(11) NOT NULL DEFAULT '0',
   `time_19` int(11) NOT NULL DEFAULT '0',
   `time_20` int(11) NOT NULL DEFAULT '0',
   PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_statistics_user_his`;
CREATE TABLE `tb_statistics_user_his` (
   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
   `task_id` int(11) NOT NULL,
   `user_name` VARCHAR(255) NOT NULL DEFAULT '',
   `is_done_groups` int(11) NOT NULL DEFAULT '0',
   `is_done_ads` int(11) NOT NULL DEFAULT '0',
   PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_new_trade_type`;
 CREATE TABLE `tb_new_trade_type` (
 	`first_id` INT(11) NOT NULL,
 	`first_name` varchar(255) NOT NULL,
 	`second_id` INT(11) NOT NULL,
 	`second_name` varchar(255) NOT NULL,
 	`third_id` INT(11) NOT NULL,
 	`third_name` varchar(255) NOT NULL,
 	`full_id` INT(11) NOT NULL,
 	`full_name` varchar(255) NOT NULL,
 	`level2_id` INT(11) NOT NULL DEFAULT 0,
 	`level2_name` VARCHAR(64) NOT NULL DEFAULT '',
 	PRIMARY KEY (`full_id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
  
 DROP TABLE IF EXISTS `tb_trade_type`;
 CREATE TABLE `tb_trade_type` (
 	`first_id` INT(11) NOT NULL,
 	`first_name` varchar(255) NOT NULL,
 	`second_id` INT(11) NOT NULL,
 	`second_name` varchar(255) NOT NULL,
 	`full_id` INT(11) NOT NULL,
 	`full_name` varchar(255) NOT NULL,
 	PRIMARY KEY (`full_id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
