create database trytryagain;
drop table if exists user;
create table user(
 id int(11) unsigned NOT NULL AUTO_INCREMENT,
 userName varchar(128),
 loginEmail varchar(128),
 password varchar(128),
 sex varchar(8),
 createTime datetime,
 lastLoginTime datetime,
 isDel int(1) default 0,
 PRIMARY KEY (id)
)CHARSET=UTF8;
drop table if exists trytryagain;
create table trytryagain(
id int(11) unsigned NOT NULL AUTO_INCREMENT,
tags varchar(128),
userId int(11),
userName varchar(128),
title varchar(128),
info varchar(1024),
img varchar(128),
content text,
createTime datetime,
updateTime datetime,
isDel int(1) default 0,
primary  key (id)
)CHARSET=UTF8;
insert into trytryagain values(2,'测试',1,'zeal','大道理','每个人都会经过这个阶段，见到一座山，就想知道山后面是什么。我很想告诉他，可能翻过山后面，你会发现没什么特别。回望之下，可能会觉得这一边更好。 每个人都会坚持自己的信念，在别人看来，是浪费时间，我却觉得很重要 。','http://a2.att.hudong.com/38/59/300001054794129041591416974.jpg','','2015-12-5 23:28:30','2015-12-5 23:28:30',0);
insert into trytryagain values(1,'测试',1,'zeal','大道理','有一只小猴子，肚肚被树枝划伤了，流了很多血。它见到一个猴子朋友就扒开伤口说，你看我的伤口，好痛。每个看见它伤口的猴子都安慰它，同情它，告诉它不同的治疗方法，它继续给朋友们看伤口。继续听取意见，后来它感染死掉了。一个老猴子说，它是因自己而死的。痛，说一次就复习一次。','https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=4221506303,1688976220&fm=58','','2015-12-5 23:28:30','2015-12-5 23:28:30',0);
insert into trytryagain values(3,'测试',1,'zeal','大道理','骏马面前无沟壑，怂人面前全是坎。','','','2015-12-5 23:28:30','2015-12-5 23:28:30',0);
insert into trytryagain values(4,'测试',1,'zeal','大道理','怕什么真理无穷，进一寸有一寸的欢喜。','','','2015-12-5 23:28:30','2015-12-5 23:28:30',0);

-- create table step(
-- id int(11),
-- tryId int(11),
-- stepName varchar (256),
-- stepNo int(11),
-- content text,
-- createTime datetime,
-- updateTime datetime,
-- isDel int(1) default 0,
-- primary key (id)
-- );
