drop database if exists wcrs;
create database wcrs;
use wcrs;

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
    parent         int               NOT NULL DEFAULT 0,        #   must be a valid scene_id
    nick_name      varchar(40)       NOT NULL,	                #	姓   
    gender         varchar(6)        NOT NULL DEFAULT 'MALE',	#	性别  //user info.
    language       varchar(6)        NOT NULL DEFAULT 'cn',
    city           varchar(30)       NOT NULL DEFAULT '',
    province       varchar(30)       NOT NULL DEFAULT '',	
    country        varchar(64)       NOT NULL DEFAULT '',	
    head_img_url   varchar(128)      NOT NULL DEFAULT '',	 
    create_t       datetime          DEFAULT NULL,	            #	记录时间
    modify_t       datetime          DEFAULT NULL,	            #	记录更新时间
    status         smallint          NOT NULL DEFAULT 0,
    ticket         varchar(100)       DEFAULT NULL	            #
)  DEFAULT CHARSET=utf8;

###############################################################
# wc_event
###############################################################
CREATE TABLE if not exists wc_event (
    id              int unsigned       NOT NULL auto_increment primary key,
    to_user_name    varchar(30)        NOT NULL DEFAULT '',
    from_user_name  varchar(30)        NOT NULL DEFAULT '',
    msg_type        varchar(20)        NOT NULL DEFAULT '',
    event           varchar(20)        NOT NULL DEFAULT '',
    event_key       varchar(20)        NOT NULL DEFAULT '',
    create_t        datetime           DEFAULT NULL	            #	记录时间
)  DEFAULT CHARSET=utf8;

