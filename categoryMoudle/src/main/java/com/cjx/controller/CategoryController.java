package com.cjx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cjx.entity.Category;

import com.cjx.exception.IllegalPasswordException;
import com.cjx.service.CategoryService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> getAll() {
        return categoryService.getAll();
    }
    @GetMapping("/getName")
    public String getNameById(@RequestParam(value = "id", required = false) Integer id) {
        String categoryName = categoryService.getCategoryName(id);
        return categoryName;
    }
    @PatchMapping("/categories/{id}")
    public Map<String, String> updateCategory(@PathVariable(value = "id", required = false) Integer id,
                                              @RequestBody Category category
    ) {
        if (category.getParentId() == id) {
            throw new IllegalPasswordException("parent_id不能修改成自身id");
        }
        category.setId(id);
        categoryService.updateById(category);
        /* int i = categoryService.updateCategoryById(category, id);*/
        //System.out.println(category);
        category = categoryService.getOne(new QueryWrapper<Category>().eq("id", id));
        //System.out.println(category);
        Map<String, String> map = new HashMap<>();
        map.put("id", category.getId().toString());
        map.put("name", category.getName());
        if (ObjectUtils.isNotEmpty(category.getParentId())) {
            map.put("parent_id", category.getParentId().toString());
        }
        return map;
    }

    @DeleteMapping("/categories/{id}")
    public void deleteById(@PathVariable(value = "id", required = false) Integer id) {
        //先删除video中的category_id
        //在删除1/2目录
        categoryService.deleteCateGoryById(id);
    }

    @PostMapping("/categories")
    public Map<String, String> insertCategory(@RequestBody Category category) {
        category = categoryService.insertCategory(category);
        Map<String, String> map = new HashMap<>();
        map.put("id", category.getId().toString());
        map.put("name", category.getName());
        System.err.println(category);
        if (!ObjectUtils.isEmpty(category.getParentId())) {
            map.put("parent_id", category.getParentId().toString());
        }
        return map;
    }
    @GetMapping("/getcids")
    public List<Integer> getCids(@RequestParam(value = "categoryId",required = false)Integer categoryId) {
        List<Integer> cids = categoryService.getCids(categoryId);
        return cids;
    }
}
