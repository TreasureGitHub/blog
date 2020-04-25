-- --创建数据库
-- create database jianshu;
-- --创建用户名密码
-- create user 'jianshu'@'localhost' identified by 'jianshu';
--
-- -- 授权
-- grant all privileges on *.* to 'jianshu'@'localhost';

-- 建表语句
-- 用户信息
drop table if exists `user`;
create table `user` (
  `id` bigint  not null auto_increment,
  gmt_create  bigint not null comment '创建时间',
  gmt_modified bigint not null comment '更新时间',
  is_deleted   varchar(100) not null default '0' comment '是否删除',
`name` varchar(255) not null  comment '姓名' ,
`password` varchar(255) not null comment '密码' ,
`email` varchar(100) not null comment '邮箱' ,
`url` varchar(100) default ''  comment 'url' ,
`last_login_time` bigint comment '上次登录时间' ,
`role` int(1) unsigned default '2'  comment '用户角色 1：admin;2:普通用户' ,
primary key (`id`),
unique key (email)
);

-- 文章
drop table if exists article;
create table article (
`id` bigint  not null auto_increment,
  gmt_create  bigint not null comment '创建时间',
  gmt_modified bigint not null comment '更新时间',
  is_deleted   varchar(100) not null default '0' comment '是否删除',
  title varchar(500) not null comment '标题',
  summary varchar(500) not null  comment '摘要',
  content text not null comment '文章内容',
primary key (`id`)
) ;

-- 标签
drop table if exists `tag`;
create table `tag` (
  `id` bigint  not null auto_increment,
  gmt_create  bigint not null comment '创建时间',
  gmt_modified bigint not null comment '更新时间',
  is_deleted   varchar(100) not null default '0' comment '是否删除',
`name` varchar(255) not null  comment '标签名称' ,
primary key (`id`)
);
