package com.mabushizai.maibudu.dao;

import com.mabushizai.maibudu.domain.BookDetails;
import com.mabushizai.maibudu.domain.ShelfBook;
import com.mabushizai.maibudu.dto.PageModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 *
 * @author Pengyu Gan
 * CreateDate 2022/6/27
 */
public interface ShelfBookDao {

    int insert(ShelfBook record);

    List<BookDetails> searchBook(@Param("uid") String uid, @Param("title") String keyword, PageModel pageModel);

    BookDetails getBookDetails(@Param("uid") String uid, @Param("bookId") Long bookId);

    ShelfBook findByUidAndBookId(@Param("uid") String uid, @Param("bookId") Long bookId);

    int removeBook(@Param("uid") String uid, @Param("bookId") Long bookId);

    long countBook(@Param("uid") String uid);


}