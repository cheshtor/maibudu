package com.mabushizai.maibudu.controller;

import com.mabushizai.maibudu.config.ApiResponse;
import com.mabushizai.maibudu.domain.Book;
import com.mabushizai.maibudu.dto.BookSlimInfo;
import com.mabushizai.maibudu.service.BookService;
import com.mabushizai.maibudu.utils.AssertUtil;
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

    @GetMapping(value = "/scan")
    public ApiResponse<BookSlimInfo> scanBook(@RequestParam("isbn") String isbn) {
        AssertUtil.notEmpty(isbn, "书籍 ISBN 不能为空");
        AssertUtil.isTrue(isbn.length() == 10 || isbn.length() == 13, "仅支持 10 位和 13 位 ISBN");
        BookSlimInfo slimInfo = bookService.scanBook(isbn);
        return ApiResponse.ok(slimInfo);
    }

    @GetMapping(value = "/get")
    public ApiResponse<Book> getBook(@RequestParam("bookId") Long bookId) {
        AssertUtil.notNull(bookId, "书籍 ID 不能为空");
        Book book = bookService.findById(bookId);
        return ApiResponse.ok(book);
    }

    @GetMapping(value = "/getSlim")
    public ApiResponse<BookSlimInfo> getSlimBookInfo(@RequestParam("bookId") Long bookId) {
        AssertUtil.notNull(bookId, "书籍 ID 不能为空");
        Book book = bookService.findById(bookId);
        BookSlimInfo slimInfo = new BookSlimInfo();
        slimInfo.doSlim(book);
        return ApiResponse.ok(slimInfo);
    }

}
