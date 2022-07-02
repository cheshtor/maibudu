package com.mabushizai.maibudu.controller;

import com.mabushizai.maibudu.annotations.RequireRegister;
import com.mabushizai.maibudu.config.ApiResponse;
import com.mabushizai.maibudu.domain.Book;
import com.mabushizai.maibudu.dto.Page;
import com.mabushizai.maibudu.dto.PageModel;
import com.mabushizai.maibudu.service.ShelfBookService;
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
@RequestMapping(value = "/api/shelf")
public class ShelfBookController {

    @Resource
    private ShelfBookService shelfBookService;

    @RequireRegister(require = true)
    @GetMapping(value = "/addBook")
    public ApiResponse<Boolean> addBook(@RequestParam("bookId") Long bookId) {
        AssertUtil.notNull(bookId, "书籍 ID 不能为空");
        boolean success = shelfBookService.addBook(bookId);
        return ApiResponse.ok(success);
    }

    @GetMapping(value = "/listBook")
    public ApiResponse<Page<Book>> listBook(@RequestParam("pageNo") Long pageNo,
                                            @RequestParam("pageSize") Integer pageSize,
                                            @RequestParam(value = "shareCode", required = false) String shareCode,
                                            @RequestParam(value = "keyword", required = false) String keyword) {
        PageModel pageModel = new PageModel(pageNo, pageSize);
        Page<Book> page = shelfBookService.list(pageModel, shareCode, keyword);
        return ApiResponse.ok(page);
    }

    @RequireRegister(require = true)
    @GetMapping(value = "/remove")
    public ApiResponse<Boolean> removeBook(@RequestParam("bookId") Long bookId) {
        AssertUtil.notNull(bookId, "书籍 ID 不能为空");
        boolean success = shelfBookService.removeBook(bookId);
        return ApiResponse.ok(success);
    }

    @GetMapping(value = "/count")
    public ApiResponse<Long> countBook() {
        long count = shelfBookService.countBook();
        return ApiResponse.ok(count);
    }



}
