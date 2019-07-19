create database dianping;
use dianping;
create table `ad`(
    `id` int(11) not null auto_increment comment '主键',
    `title` varchar(50) default null comment '标题',
    `img_file_name` varchar(100) default null comment '图片文件名',
    `link` varchar(200) default null comment '链接地址',
    `weight` int(11) default null comment '权重',
    primary key (`id`)
)ENGINE = InnoDB default charset = UTF8MB4;
