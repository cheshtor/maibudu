package com.mabushizai.maibudu.service;

import com.mabushizai.maibudu.config.MaibuduException;
import com.mabushizai.maibudu.constants.SysStatusEnum;
import com.mabushizai.maibudu.dao.ShelfBookDao;
import com.mabushizai.maibudu.domain.Book;
import com.mabushizai.maibudu.domain.ShelfBook;
import com.mabushizai.maibudu.domain.User;
import com.mabushizai.maibudu.dto.Page;
import com.mabushizai.maibudu.dto.PageModel;
import com.mabushizai.maibudu.utils.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Pengyu Gan
 * CreateDate 2022/6/27
 */
@Slf4j
@Service
public class ShelfBookService {

    @Resource
    private ShelfBookDao shelfBookDao;

    @Resource
    private UserService userService;

    @Transactional
    public boolean addBook(Long bookId) {
        String uid = UserContext.getUid();
        ShelfBook shelfBook = shelfBookDao.findByUidAndBookId(uid, bookId);
        if (null != shelfBook) {
            throw new MaibuduException("这本书已经添加过啦！");
        }
        shelfBook = new ShelfBook();
        shelfBook.setUid(uid);
        shelfBook.setBookId(bookId);
        shelfBook.setCreateDate(LocalDateTime.now());
        shelfBook.setSysStatus(SysStatusEnum.NORMAL.getValue());
        int rows = shelfBookDao.insert(shelfBook);
        return rows != 0;
    }

    public Page<Book> list(PageModel pageModel, String shareCode, String keyword) {
        String uid = UserContext.getUid();
        // 如果有共享码，则使用共享码查询到对应的 uid
        if (StringUtils.hasLength(shareCode)) {
            User user = userService.findByCode(shareCode);
            if (null == user) {
                throw new MaibuduException("无效的共享码");
            }
            uid = user.getUid();
        }
        List<Book> bookDetails = shelfBookDao.searchBook(uid, keyword, pageModel);
        return new Page<>(pageModel, bookDetails);
    }

    @Transactional
    public boolean removeBook(Long bookId) {
        String uid = UserContext.getUid();
        int rows = shelfBookDao.removeBook(uid, bookId);
        return rows != 0;
    }

    public long countBook() {
        String uid = UserContext.getUid();
        return shelfBookDao.countBook(uid);
    }


}
