package com.mabushizai.maibudu.service;

import com.mabushizai.maibudu.config.MaibuduException;
import com.mabushizai.maibudu.dao.BookDao;
import com.mabushizai.maibudu.dao.ShelfBookDao;
import com.mabushizai.maibudu.domain.Book;
import com.mabushizai.maibudu.domain.ShelfBook;
import com.mabushizai.maibudu.dto.BookSlimInfo;
import com.mabushizai.maibudu.dto.JikeBookInfo;
import com.mabushizai.maibudu.utils.HttpUtil;
import com.mabushizai.maibudu.utils.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author Pengyu Gan
 * CreateDate 2022/6/27
 */
@Slf4j
@Service
public class BookService {

    @Resource
    private SnowflakeIDGenerator idGenerator;

    @Resource
    private BookDao bookDao;

    @Resource
    private ShelfBookDao shelfBookDao;

    @Transactional
    public BookSlimInfo scanBook(String isbn) {
        BookSlimInfo slimInfo = new BookSlimInfo();
        String uid = UserContext.getUid();
        // 不重复入库
        Book book = bookDao.findByISBN(isbn);
        if (null != book) {
            ShelfBook shelfBook = shelfBookDao.findByUidAndBookId(uid, book.getId());
            slimInfo.extract(book, shelfBook.getReadStatus());
            return slimInfo;
        }
        // 调用 API 获取书籍信息
        JikeBookInfo bookInfo = HttpUtil.getJikeBookInfo(isbn);
        if (null == bookInfo) {
            throw new MaibuduException("查询书籍信息失败");
        }
        book = bookInfo.convert();
        book.setId(idGenerator.getId());
        book.setUid(uid);
        book.setCreateDate(LocalDateTime.now());
        // 入库
        int rows = bookDao.insert(book);
        if (rows <= 0) {
            throw new MaibuduException("书籍入库失败，请重试！");
        }
        slimInfo.extract(book, null);
        return slimInfo;
    }


}
