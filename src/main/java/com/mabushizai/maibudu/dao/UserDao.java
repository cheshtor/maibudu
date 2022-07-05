package com.mabushizai.maibudu.dao;

import com.mabushizai.maibudu.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 *
 * @author Pengyu Gan
 * CreateDate 2022/6/29
 */
public interface UserDao {
    int insert(User record);

    User selectByPrimaryKey(String uid);

    int countByCode(@Param("code") String code);

    User findByCode(@Param("code") String code);

    List<User> findByUidList(@Param("ids") List<String> uidList);
}