create database coin character set utf8mb4 default collate utf8mb4_general_ci;
use coin;

create table coin (
	idx BIGINT not null auto_increment primary key,
    opening_price double not null,
    high_price double not null,
    low_price double not null,
    trade_price double not null,
    candle_acc_trade_volume double not null,
    candle_date_time_kst datetime not null
);

create table member (
	idx BIGINT not null auto_increment primary key,
    loginId varchar(30) not null unique,
    password varchar(255) not null,
    nickname varchar(30) not null unique,
    memberType varchar(20) not null,
    createAt datetime not null,
    updateAt datetime not null
);

create table refresh_token (
	rt_key varchar(30) not null primary key,
    rt_value varchar(255) not null
);



select * from coin;
select * from member;
select * from refresh_token;
drop table member;
drop table refresh_token;