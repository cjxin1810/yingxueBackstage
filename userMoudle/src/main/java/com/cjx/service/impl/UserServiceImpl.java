package com.cjx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjx.constants.RedisPrefix;
import com.cjx.entity.Admin;
import com.cjx.entity.User;
import com.cjx.service.UserService;
import com.cjx.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author chenxin
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2023-03-14 08:24:25
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public Map<String,Object> pageUsers(Integer page, Integer perPage, Integer id, String name, String phone) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(User::getId, User::getName, User::getAvatar, User::getIntro,
                        User::getPhone, User::getPhoneLinked, User::getOpenid, User::getWechatLinked)
                .eq(!ObjectUtils.isEmpty(id), User::getId, id)
                .like(!StringUtils.isEmpty(name), User::getName, name)
                .like(!StringUtils.isEmpty(phone), User::getPhone, phone);
        PageHelper.startPage(page, perPage);
        List<User> list = baseMapper.selectList(queryWrapper);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        Map<String, Object> map = new HashMap<>();
        map.put("totalcount", pageInfo.getTotal());
        map.put("items", pageInfo.getList());
        return map;
    }

    @Override
    public List<User> selectUserByName(String name) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.like(!StringUtils.isEmpty(name),"name", name);
        return  baseMapper.selectList(qw);
    }

    @Override
    public User selectUserById(Integer id) {
      return   baseMapper.selectById(id);
    }
}




