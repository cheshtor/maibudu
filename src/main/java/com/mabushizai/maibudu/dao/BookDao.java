package com.mabushizai.maibudu.dao;

import com.mabushizai.maibudu.domain.Book;
import com.mabushizai.maibudu.domain.BookCompleteInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 
 *
 * @author Pengyu Gan
 * CreateDate 2022/6/27
 */
public interface BookDao {

    Book findByISBN(@Param("isbn") String isbn);

    int insert(Book record);

    Book selectByPrimaryKey(Long id);

    BookCompleteInfo findByBookId(Long id);
}