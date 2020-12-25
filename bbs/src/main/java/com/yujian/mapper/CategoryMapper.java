package com.yujian.mapper;

import com.yujian.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/*注解Mapper：表示这是一个mybatis的mapper类*/
@Mapper
@Repository
public interface CategoryMapper {
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
