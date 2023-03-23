package com.cjx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjx.entity.Admin;
import com.cjx.exception.IllegalPasswordException;
import com.cjx.mapper.AdminMapper;
import com.cjx.service.AdminService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
* @author chenxin
* @description 针对表【admin】的数据库操作Service实现
* @createDate 2023-03-14 16:27:33
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{
    @Override
    public Admin login(Admin admin) {
        Admin admin1 = baseMapper.selectOne(new QueryWrapper<Admin>().eq("username", admin.getUsername()));
        if (ObjectUtils.isEmpty(admin)) {
            throw new IllegalPasswordException("用户名输入错误");
        }
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", admin.getUsername())
                .eq("password", DigestUtils.md5DigestAsHex(admin.getPassword().getBytes(StandardCharsets.UTF_8)));
        return baseMapper.selectOne(queryWrapper);
    }
}




