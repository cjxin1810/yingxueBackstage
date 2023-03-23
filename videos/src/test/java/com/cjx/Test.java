package com.cjx;

import com.cjx.clients.UserClient;
import com.cjx.entity.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = VideoApplication.class)
@RunWith(SpringRunner.class)
public class Test {
    @Autowired
    private UserClient userClient;

    @org.junit.Test
    public void test() {
        User user = userClient.selectUserById(1);
        System.out.println(user);
    }
}
