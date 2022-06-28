package com.mabushizai.maibudu.dto;

import lombok.Data;

/**
 * 分页模型
 *
 * @author Gan Pengyu
 * CreateDate 2022/6/21
 */
@Data
public class PageModel {

    /**
     * 请求页码
     */
    private Long pageNo;

    /**
     * 请求当页数据量
     */
    private Integer pageSize;

    /**
     * 数据总量
     */
    private Long totalCount;

    /**
     * 总页数 = 数据总量 / 请求当页数据量
     */
    private Long totalPages;

    public PageModel(Long pageNo, Integer pageSize) {
        this.setPageNo(pageNo);
        this.setPageSize(pageSize);
    }

    public void setPageNo(long pageNo) {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        this.pageNo = pageNo;
    }

    public void setPageSize(int pageSize) {
        if (pageSize <= 0) {
            pageSize = 10;
        }
        this.pageSize = pageSize;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        this.totalPages =
                totalCount % pageSize == 0 ?
                        totalCount / pageSize :
                        totalCount / pageSize + 1;
    }

}
