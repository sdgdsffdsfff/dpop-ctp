
insert into tb_group (group_id, task_name, task_id, ad_num, status, priority, modify_user_id) values
(11, 'task_0', 1001, 10, 2, 1, 0),
(12, 'task_0', 1001, 10, 1, 2, 1);

insert into tb_dsp_task(id, ad_id, user_id, dsp_id,wuliao_type,width,height,ad_trade_id_level2,ad_trade_id_level3,
ad_tag,mc_id,mc_version_id,priority,second_priority,moduser_level,task_id,chatime,add_time,add_user)
values(1,1,11,1,1,20,21,30,31,8,1001,1,1,1,0,1001,'2014-11-20 11:00:00','2014-11-20 11:00:00', 'cgd'),
(2,2,12,1,1,20,21,30,31,16,1001,1,1,1,0,1001,'2014-11-20 11:00:00','2014-11-20 11:00:00', 'cgd');

