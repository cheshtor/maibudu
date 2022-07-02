package com.mabushizai.maibudu.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户
 *
 * @author Pengyu Gan
 * CreateDate 2022/6/29
 */
@Data
public class User {
    /**
    * 用户ID
    */
    private String uid;

    /**
    * 用户唯一编码，用于分享等场景
    */
    private String code;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
    * 创建时间
    */
    private LocalDateTime createDate;

    /**
    * 0 - 正常，1 - 删除
    */
    private Byte sysStatus;
}