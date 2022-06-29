package com.mabushizai.maibudu.dao;

import com.mabushizai.maibudu.domain.ShareShelf;
import com.mabushizai.maibudu.dto.PageModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 *
 * @author Pengyu Gan
 * CreateDate 2022/6/29
 */
public interface ShareShelfDao {
    int insert(ShareShelf record);

    ShareShelf selectByPrimaryKey(@Param("importerId") String importerId, @Param("exporterId") String exporterId);

    int removeShare(@Param("importerId") String importerId, @Param("exporterId") String exporterId);

    List<ShareShelf> listShares(@Param("importerId") String importerId, PageModel pageModel);
}