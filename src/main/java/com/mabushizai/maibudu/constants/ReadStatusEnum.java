package com.mabushizai.maibudu.constants;

/**
 * 书籍阅读状态枚举
 *
 * @author Pengyu Gan
 * CreateDate 2022/6/27
 */
public enum ReadStatusEnum {

    READ("已读完", (byte) 0),
    READING("未读完", (byte) 0),
    UNREAD("未读", (byte) 2);

    private String name;

    private Byte value;

    ReadStatusEnum(String name, Byte value) {
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
