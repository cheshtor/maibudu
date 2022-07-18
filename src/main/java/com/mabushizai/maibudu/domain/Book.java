package com.mabushizai.maibudu.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 书籍信息
 *
 * @author Pengyu Gan
 * CreateDate 2022/6/27
 */
@Data
public class Book {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 豆瓣图书对应 ID
     */
    private String doubanId;

    /**
     * 第一次扫描本书的用户ID
     */
    private String uid;

    /**
     * 书籍名称
     */
    private String title;

    /**
     * 副标题
     */
    private String subtitle;

    /**
     * 作者
     */
    private String author;

    /**
     * 出版日期
     */
    private String publishDate;

    /**
     * 出版社名称
     */
    private String publisher;

    /**
     * ISBN 码
     */
    private String isbn;

    /**
     * 内容简介
     */
    private String summary;

    /**
     * 总页数
     */
    private String pages;

    /**
     * 销售价格
     */
    private String price;

    /**
     * 装订方式
     */
    private String binding;

    /**
     * 封面
     */
    private String cover;

    /**
     * 书籍豆瓣评分
     */
    private String score;

    /**
     * 入库时间
     */
    private LocalDateTime createDate;

}