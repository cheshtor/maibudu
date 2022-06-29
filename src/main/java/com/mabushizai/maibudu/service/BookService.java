package com.mabushizai.maibudu.service;

import com.mabushizai.maibudu.config.MaibuduException;
import com.mabushizai.maibudu.constants.SysStatusEnum;
import com.mabushizai.maibudu.dao.BookDao;
import com.mabushizai.maibudu.domain.Book;
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
    private UserService userService;

    @Transactional
    public BookSlimInfo scanBook(String isbn) {
        BookSlimInfo slimInfo = new BookSlimInfo();
        String uid = UserContext.getUid();
        // 不重复入库
        Book book = bookDao.findByISBN(isbn);
        if (null != book) {
            // 判断书籍状态，被禁用的书籍信息不允许使用、也不允许添加
            if (book.getSysStatus().equals(SysStatusEnum.NORMAL.getValue())) {
                slimInfo.extract(book);
                return slimInfo;
            }
            throw new MaibuduException("暂不支持此书籍入库");
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
        // 新用户入库
        String code = UserContext.getCode();
        if (null == code) {
            userService.addUser();
        }
        slimInfo.extract(book);
        return slimInfo;
    }

    public Book findById(Long bookId) {
        return bookDao.selectByPrimaryKey(bookId);
    }


}
