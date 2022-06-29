package com.mabushizai.maibudu.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Pengyu Gan
 * CreateDate 2022/6/29
 */
@Data
public class ShareShelf {
    /**
     * 导入者 ID
     */
    private String importerId;

    /**
     * 分享者 ID
     */
    private String exporterId;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;
}