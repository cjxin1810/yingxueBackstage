package com.cjx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cjx.entity.Video;

import java.util.Map;


/**
* @author chenxin
* @description 针对表【video(视频)】的数据库操作Service
* @createDate 2023-03-14 16:28:31
*/

public interface VideoService extends IService<Video> {
    Map<String, Object> pageUsers(Integer page, Integer perPage, Integer id, String name, Integer categoryId, String upName);

    int updateCategoryIdEqNull(Integer id);

}
