package com.cjx.controller;

import com.cjx.entity.Video;
import com.cjx.mapper.VideoMapper;
import com.cjx.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @GetMapping("/videos")
    public Map<String, Object> pageVideos(
             @RequestParam(value = "page", defaultValue = "1",required = false) Integer page
            , @RequestParam(value = "per_page", defaultValue = "1",required = false) Integer per_page
            , @RequestParam(value = "id" ,required = false) Integer id
            , @RequestParam(value = "name",required = false) String name,
             @RequestParam(value = "category_id",required = false) Integer categoryId,
             @RequestParam(value = "uploader_name",required = false) String uploader_name
    ) {
        Map<String, Object> map = videoService.pageUsers(page, per_page, id, name, categoryId, uploader_name);
        return map;

    }

    @DeleteMapping("/updateCategoryIdEqNull/{id}")
    public int updateCategoryIdEqual(@PathVariable(value = "id",required = false) Integer id) {
        return  videoService.updateCategoryIdEqNull(id);
    }
}
