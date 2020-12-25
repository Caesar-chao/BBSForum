package com.yujian.service.Impl;

import com.yujian.mapper.CategoryMapper;
import com.yujian.pojo.Category;
import com.yujian.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    /*查询所有分类*/
    @Override
    public List<Category> queryAllCategory() {
        return categoryMapper.queryAllCategory();
    }

    @Override
    public boolean addCategory(Map<String, Object> map) {
        return categoryMapper.addCategory(map);
    }

    @Override
    public boolean updateCategoryById(Map<String, Object> map) {
        return categoryMapper.updateCategoryById(map);
    }

    @Override
    public boolean deleteCategoryById(int id) {
        return categoryMapper.deleteCategoryById(id);
    }

    @Override
    public Category queryCategoryById(int id) {
        return categoryMapper.queryCategoryById(id);
    }
}
