package com.cjx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjx.clients.VideoClients;
import com.cjx.entity.Category;
import com.cjx.service.CategoryService;
import com.cjx.mapper.CategoryMapper;
import com.cjx.vo.CategoryVo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author chenxin
* @description 针对表【category(分类)】的数据库操作Service实现
* @createDate 2023-03-14 16:27:51
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{
    @Autowired
    private VideoClients videoClients;
    @Override
    public List<Category> getAll() {
        QueryWrapper<Category> qw = new QueryWrapper<>();
        qw.isNull("parent_id")
                .select("id", "name", "parent_id");
        List<Category> categories = baseMapper.selectList(qw);//parent_menu//一级类别
        for (Category category : categories) {
           /* QueryWrapper<Category> qh= new QueryWrapper<>();
            qh.eq("parent_id", category.getId())
                    .select("id", "name", "parent_id");
            List<Category> categories1 = baseMapper.selectList(qh);*/
            category.setChildren(getChildren(category.getId()));
        }
        return categories;
    }

    public List<Category> getChildren(Integer parentId) {
        QueryWrapper<Category> qh= new QueryWrapper<>();
        qh.eq("parent_id", parentId)
                .select("id", "name", "parent_id");
        List<Category> list = baseMapper.selectList(qh);
        if (!ObjectUtils.isEmpty(list)) {
            for (Category category : list) {
                category.setChildren(getChildren(category.getId()));
            }
        }
       return list;
    }

    @Override
    public String getCategoryName(Integer cid) {
        String s = baseMapper.categoryName(cid);
        return s;
    }

    @Override
    public int updateCategoryById(Category category, Integer id) {
       return baseMapper.update(category, new QueryWrapper<Category>().eq("id", id));

    }
    @Override
    public void deleteCateGoryById(Integer id) {
        /*QueryWrapper<Category> qw = new QueryWrapper<>();
        qw.eq(ObjectUtils.isEmpty("id"), "parent_id", id);
        List<Category> categories = baseMapper.selectList(qw);*/
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getParentId, id);
        List<Category> categories = baseMapper.selectList(queryWrapper);
        List<Integer> collect = categories.stream().map(c -> c.getId())
                .collect(Collectors.toList());
        //删除二级类别
//        int count=0;
        System.out.println(videoClients);
        for (Integer integer : collect) {
            int i = videoClients.updateCategoryIdEqual(integer);
//            count++;
        }
        if (ObjectUtils.isNotEmpty(collect)) {
            int i = baseMapper.deleteBatchIds(collect);
            if (i == 0) {
                throw new RuntimeException("二级类别删除失败");
            }
        }
        //删除一级类别
        int i1 = videoClients.updateCategoryIdEqual(id);
        if (i1 >= 0) {
            if (ObjectUtils.isNotEmpty(id)) {
                baseMapper.deleteById(id);
            }
        }
    }

    @Override
    public Category insertCategory(Category category) {
        category.setCreatedAt(new Date());
        int insert = baseMapper.insert(category);
        return category;
    }

    @Override
    public List<Integer> getCids(Integer cid) {
        List<Integer> collect=null;
        if (ObjectUtils.isNotEmpty(cid)) {
            Category category = baseMapper.selectById(cid);
            if (category != null) {
                LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(Category::getParentId, cid);
                List<Category> categories = baseMapper.selectList(queryWrapper);
                collect= categories.stream().map(c -> c.getId()).collect(Collectors.toList());
                collect.add(cid);
//            return collect;
            }
        }
        return collect;

    }
}




