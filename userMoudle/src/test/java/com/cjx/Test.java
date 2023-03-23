package com.cjx;


import com.cjx.entity.User;
import com.cjx.mapper.UserMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
@SpringBootTest(classes = UserApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class Test {
@Autowired
private UserMapper userMapper;
    @org.junit.Test
    public void test() {
        List<User> list = userMapper.selectList(null);
        list.forEach(System.err::println);
    }
}
