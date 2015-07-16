insert into tb_review_group(id,tag_group_id,group_id,data_type,task_id,task_name,task_id_review,
task_name_review,ad_num,ad_num_review,status,modify_user_id,modify_user_id_review)
values(1,1001,501,0,1111,'task_name',2222,'review_task',10,5,0,1,1),
(2,1002,502,0,1111,'task_name',2222,'review_task',10,5,2,1,1),
(3,1003,503,0,1112,'task_name_2',2223,'review_task_2',10,5,2,1,1);

insert into tb_user(id, user_name, role_type, password, add_time, update_time)
values(1, 'cgd', 0, 'aa', '2014-10-10 00:00:00', '2014-10-10 00:00:00');

insert into tb_review_ad_task(id, data_type, ref_ad_id, group_id, group_id_review, task_id, 
ad_trade_id_level3,ad_trade_id_level3_review,ad_tag, ad_tag_review,add_time,add_user,update_time,update_user)
values(1, 0, 1, 502, 2, 1111, 721,721,127,127,'2014-10-10 00:00:00','add_cgd','2014-10-10 00:00:00','upd_cgd'),
(2, 0, 1, 503, 3, 1112, 721,712,127,127,'2014-10-10 00:00:00','add_cgd','2014-10-10 00:00:00','upd_cgd');

