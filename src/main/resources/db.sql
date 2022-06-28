DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`
(
    `id`           BIGINT(20) COMMENT '主键ID',
    `uid`          VARCHAR(30) COMMENT '第一次扫描本书的用户ID',
    `title`        VARCHAR(128) COMMENT '书籍名称',
    `subtitle`     VARCHAR(128) COMMENT '副标题',
    `author`       VARCHAR(128) COMMENT '作者',
    `publish_date` DATE COMMENT '出版日期',
    `publisher`    VARCHAR(64) COMMENT '出版社名称',
    `isbn`         VARCHAR(16) COMMENT 'ISBN 码',
    `summary`      VARCHAR(1024) COMMENT '内容简介',
    `pages`        INT(10) COMMENT '总页数',
    `price`        DECIMAL(7, 2) COMMENT '销售价格',
    `binding`      VARCHAR(16) COMMENT '装订方式',
    `cover`        VARCHAR(512) COMMENT '封面',
    `create_date`  DATETIME COMMENT '入库时间',
    `sys_status`   TINYINT(2) DEFAULT 0 COMMENT '0 - 正常，1 - 删除',
    PRIMARY KEY `pk_id` (`id`)
);

DROP TABLE IF EXISTS `shelf_book`;
CREATE TABLE `shelf_book`
(
    `uid`          VARCHAR(30) COMMENT '用户ID',
    `book_id`      BIGINT(20) COMMENT '书籍ID',
    `read_status`  TINYINT(2) COMMENT '0 - 已读完，1 - 未读完，2 - 未读',
    `create_date`  DATETIME COMMENT '添加时间',
    `sys_status`   TINYINT(2) DEFAULT 0 COMMENT '0 - 正常，1 - 删除',
    PRIMARY KEY `upk_uid_bookId` (`uid`, `book_id`)
);
