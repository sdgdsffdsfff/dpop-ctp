
insert into tb_group (id, group_id, task_name, task_id, data_type, ad_num, status, priority, modify_user_id, start_time) values
(1, 1000, 'task_0', 0, 0, 10, 2, 1, 0, null),
(2, 1001, 'task_0', 0, 0, 10, 1, 2, 1, '2000-1-1 00:00:00'),
(3, 1002, 'task_0', 0, 0, 10, 0, 3, 0, null),
(4, 1003, 'task_0', 0, 0, 10, 0, 4, 0, null),
(5, 1004, 'task_0', 0, 0, 10, 0, 5, 1, null),
(6, 1005, 'task_0', 0, 0, 10, 0, 6, 0, null),
(7, 1006, 'task_0', 0, 0, 10, 0, 7, 0, null),
(8, 1007, 'task_0', 0, 0, 10, 0, 8, 0, null),
(9, 1008, 'task_0', 0, 0, 10, 0, 9, 0, null),
(10, 1009, 'task_0', 0, 0, 10, 0, 10, 0, null);

insert into tb_user(id, user_name, role_type, password, add_time, update_time)
values(0, 'aa', 11, 'test', '2014-11-20 11:00:00', '2014-11-20 11:00:00'),
(1, 'bb', 11, 'test', '2014-11-20 11:00:00', '2014-11-20 11:00:00');

insert into tb_task (id, task_name, add_time, status, moduser_level) values 
(0, 'task_0', '2014-1-1', 11, 1);

insert into tb_ad_tag (id, ref_group_id ,ad_tag, ad_trade_id_level3) values
(0, 1, '1111', 510101);