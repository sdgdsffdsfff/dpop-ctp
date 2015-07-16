-- tb_beidou_task index
ALTER TABLE tb_beidou_task DROP INDEX `idx_user_id`;
ALTER TABLE tb_beidou_task DROP INDEX `idx_task_id`;

ALTER TABLE tb_beidou_task ADD INDEX `idx_ad_tag`(`ad_tag`);
ALTER TABLE tb_beidou_task ADD INDEX `idx_task_id_group_id`(`task_id`, `group_id`);

-- tb_qiushi_task index
ALTER TABLE tb_qiushi_task DROP INDEX `idx_user_id`;
ALTER TABLE tb_qiushi_task DROP INDEX `idx_task_id`;

ALTER TABLE tb_qiushi_task ADD INDEX `idx_ad_tag`(`ad_tag`);
ALTER TABLE tb_qiushi_task ADD INDEX `idx_task_id_group_id`(`task_id`, `group_id`);

-- tb_dsp_task index
ALTER TABLE tb_dsp_task DROP INDEX `idx_user_id`;
ALTER TABLE tb_dsp_task DROP INDEX `idx_task_id`;

ALTER TABLE tb_dsp_task ADD INDEX `idx_ad_tag`(`ad_tag`);
ALTER TABLE tb_dsp_task ADD INDEX `idx_task_id_user_id`(`task_id`, `user_id`);

-- tb_group index
ALTER TABLE tb_group DROP INDEX `group_id`;
ALTER TABLE tb_group ADD INDEX `idx_task_id`(`task_id`);
ALTER TABLE tb_group ADD INDEX `idx_group_id`(`group_id`);

-- tb_operation_record
ALTER TABLE tb_operation_record ADD INDEX `idx_operator`(`operator`);

-- 添加unique key
ALTER TABLE tb_group ADD UNIQUE INDEX `uk_group_id_data_type_task_id`(`group_id`, `data_type`, `task_id`);

