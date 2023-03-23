package com.cjx;

import com.cjx.entity.Category;
import com.cjx.service.CategoryService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;
import java.util.Date;

@SpringBootTest(classes = CategoryApplication.class)
@RunWith(SpringRunner.class)
public class Test {
    @Autowired
    private CategoryService categoryService;

    @org.junit.Test
    public void sdsa() {
        /*Category category = new Category();
        category.setId(2);
        category.setName("zhangsan");
        Category category1 = categoryService.updateCategoryById(category,category.getId());
        System.out.println(category1);*/
        System.out.println(4 / 8.0 + 4 / 7);
    }
}
