package com.mabushizai.maibudu.controller;

import com.mabushizai.maibudu.config.ApiResponse;
import com.mabushizai.maibudu.domain.BookDetails;
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
@RequestMapping(value = "/api/shelfbook")
public class ShelfBookController {

    @Resource
    private ShelfBookService shelfBookService;

    @GetMapping(value = "/addBook")
    public ApiResponse<Boolean> addBook(@RequestParam("bookId") Long bookId,
                                        @RequestParam("readStatus") Byte readStatus) {
        AssertUtil.notNull(bookId, "书籍 ID 不能为空");
        AssertUtil.notNull(readStatus, "书架阅读状态不能为空");
        boolean success = shelfBookService.addBook(bookId, readStatus);
        return ApiResponse.ok(success);
    }

    @GetMapping(value = "/listBook")
    public ApiResponse<Page<BookDetails>> listBook(@RequestParam("pageNo") Long pageNo,
                                                    @RequestParam("pageSize") Integer pageSize,
                                                    @RequestParam("keyword") String keyword) {
        PageModel pageModel = new PageModel(pageNo, pageSize);
        Page<BookDetails> page = shelfBookService.list(pageModel, keyword);
        return ApiResponse.ok(page);
    }

    @GetMapping(value = "/get")
    public ApiResponse<BookDetails> getBook(@RequestParam("bookId") Long bookId) {
        AssertUtil.notNull(bookId, "书籍 ID 不能为空");
        BookDetails bookDetails = shelfBookService.getBookDetails(bookId);
        return ApiResponse.ok(bookDetails);
    }

}
