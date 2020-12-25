package com.yujian.controller.admin.category;

import com.yujian.pojo.Category;
import com.yujian.pojo.User;
import com.yujian.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CategoryManagementController {
    @Autowired
    private CategoryService categoryService;
    /*页面跳转至 分类管理页面*/
    @GetMapping("/admin/categoryManagement")
    public String adminCategoryManagementPage(Model model){
        List<Category> categories = categoryService.queryAllCategory();
        model.addAttribute("categories", categories);
        System.out.println("查询所有用户，跳转至用户管理页面");
        return "admin/category/categoryManagement";
    }
    @GetMapping("/admin/category/page/add")
    public String adminAddCategoryPage(){
        return "admin/category/addCategory";
    }


    @GetMapping("/admin/category/page/update/{id}")
    public String adminUpdateCategoryPage(@PathVariable int id, Model model){
        Category category=categoryService.queryCategoryById(id);
        model.addAttribute("category", category);
        System.out.println(category);
        return "admin/category/updateCategory";
    }


    /*分类添加*/
    @ResponseBody
    @GetMapping("/admin/category/add")
    public Object add(@RequestParam String name,
                      @RequestParam String description){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name",name);
        map.put("description",description);
        System.out.println("执行SQL语句前");
        boolean result= categoryService.addCategory(map);
        System.out.println("执行SQL语句后");
        System.out.println(result);
        Map<String, Object> res = new HashMap<String, Object>();
        if (result){
            res.put("success",result);
            res.put("message","添加分类成功");
        }else{
            res.put("success",result);
            res.put("message","添加分类失败");
        }
        return res;
    }
    /*分类修改*/
    @ResponseBody
    @GetMapping("/admin/category/update")
    public Object update(@RequestParam String name,
                         @RequestParam String description, @RequestParam int id){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",id);
        map.put("name",name);
        map.put("description",description);
        System.out.println("执行SQL语句前");
        boolean result=categoryService.updateCategoryById(map);
        System.out.println("执行SQL语句后");
        System.out.println(result);
        Map<String, Object> res = new HashMap<String, Object>();
        if (result){
            res.put("success",result);
            res.put("message","分类修改成功");
        }else{
            res.put("success",result);
            res.put("message","分类修改成功");
        }
        return res;
    }
    /*删除分类*/
    @ResponseBody
    @GetMapping("/admin/category/delete/{id}")
    public Object adminDelete(@PathVariable int id){
        boolean result=categoryService.deleteCategoryById(id);
        Map<String, Object> res = new HashMap<String, Object>();
        if (result){
            res.put("success",result);
            res.put("message","删除分类成功");
        }else{
            res.put("success",result);
            res.put("message","删除分类失败");
        }
        return res;
    }
}
