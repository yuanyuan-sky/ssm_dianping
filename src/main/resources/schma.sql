create database dianping;
use dianping;
-- ----------------------------
-- Table structure for ad
-- ----------------------------
create table `ad`(
    `id` int(11) not null auto_increment comment '主键',
    `title` varchar(50) default null comment '标题',
    `img_file_name` varchar(100) default null comment '图片文件名',
    `link` varchar(200) default null comment '链接地址',
    `weight` int(11) default null comment '权重',
    primary key (`id`)
)ENGINE = InnoDB default charset = UTF8MB4;

-- ----------------------------
-- Table structure for business
-- ----------------------------
DROP TABLE if exists `business`;
create table `business`(
    `id` int(11) not null auto_increment comment '主键',
    `img_file_name` varchar(100) default null comment '图片文件名',
    `title` varchar(50) default null comment '标题',
    `subtitle` varchar(100) default null comment '副标题',
    `price` decimal(11,2) default null comment '价格：元',
    `distance` int(11) default null comment '',
    `number` int(11) default null comment '',
    `desc` varchar(500) default null comment '',
    `city` varchar(16) default null comment '',
    `category` varchar(16) default null comment '',
    `star_total_num` int(11) default null comment '',
    `comment_total_num` int(11) default null comment '',
    primary key (id)
)engine = InnoDB default charset = UTF8MB4;

-- ----------------------------
-- Table structure for dic
-- ----------------------------
drop table if exists `dic`;
create table `dic`(
`type` varchar(16) not null ,
`code` varchar(16) not null ,
`name` varchar(16) not null ,
`weight` int(11) default null,
primary key (`type`,`code`)
)engine = InnoDB charset = UTF8MB4;

-- ----------------------------
-- Table structure for member
-- ----------------------------
drop table if exists `member`;
create table `member`
(
    `id`       int         not null comment '编号',
    `phone`    bigint(13)  not null comment '手机号',
    `name`     varchar(36) not null comment '用户名',
    `password` varchar(32) not null comment '密码',
    primary key (`id`),
    unique key `phone_unique` (`phone`),
    unique key `name_unique` (`name`)
) engine = InnoDB charset = UTF8MB4;



