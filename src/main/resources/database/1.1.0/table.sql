DROP TABLE IF EXISTS `tb_ad_tag`;
CREATE TABLE `tb_ad_tag` (
   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   `ref_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '关联的ad的主键id',
   `ref_group_id` BIGINT(20) NOT NULL COMMENT '关联group的主键id',
   `task_id` INTEGER(11) NOT NULL COMMENT '标注任务id',
   `data_type` INTEGER(11) NOT NULL COMMENT '数据类型',
   `ad_tag` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '标签',
   `ad_trade_id_level3` INTEGER(11) NOT NULL DEFAULT 0 COMMENT '创意三级行业',
   `comment` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '备注',
   `update_time` datetime NOT NULL COMMENT '修改时间',
   `update_user` varchar(64) NOT NULL COMMENT '修改人',
   PRIMARY KEY pk_tb_ad_tag(`id`),
   KEY idx_ref_id(`ref_id`),
   KEY idx_ref_group_id(`ref_group_id`),
   KEY idx_task_id(`task_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='标注信息表';

DROP TABLE IF EXISTS `tb_tag_type`;
CREATE TABLE `tb_tag_type` (
   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   `trade_id` INTEGER(11) UNSIGNED NOT NULL COMMENT '行业id，一级行业后4位为0，二级行业后2位为0',
   `tag_type` VARCHAR(50) NOT NULL COMMENT '标签名称',
   PRIMARY KEY pk_tb_tag_type(`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='标签类型与行业关联表';

ALTER TABLE tb_beidou_task MODIFY COLUMN ad_tag VARCHAR(64) NOT NULL COMMENT '原始标签';
ALTER TABLE tb_beidou_task DROP update_time;
ALTER TABLE tb_beidou_task DROP update_user;
ALTER TABLE tb_beidou_task DROP comment;

ALTER TABLE tb_qiushi_task MODIFY COLUMN ad_tag VARCHAR(64) NOT NULL COMMENT '原始标签';
ALTER TABLE tb_qiushi_task DROP update_time;
ALTER TABLE tb_qiushi_task DROP update_user;
ALTER TABLE tb_qiushi_task DROP comment;

ALTER TABLE tb_dsp_task MODIFY COLUMN ad_tag VARCHAR(64) NOT NULL COMMENT '原始标签';
ALTER TABLE tb_dsp_task DROP update_time;
ALTER TABLE tb_dsp_task DROP update_user;
ALTER TABLE tb_dsp_task DROP comment;