package com.yujian.controller.index.category;

import com.yujian.dto.ArticleInfo;
import com.yujian.pojo.Category;
import com.yujian.service.ArticleService;
import com.yujian.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CategoeyController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ArticleService articleService;
    @GetMapping("/category/{id}")
    public String category(@PathVariable int id, Model model){
        List<Category> categories=categoryService.queryAllCategory();
        model.addAttribute("categories", categories);
        System.out.println(categories);
        List<ArticleInfo> articleInfos= articleService.queryArticleByCategory(id);
        System.out.println("根据分类id查询文章信息");
        System.out.println(articleInfos);
        model.addAttribute("articleInfos", articleInfos);
        return "index/article/index";

    }
}
