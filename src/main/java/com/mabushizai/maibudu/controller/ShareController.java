package com.mabushizai.maibudu.controller;

import com.mabushizai.maibudu.annotations.RequireRegister;
import com.mabushizai.maibudu.config.ApiResponse;
import com.mabushizai.maibudu.domain.ShareShelf;
import com.mabushizai.maibudu.dto.Page;
import com.mabushizai.maibudu.dto.PageModel;
import com.mabushizai.maibudu.service.ShareShelfService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Pengyu Gan
 * CreateDate 2022/6/29
 */
@RestController
@RequestMapping(value = "/api/share")
public class ShareController {

    @Resource
    private ShareShelfService shareShelfService;

    @RequireRegister(require = true)
    @GetMapping(value = "/import")
    public ApiResponse<Boolean> importShare(@RequestParam("shareCode") String shareCode) {
        boolean success = shareShelfService.importShare(shareCode);
        return ApiResponse.ok(success);
    }

    @RequireRegister(require = true)
    @GetMapping(value = "/remove")
    public ApiResponse<Boolean> removeShare(@RequestParam("shareCode") String shareCode) {
        boolean success = shareShelfService.removeShare(shareCode);
        return ApiResponse.ok(success);
    }

    @RequireRegister(require = true)
    @GetMapping(value = "/list")
    public ApiResponse<Page<ShareShelf>> listShares(@RequestParam("pageNo") Long pageNo,
                                                    @RequestParam("pageSize") Integer pageSize) {
        PageModel pageModel = new PageModel(pageNo, pageSize);
        Page<ShareShelf> list = shareShelfService.list(pageModel);
        return ApiResponse.ok(list);
    }


}
