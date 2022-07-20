package com.mabushizai.maibudu.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Pengyu Gan
 * CreateDate 2022/7/20
 */
@Data
public class BookCompleteInfo {

    /**
     * 书籍 ID
     */
    private Long id;

    private String isbn;

    private String title;

    private String subtitle;

    private String author;

    private String publishDate;

    private String publisher;

    private String cover;

    /**
     * 书籍所属用户 ID
     */
    private String ownerId;

    /**
     * 书籍所属用户昵称
     */
    private String ownerNickname;

    /**
     * 书籍所属用户头像
     */
    private String ownerAvatar;

    /**
     * 用户扫描这本书的时间
     */
    private LocalDateTime ownerScanTime;

    /**
     * 第一次添加这本书到系统的用户的 ID
     */
    private String creatorId;

    /**
     * 书籍第一次被添加到系统的时间
     */
    private LocalDateTime stockTime;

}
