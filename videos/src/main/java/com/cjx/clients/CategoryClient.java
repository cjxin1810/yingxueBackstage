package com.cjx.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("cloud-category")
public interface CategoryClient {
    @GetMapping("/category/getName")
    public String getNameById(@RequestParam(value = "id",required = false) Integer id);
    @GetMapping("/category/getcids")
    public List<Integer> getCids(@RequestParam(value = "categoryId",required = false) Integer categoryId);
}
