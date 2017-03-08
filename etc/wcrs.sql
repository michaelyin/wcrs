﻿drop database if exists wcrs;
create database wcrs;
use wcrs;

DROP TABLE IF EXISTS account;
###############################################################
##1.  start from mysql 5.5, innodb is the default storage enginer
##2.  use unsigned
##3.  use NOT NULL if possible
##4.  use ENUM
##5.  table name in SINGULAR
## http://www.devshed.com/c/a/mysql/designing-a-mysql-database-tips-and-techniques/
###############################################################

###############################################################
# user_info
###############################################################
CREATE TABLE if not exists user_info (
    openid         VARCHAR(36)       primary key NOT NULL,
    scene_id       int               NOT NULL DEFAULT 0,
    parent         int               NOT NULL DEFAULT 0,
    nick_name      varchar(40)       NOT NULL,	        #	姓   
    gender         varchar(6)        NOT NULL DEFAULT 'MALE',	#	性别  //user info.
    city           varchar(30)       NOT NULL DEFAULT '',
    province       int               NOT NULL DEFAULT 999,	
    country        varchar(64)       NOT NULL DEFAULT '',	
    head_img_url   varchar(128)      NOT NULL DEFAULT '',	 
    create_t       datetime          DEFAULT NULL,	                    #	记录时间
    modify_t       datetime          DEFAULT NULL,	                    #	记录更新时间
    ticket         varchar(32)       DEFAULT NULL	        #
)  DEFAULT CHARSET=utf8;
