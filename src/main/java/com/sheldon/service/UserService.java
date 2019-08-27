package com.sheldon.service;

import com.sheldon.dao.UserDao;
import com.sheldon.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 业务层
 * Created by Sheldon on 2019/8/26.
 * Project Name: sso.
 * Package Name: com.sheldon.service.
 */

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    RedisTemplate redisTemplate;

    // 登录验证数据
    public User login(String id, String passwd){
        return userDao.getUser(id, passwd);
    }

    // 存储 ticket 到缓存中
    public boolean addTicket(String ticket, User user){
        // 设置缓存数据类型
        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
        // 存储数据并设置缓存过期时间
        valueOperations.set(ticket, user, 60, TimeUnit.MINUTES);
        // 检测数据是否已经写入内存
        if (redisTemplate.hasKey(ticket)){
            return true;
        }else {
            return false;
        }
    }

    // redis中获取数据
    public User getUserByTicket(String ticket){
        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
        // 检测数据是否存在
        if (redisTemplate.hasKey(ticket)){
            // 存在则返回数据
            User user = valueOperations.get(ticket);
            return user;
        }else {
            return null;
        }
    }


    // 删除 redis 中对应数据
    public boolean delTicket(String ticket){
        return redisTemplate.delete(ticket);
    }

}
