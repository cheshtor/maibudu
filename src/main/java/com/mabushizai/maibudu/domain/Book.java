package com.mabushizai.maibudu.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    private LocalDate publishDate;

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
    private Integer pages;

    /**
     * 销售价格
     */
    private BigDecimal price;

    /**
     * 装订方式
     */
    private String binding;

    /**
     * 封面
     */
    private String cover;

    /**
     * 入库时间
     */
    private LocalDateTime createDate;

    /**
     * 0 - 正常，1 - 删除
     */
    private Byte sysStatus;
}