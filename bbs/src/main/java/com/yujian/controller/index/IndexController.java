package com.yujian.controller.index;

import com.yujian.dto.ArticleInfo;
import com.yujian.pojo.Article;
import com.yujian.pojo.Category;
import com.yujian.pojo.User;
import com.yujian.service.ArticleService;
import com.yujian.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    /*页面跳转*/
    @RequestMapping("/index")
    public String index(Model model){
        List<ArticleInfo> articleInfos= articleService.queryAllArticleInfoByTime();
        model.addAttribute("articleInfos", articleInfos);
        System.out.println("查询所有文章");
        List<ArticleInfo> articlesIsTop= articleService.queryArticleByIsTop();
        model.addAttribute("articlesIsTop", articlesIsTop);
        System.out.println("查询置顶文章");

        List<Category> categories=categoryService.queryAllCategory();
        model.addAttribute("categories", categories);
        System.out.println("查询所有分类");

        List<Article> articles=articleService.queryArticleByCommentNum();
        model.addAttribute("articles", articles);
        System.out.println("查询评论数最多的十篇文章");
        /*页面跳转至首页*/
        return "index/index";
    }
    /*VIP/svip*/
    @GetMapping("/status/{id}")
    public String queryArticleByStatus(@PathVariable int id, Model model){
        List<Category> categories=categoryService.queryAllCategory();
        model.addAttribute("categories", categories);
        System.out.println(categories);
        List<ArticleInfo> articleInfos= articleService.queryArticleByStatus(id);
        System.out.println("根据分类id查询文章信息");
        System.out.println(articleInfos);
        model.addAttribute("articleInfos", articleInfos);
        return "index/article/index";

    }
}
