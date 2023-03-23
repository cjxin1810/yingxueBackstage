package com.cjx.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("cloud-video")
public interface VideoClients {
    @DeleteMapping("/video/updateCategoryIdEqNull/{id}")
    public int updateCategoryIdEqual(@PathVariable(value = "id",required = false) Integer id);

}
