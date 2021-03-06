package com.mabushizai.maibudu.utils;

/**
 * @author Pengyu Gan
 * CreateDate 2022/6/21
 */
public class StopWatch {

    /**
     * 启动时间
     */
    private long start;

    public StopWatch() {
        this.start = System.currentTimeMillis();
    }

    /**
     * 重置计时器，将启动时间设置为当前时间
     */
    public void reset() {
        this.start = System.currentTimeMillis();
    }

    /**
     * 获取计时长度
     * @return 计时长度
     */
    public long getElapse() {
        return System.currentTimeMillis() - start;
    }

}
