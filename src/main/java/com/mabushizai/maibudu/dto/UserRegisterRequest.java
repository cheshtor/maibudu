package com.mabushizai.maibudu.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Gan Pengyu
 * CreateDate 2022/7/2
 */
@Data
public class UserRegisterRequest implements Serializable {

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

}
