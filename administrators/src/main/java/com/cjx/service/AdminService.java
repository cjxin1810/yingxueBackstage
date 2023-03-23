package com.cjx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cjx.entity.Admin;

/**
* @author chenxin
* @description 针对表【admin】的数据库操作Service
* @createDate 2023-03-14 16:27:33
*/
public interface AdminService extends IService<Admin> {
    Admin login(Admin admin);

}
