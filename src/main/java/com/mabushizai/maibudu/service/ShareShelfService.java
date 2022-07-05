package com.mabushizai.maibudu.service;

import com.mabushizai.maibudu.dao.ShareShelfDao;
import com.mabushizai.maibudu.domain.ShareShelf;
import com.mabushizai.maibudu.domain.User;
import com.mabushizai.maibudu.dto.Page;
import com.mabushizai.maibudu.dto.PageModel;
import com.mabushizai.maibudu.dto.ShareShelfInfo;
import com.mabushizai.maibudu.utils.AssertUtil;
import com.mabushizai.maibudu.utils.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Pengyu Gan
 * CreateDate 2022/6/29
 */
@Slf4j
@Service
public class ShareShelfService {

    @Resource
    private ShareShelfDao shareShelfDao;

    @Resource
    private UserService userService;

    @Transactional
    public boolean importShare(String shareCode) {
        User exporter = userService.findByCode(shareCode);
        AssertUtil.notNull(exporter, "共享码不存在");
        ShareShelf shareShelf = shareShelfDao.selectByPrimaryKey(UserContext.getUid(), exporter.getUid());
        AssertUtil.isTrue(null == shareShelf, "已经共享过 TA 的书籍啦");
        shareShelf = new ShareShelf();
        shareShelf.setImporterId(UserContext.getUid());
        shareShelf.setExporterId(exporter.getUid());
        shareShelf.setCreateDate(LocalDateTime.now());
        int rows = shareShelfDao.insert(shareShelf);
        return rows != 0;
    }

    @Transactional
    public boolean removeShare(String shareCode) {
        User exporter = userService.findByCode(shareCode);
        AssertUtil.notNull(exporter, "共享码不存在");
        String importerId = UserContext.getUid();
        ShareShelf shareShelf = shareShelfDao.selectByPrimaryKey(importerId, exporter.getUid());
        AssertUtil.notNull(shareShelf, "对方没有向你共享过内容");
        int rows = shareShelfDao.removeShare(importerId, exporter.getUid());
        return rows != 0;
    }

    public Page<ShareShelfInfo> list(PageModel pageModel) {
        List<ShareShelfInfo> shareInfos = new ArrayList<>();
        String importerId = UserContext.getUid();
        List<ShareShelf> shares = shareShelfDao.listShares(importerId, pageModel);
        if (null != shares && !shares.isEmpty()) {
            List<String> uidList = shares.stream().map(ShareShelf::getExporterId).collect(Collectors.toList());
            List<User> users = userService.findByUidList(uidList);
            userLoop:
            for (User user : users) {
                for (ShareShelf share : shares) {
                    if (user.getUid().equals(share.getExporterId())) {
                        ShareShelfInfo info = new ShareShelfInfo();
                        info.setImporterId(share.getImporterId());
                        info.setExporterId(share.getExporterId());
                        info.setCreateDate(share.getCreateDate());
                        info.setCode(user.getCode());
                        info.setAvatar(user.getAvatar());
                        info.setNickname(user.getNickname());
                        shareInfos.add(info);
                        continue userLoop;
                    }
                }
            }
        }
        return new Page<>(pageModel, shareInfos);
    }


}
