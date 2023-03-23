package com.cjx.clients;

import com.cjx.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("cloud-user")
public interface UserClient {
    @GetMapping("/user/getUserByName")
    public List<User> searchName(@RequestParam(value = "name",required = false) String name);

    @GetMapping("/user/getUserById")
    public User selectUserById(@RequestParam(value = "id",required = false) Integer id);
}
