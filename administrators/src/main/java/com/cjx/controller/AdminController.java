package com.cjx.controller;

import com.cjx.constants.RedisPrefix;
import com.cjx.entity.Admin;
import com.cjx.exception.IllegalPasswordException;
import com.cjx.service.AdminService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/admin")
@RefreshScope
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @PostMapping("/tokens")
    public Map<String, String> tokens(@RequestBody Admin admin, HttpSession session) {
        Map<String, String> result = new HashMap<>();
        admin= adminService.login(admin);
        if (admin == null) {
            throw new IllegalPasswordException("密码输入错误");
        }
        String token = session.getId();
        //将sessionid作为token
        ValueOperations<String, Object> redis = redisTemplate.opsForValue();
        redis.set(RedisPrefix.TOKEN_KEY+token,admin,30, TimeUnit.MINUTES);
        result.put("token", token);
        return result;
    }
    @GetMapping("/admin-user")
    public HashMap<String,String> adminInfo(@RequestParam("token") String token) {
        Admin admin = (Admin) redisTemplate.opsForValue().get(RedisPrefix.TOKEN_KEY+token);
        if (ObjectUtils.isEmpty(admin)) {
            throw new IllegalPasswordException("token失效或者无效");
        }
       return new HashMap<String,String>(){{
            put("name", admin.getUsername());
            put("avatar", admin.getAvatar());
        }};
    }
    @DeleteMapping("/tokens/{token}")
    public void logout(@PathVariable("token") String token){
        Boolean delete = redisTemplate.delete(RedisPrefix.TOKEN_KEY + token);
        System.out.println(delete);
        if (!delete) {
            throw new IllegalPasswordException("登出失败");
        }
    }
}
