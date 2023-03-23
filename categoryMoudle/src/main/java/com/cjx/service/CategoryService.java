package com.cjx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cjx.entity.Category;
import com.cjx.vo.CategoryVo;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author chenxin
 * @description 针对表【category(分类)】的数据库操作Service
 * @createDate 2023-03-14 16:27:51
 */
public interface CategoryService extends IService<Category> {
    List<Category> getAll();

    String getCategoryName(Integer cid);

    int updateCategoryById(Category category, Integer id);

    void deleteCateGoryById(Integer id);
    Category insertCategory(Category category);

    List<Integer> getCids(Integer cid);
}
