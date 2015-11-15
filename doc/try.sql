create table user(
 id int(11),
 userName varchar(128),
 loginEmail varchar(128),
 password varchar(128),
 sex varchar(8),
 createTime datetime,
 lastLoginTime datetime,
 isDel int(1),
 PRIMARY KEY (id)
);

create table trytryagain(
id int(11),,
tags varchar(128),
userId int(11),
info varchar(1024),
img varchar(128),
createTime datetime,
updateTime datetime,
isDel int(1),
primary  key (id)
);

create table step(
id int(11),
tryId int(11),
stepName varchar (256),
stepNo int(11),
content text,
createTime datetime,
updateTime datetime,
isDel int(1),
primary key (id)
);
