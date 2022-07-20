package com.mabushizai.maibudu.controller;

import com.mabushizai.maibudu.annotations.RequireRegister;
import com.mabushizai.maibudu.config.ApiResponse;
import com.mabushizai.maibudu.domain.Book;
import com.mabushizai.maibudu.domain.BookCompleteInfo;
import com.mabushizai.maibudu.dto.BookVO;
import com.mabushizai.maibudu.service.BookService;
import com.mabushizai.maibudu.utils.AssertUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Pengyu Gan
 * CreateDate 2022/6/27
 */
@RestController
@RequestMapping(value = "/api/book")
public class BookController {

    @Resource
    private BookService bookService;

    @RequireRegister(require = true)
    @GetMapping(value = "/scan")
    public ApiResponse<BookVO> scanBook(@RequestParam("isbn") String isbn) {
        AssertUtil.notEmpty(isbn, "书籍 ISBN 不能为空");
        Book book = bookService.scanBook(isbn);
        BookVO vo = new BookVO();
        vo.setId(book.getId());
        vo.setTitle(book.getTitle());
        vo.setSubtitle(book.getSubtitle());
        vo.setAuthor(book.getAuthor());
        vo.setPublisher(book.getPublisher());
        vo.setIsbn(book.getIsbn());
        return ApiResponse.ok(vo);
    }

    @RequireRegister(require = true)
    @GetMapping(value = "/get")
    public ApiResponse<BookVO> getBook(@RequestParam("bookId") Long bookId) {
        AssertUtil.notNull(bookId, "书籍 ID 不能为空");
        BookCompleteInfo info = bookService.findById(bookId);
        BookVO vo = new BookVO();
        BeanUtils.copyProperties(info, vo);
        return ApiResponse.ok(vo);
    }

//    @GetMapping(value = "/getSimple")
//    public ApiResponse<BookVO> getSlimBookInfo(@RequestParam("bookId") Long bookId) {
//        AssertUtil.notNull(bookId, "书籍 ID 不能为空");
//        BookCompleteInfo info = bookService.findById(bookId);
//        BookSlimInfo slimInfo = new BookSlimInfo();
//        slimInfo.doSlim(book);
//        return ApiResponse.ok(slimInfo);
//    }

}
