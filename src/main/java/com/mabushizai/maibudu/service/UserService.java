package com.mabushizai.maibudu.service;

import com.mabushizai.maibudu.constants.SysStatusEnum;
import com.mabushizai.maibudu.dao.UserDao;
import com.mabushizai.maibudu.domain.User;
import com.mabushizai.maibudu.utils.AssertUtil;
import com.mabushizai.maibudu.utils.StringUtil;
import com.mabushizai.maibudu.utils.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author Pengyu Gan
 * CreateDate 2022/6/29
 */
@Slf4j
@Service
public class UserService {

    @Resource
    private UserDao userDao;

    @Transactional
    public boolean addUser() {
        User user = new User();
        String uid = UserContext.getUid();
        user.setUid(uid);
        String code = this.getCode();
        user.setCode(code);
        user.setCreateDate(LocalDateTime.now());
        user.setSysStatus(SysStatusEnum.NORMAL.getValue());
        int rows = userDao.insert(user);
        return rows != 0;
    }

    public User findByUid() {
        String uid = UserContext.getUid();
        return userDao.selectByPrimaryKey(uid);
    }

    public User findByCode(String code) {
        AssertUtil.notEmpty(code, "用户书架共享码不能为空");
        return userDao.findByCode(code);
    }

    private String getCode() {
        int rows;
        String code;
        do {
            code = StringUtil.generateCode();
            rows = userDao.countByCode(code);
        } while (rows != 0);
        return code;
    }

}
