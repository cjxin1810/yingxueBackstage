package com.cjx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cjx.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author chenxin
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2023-03-14 08:24:25
* @Entity com.cjx.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




