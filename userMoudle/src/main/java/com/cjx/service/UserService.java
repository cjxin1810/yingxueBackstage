package com.cjx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cjx.entity.User;

import java.util.List;
import java.util.Map;

/**
* @author chenxin
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2023-03-14 08:24:25
*/
public interface UserService extends IService<User> {
    Map<String,Object> pageUsers(Integer page, Integer perPage, Integer id, String name, String phone);

    List<User> selectUserByName(String name);

    User selectUserById(Integer id);
}
