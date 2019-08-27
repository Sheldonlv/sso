package com.sheldon.dao;

import com.sheldon.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * 用户信息持久层
 * Created by Sheldon on 2019/8/26.
 * Project Name: sso.
 * Package Name: com.sheldon.dao.
 */
public interface UserDao {

    public User getUser(@Param("id")String id, @Param("passwd")String passwd);
}
