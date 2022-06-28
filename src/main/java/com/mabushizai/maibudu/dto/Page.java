package com.mabushizai.maibudu.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果
 *
 * @author Gan Pengyu
 * CreateDate 2022/6/21
 */
@Data
public class Page<T> implements Serializable {

    /**
     * 请求数据
     */
    private List<T> rows;

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

    public Page(PageModel pageModel, List<T> rows) {
        this.rows = rows;
        this.pageNo = pageModel.getPageNo();
        this.pageSize = pageModel.getPageSize();
        this.totalCount = pageModel.getTotalCount();
        this.totalPages = pageModel.getTotalPages();
    }

}
