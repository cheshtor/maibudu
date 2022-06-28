package com.mabushizai.maibudu.constants;

/**
 * 数据系统状态枚举
 *
 * @author Pengyu Gan
 * CreateDate 2022/6/27
 */
public enum SysStatusEnum {

    NORMAL("正常", (byte) 0),
    DELETED("删除", (byte) 1);

    private String name;

    private Byte value;

    SysStatusEnum(String name, Byte value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Byte getValue() {
        return value;
    }
}
