package com.cjx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cjx.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author chenxin
* @description 针对表【category(分类)】的数据库操作Mapper
* @createDate 2023-03-14 16:27:51
* @Entity com.cjx.entity.Category
*/
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    /*  List<Category> selectCategorys();*/
    String categoryName(Integer cid);
}




