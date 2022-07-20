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
     * 分享者分享码
     */
    private String exporterCode;

    /**
     * 分享者昵称
     */
    private String exporterNickname;

    /**
     * 分享者头像
     */
    private String exporterAvatar;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;
}