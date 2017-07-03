create database atguigu;

use atguigu;

create table t_user(
	`id` int primary key auto_increment,
	`username` varchar(50) not null unique,
	`password` varchar(50) not null,
	`email` varchar(50)
);

insert into t_user(`username`,`password`,`email`) values('admin','admin','admin@atguigu.com');


select * from t_user;