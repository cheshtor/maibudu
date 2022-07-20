package com.mabushizai.maibudu.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 架上图书信息
 *
 * @author Pengyu Gan
 * CreateDate 2022/6/27
 */
@Data
public class ShelfBook {


    /**
     * 用户ID
     */
    private String uid;

    /**
     * 书籍ID
     */
    private Long bookId;

    /**
     * 添加时间
     */
    private LocalDateTime createDate;

}