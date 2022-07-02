package com.mabushizai.maibudu.service;

import com.mabushizai.maibudu.config.MaibuduException;
import com.mabushizai.maibudu.constants.SysStatusEnum;
import com.mabushizai.maibudu.dao.UserDao;
import com.mabushizai.maibudu.domain.User;
import com.mabushizai.maibudu.dto.UserRegisterRequest;
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
    public User addUser(UserRegisterRequest request) {
        String uid = UserContext.getUid();
        User user = userDao.selectByPrimaryKey(uid);
        AssertUtil.isTrue(user == null, "您已经注册过啦~");
        user = new User();
        user.setUid(uid);
        String code = this.getCode();
        user.setCode(code);
        user.setNickname(request.getNickname());
        user.setAvatar(request.getAvatar());
        user.setCreateDate(LocalDateTime.now());
        user.setSysStatus(SysStatusEnum.NORMAL.getValue());
        int rows = userDao.insert(user);
        if (rows != 0) {
            return user;
        }
        throw new MaibuduException("新用户注册失败");
    }

    public User findByUid() {
        String uid = UserContext.getUid();
        return userDao.selectByPrimaryKey(uid);
    }

    public User findByCode(String code) {
        AssertUtil.notEmpty(code, "共享码不能为空");
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
