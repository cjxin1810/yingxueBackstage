package com.cjx.controller;

import com.cjx.entity.User;
import com.cjx.mapper.UserMapper;
import com.cjx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Map<String, Object> PageUsers(
             @RequestParam(value = "page", defaultValue = "1",required = false) Integer page
            , @RequestParam(value = "per_page", defaultValue = "1",required = false) Integer per_page
            , @RequestParam(value = "id",required = false) Integer id
            , @RequestParam(value = "name",required = false) String name,
             @RequestParam(value = "phone",required = false) String phone
    ) {

        return userService.pageUsers( page, per_page, id, name, phone);

    }

    @GetMapping("/getUserByName")
    public List<User> searchName(@RequestParam(value = "name",required = false) String name) {
        return userService.selectUserByName(name);
    }

    @GetMapping("/getUserById")
    public User selectUserById(@RequestParam(value = "id",required = false) Integer id) {
        User user = userService.selectUserById(id);
        return user;
    }
}
