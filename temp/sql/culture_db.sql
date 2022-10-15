drop database if exists culture_db;
create database culture_db;
use culture_db;
set SQL_SAFE_UPDATES = 0;
#유저테이블 생성
create table user_basic
(
    id      varchar(20)        not null,
    pwd     varchar(20)        not null,
    name    varchar(20)        not null,
    regDate datetime default now() null,
    constraint user_basic_pk
        primary key (id)
);

create table user_info
(
    u_info_no bigint auto_increment,
    id        varchar(20) not null,
    age       int         not null,
    gender    int         not null,
    constraint user_basic_pk
        primary key (u_info_no)
);
alter table user_info
    add constraint user_info___fk
        foreign key (id) references user_basic (id);

create table user_res
(
    u_res_no bigint,
    id       varchar(20) not null,
    email    varchar(20) not null,
    phone    varchar(20) not null,
    constraint user_res_pk
        primary key (u_res_no),
    constraint user_res___fk
        foreign key (id) references user_basic (id)
);
alter table user_res
    modify u_res_no bigint auto_increment;

#행사테이블 작성
create table culture_basic
(
    cno      bigint,
    name     varchar(20) not null,
    location varchar(30) not null,
    phone    varchar(20) not null,
    regDate datetime default now() null,
    constraint culture_basic_pk
        primary key (cno)
);
alter table culture_basic
    modify cno bigint auto_increment;

create table culture_info
(
    c_info_no  bigint auto_increment,
    cno        bigint      not null ,
    target     Integer null,
    select_way Integer null,
    res_way    Integer null,
    constraint culture_info_pk
        primary key (c_info_no),
    constraint culture_info___fk
        foreign key (cno) references culture_basic (cno)
);

create table culture_res
(
    c_res_no bigint auto_increment,
    cno      bigint not null,
    capacity int    not null,
    price    int    not null,
    `limit`  int    not null,
    constraint culture_res_pk
        primary key (c_res_no),
    constraint culture_res___fk
        foreign key (cno) references culture_basic (cno)
);

create table culture_schedule
(
    s_sc_no         bigint auto_increment,
    cno             bigint      not null,
    c_duration_from varchar(20) not null,
    c_duration_to   varchar(20) not null,
    r_duration_from varchar(20) not null,
    r_duration_to   varchar(20) not null,
    rc_duration     varchar(20) not null,
    constraint culture_schedule_pk
        primary key (s_sc_no),
    constraint culture_schedule_culture_basic_null_fk
        foreign key (cno) references culture_basic (cno)
);

#리뷰테이블 생성
create table review
(
    re_no   bigint auto_increment,
    id      varchar(20)        not null,
    cno     bigint             not null,
    content text               not null,
    grade   int                not null,
    regDate date default now() not null,
    constraint review_pk
        primary key (re_no),
    constraint review_culture_basic_null_fk
        foreign key (cno) references culture_basic (cno),
    constraint review_user_basic_null_fk
        foreign key (id) references user_basic (id)
);

#예약테이블 생성
create table reservation_basic
(
    rno     bigint auto_increment,
    id      varchar(20)        not null,
    cno     bigint             not null,
    regDate date default now() not null,
    constraint reservation_basic_pk
        primary key (rno),
    constraint reservation_basic_culture_basic_null_fk
        foreign key (cno) references culture_basic (cno),
    constraint reservation_basic_user_basic_null_fk
        foreign key (id) references user_basic (id)
);

create table reservation_schedule
(
    r_rc_no           bigint auto_increment,
    rno               bigint not null,
    user_res_schedule date   not null,
    constraint reservation_schedule_pk
        primary key (r_rc_no),
    constraint reservation_schedule_reservation_basic_null_fk
        foreign key (rno) references reservation_basic (rno)
);

#QnA
create table QnA_Q
(
    qqno    bigint             not null auto_increment,
    id      varchar(20)        not null,
    regDate date default now() not null,
    constraint QnA_Q_pk
        primary key (qqno),
    constraint QnA_Q_user_basic_null_fk
        foreign key (id) references user_basic (id)
);

create table QnA_A
(
    qano    bigint             not null auto_increment,
    qqno    bigint             not null,
    regDate date default now() not null,
    constraint QnA_A_pk
        primary key (qano),
    constraint QnA_A_QnA_Q_null_fk
        foreign key (qqno) references QnA_Q (qqno)
);

create table QnA_content
(
    qac_no bigint auto_increment,
    qqno   bigint not null,
    qano   bigint not null,
    constraint QnA_content_pk
        primary key (qac_no),
    constraint QnA_content_QnA_A_null_fk
        foreign key (qano) references QnA_A (qano),
    constraint QnA_content_QnA_Q_null_fk
        foreign key (qqno) references QnA_Q (qqno)
);

set SQL_SAFE_UPDATES = 1;









