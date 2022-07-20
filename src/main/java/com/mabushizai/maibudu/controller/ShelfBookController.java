package com.mabushizai.maibudu.controller;

import com.mabushizai.maibudu.annotations.RequireRegister;
import com.mabushizai.maibudu.config.ApiResponse;
import com.mabushizai.maibudu.domain.BookCompleteInfo;
import com.mabushizai.maibudu.dto.BookVO;
import com.mabushizai.maibudu.dto.Page;
import com.mabushizai.maibudu.dto.PageModel;
import com.mabushizai.maibudu.service.ShelfBookService;
import com.mabushizai.maibudu.utils.AssertUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @RequireRegister(require = true)
    @GetMapping(value = "/listBook")
    public ApiResponse<Page<BookVO>> listBook(@RequestParam("pageNo") Long pageNo,
                                                        @RequestParam("pageSize") Integer pageSize,
                                                        @RequestParam(value = "shareCode", required = false) String shareCode,
                                                        @RequestParam(value = "keyword", required = false) String keyword) {
        PageModel pageModel = new PageModel(pageNo, pageSize);
        Page<BookCompleteInfo> page = shelfBookService.list(pageModel, shareCode, keyword);
        // 模型转换
        List<BookCompleteInfo> rows = page.getRows();
        List<BookVO> list = new ArrayList<>();
        for (BookCompleteInfo row : rows) {
            BookVO vo = new BookVO();
            BeanUtils.copyProperties(row, vo);
            list.add(vo);
        }
        PageModel model = new PageModel(page.getPageNo(), page.getPageSize());
        model.setTotalCount(page.getTotalCount());
        model.setTotalPages(page.getTotalPages());
        Page<BookVO> res = new Page<>(model, list);
        return ApiResponse.ok(res);
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
