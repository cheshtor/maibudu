DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `douban_id`    varchar(2000) DEFAULT NULL COMMENT '豆瓣 ID',
    `uid`          varchar(64)   DEFAULT NULL COMMENT '第一次添加图书的用户',
    `title`        varchar(350)  DEFAULT NULL COMMENT '书籍名称',
    `subtitle`     varchar(800)  DEFAULT NULL COMMENT '副标题',
    `author`       varchar(600)  DEFAULT NULL COMMENT '作者',
    `publish_date` varchar(100)  DEFAULT NULL COMMENT '出版日期',
    `publisher`    varchar(100)  DEFAULT NULL COMMENT '出版社名称',
    `isbn`         varchar(30)   DEFAULT NULL COMMENT 'ISBN 码',
    `summary`      longtext COMMENT '内容简介',
    `pages`        varchar(100)  DEFAULT NULL COMMENT '总页数',
    `price`        varchar(100)  DEFAULT NULL COMMENT '销售价格',
    `binding`      varchar(100)  DEFAULT NULL COMMENT '装订方式',
    `cover`        varchar(100)  DEFAULT NULL COMMENT '封面',
    `score`        varchar(10)   DEFAULT NULL COMMENT '评分',
    `create_date`  datetime      DEFAULT NULL COMMENT '入库时间',
    PRIMARY KEY (`id`),
    KEY `idx_title` (`title`),
    KEY `idx_isbn` (`isbn`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


DROP TABLE IF EXISTS `share_shelf`;
CREATE TABLE `share_shelf`
(
    `importer_id`       varchar(30) NOT NULL COMMENT '导入者 ID',
    `exporter_id`       varchar(30) NOT NULL COMMENT '分享者 ID',
    `exporter_code`     varchar(8)    DEFAULT NULL COMMENT '分享者的分享码',
    `exporter_nickname` varchar(64)   DEFAULT NULL COMMENT '分享者昵称',
    `exporter_avatar`   varchar(1024) DEFAULT NULL COMMENT '分享者头像',
    `create_date`       datetime      DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`importer_id`, `exporter_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


DROP TABLE IF EXISTS `shelf_book`;
CREATE TABLE `shelf_book`
(
    `uid`         varchar(30) NOT NULL COMMENT '用户ID',
    `book_id`     bigint(20)  NOT NULL COMMENT '书籍ID',
    `create_date` datetime DEFAULT NULL COMMENT '添加时间',
    PRIMARY KEY (`uid`, `book_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `uid`         varchar(30) NOT NULL COMMENT '用户ID',
    `code`        varchar(8)    DEFAULT NULL COMMENT '用户唯一编码，用于分享等场景',
    `nickname`    varchar(64)   DEFAULT NULL COMMENT '昵称',
    `avatar`      varchar(1024) DEFAULT NULL COMMENT '头像',
    `create_date` datetime      DEFAULT NULL COMMENT '创建时间',
    `sys_status`  tinyint(2)    DEFAULT '0' COMMENT '0 - 正常，1 - 删除',
    PRIMARY KEY (`uid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;