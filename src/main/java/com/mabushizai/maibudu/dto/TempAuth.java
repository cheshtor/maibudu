package com.mabushizai.maibudu.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Gan Pengyu
 * CreateDate 2022/6/26
 */
@Data
public class TempAuth implements Serializable {

    @JSONField(name = "TmpSecretId")
    private String tmpSecretId;

    @JSONField(name = "TmpSecretKey")
    private String tmpSecretKey;

    @JSONField(name = "Token")
    private String token;

    @JSONField(name = "ExpiredTime")
    private String expiredTime;

}
