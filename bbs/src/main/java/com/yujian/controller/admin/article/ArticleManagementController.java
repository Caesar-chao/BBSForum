package com.yujian.controller.admin.article;

import com.yujian.dto.ArticleInfo;
import com.yujian.pojo.Category;
import com.yujian.service.ArticleService;
import com.yujian.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ArticleManagementController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    /*页面跳转至 文章管理页面*/
    @GetMapping("/admin/articleManagement")
    public String adminArticleManagementPage(Model model) {
        List<ArticleInfo> articleInfos = articleService.queryAllArticleInfoById();
        model.addAttribute("articleInfos", articleInfos);
        System.out.println("查询按照id排序的所有文章，并跳转至文章管理页面");
        return "admin/article/articleManagement";
    }

    @GetMapping("/admin/article/page/add")
    public String adminAddCategoryPage(Model model) {
        List<Category> categories = categoryService.queryAllCategory();
        model.addAttribute("categories", categories);
        System.out.println("查询所有分类，并跳转至文章新增页面");
        return "admin/article/addArticle";

    }

    /*文章添加*/
    @ResponseBody
    @GetMapping("/admin/article/add")
    public Object add(@RequestParam String title,
                      @RequestParam String summary,
                      @RequestParam String content,
                      @RequestParam String isTop,
                      @RequestParam String refinement,
                      @RequestParam String grade,
                      @RequestParam String category_id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", title);
        map.put("summary", summary);
        map.put("content", content);
        map.put("create_time", new Date());
        map.put("isTop", isTop);
        map.put("refinement", refinement);
        map.put("grade", grade);
        map.put("user_id", 1);
        map.put("category_id", category_id);
        System.out.println("执行SQL语句前");
        boolean result = articleService.addArticle(map);
        System.out.println("执行SQL语句后");
        System.out.println(result);
        Map<String, Object> res = new HashMap<String, Object>();
        if (result) {
            res.put("success", result);
            res.put("message", "添加分类成功");
        } else {
            res.put("success", result);
            res.put("message", "添加分类失败");
        }
        return res;
    }

    /*页面跳转 至 文章编辑页面*/
    @GetMapping("/admin/article/page/update/{id}")
    public String adminUpdateCategoryPage(@PathVariable int id, Model model) {
        ArticleInfo articleInfo = articleService.queryArticleInfoById(id);
        model.addAttribute("articleInfo", articleInfo);
        System.out.println("通过id查询文章，用户，分类等信息，并跳转至文章修改页面");
        List<Category> categories = categoryService.queryAllCategory();
        model.addAttribute("categories", categories);
        System.out.println("查询所有分类，并跳转至文章修改页面");
        return "admin/article/updateArticle";
    }

    /*文章修改*/
    @ResponseBody
    @GetMapping("/admin/article/update")
    public Object update(@RequestParam String id,
                         @RequestParam String title,
                         @RequestParam String summary,
                         @RequestParam String content,
                         @RequestParam String isTop,
                         @RequestParam String refinement,
                         @RequestParam String grade,
                         @RequestParam int category_id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("title", title);
        map.put("summary", summary);
        map.put("content", content);
        map.put("create_time", new Date());
        map.put("isTop", isTop);
        map.put("refinement", refinement);
        map.put("grade", grade);
        map.put("category_id", category_id);
        System.out.println("执行SQL语句前");
        boolean result = articleService.updateArticle(map);
        System.out.println("执行SQL语句后");
        System.out.println(result);
        Map<String, Object> res = new HashMap<String, Object>();
        if (result) {
            res.put("success", result);
            res.put("message", "文章修改成功");
        } else {
            res.put("success", result);
            res.put("message", "文章修改失败");
        }
        return res;
    }
      /*删除文章*/
    @ResponseBody
    @GetMapping("/admin/article/delete/{id}")
    public Object adminDelete(@PathVariable int id){
        boolean result=articleService.deleteArticleById(id);
        Map<String, Object> res = new HashMap<String, Object>();
        if (result){
            res.put("success",result);
            res.put("message","文章删除成功");
        }else{
            res.put("success",result);
            res.put("message","文章删除失败");
        }
        return res;
    }

}

