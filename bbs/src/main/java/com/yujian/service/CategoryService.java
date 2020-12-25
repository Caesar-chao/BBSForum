package com.yujian.service;

import com.yujian.pojo.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    List<Category> queryAllCategory();
    /*==============后台===============*/
    /*添加分类*/
    boolean addCategory(Map<String,Object> map);
    /*修改分类*/
    boolean updateCategoryById(Map<String,Object> map);
    /*删除分类*/
    boolean deleteCategoryById(int id);
    /*根据id查询分类*/
    Category queryCategoryById(int id);
}
