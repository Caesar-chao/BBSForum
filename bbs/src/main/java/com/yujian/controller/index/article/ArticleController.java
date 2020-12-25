package com.yujian.controller.index.article;

import com.yujian.dto.ArticleInfo;
import com.yujian.dto.CommentInfo;
import com.yujian.pojo.Category;
import com.yujian.pojo.User;
import com.yujian.service.ArticleService;
import com.yujian.service.CategoryService;
import com.yujian.service.CommentService;
import com.yujian.service.UserService;
import com.yujian.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    /*页面跳转 发布帖子的页面*/
    @GetMapping("/article/addPage")
    public String addPage(Model model, HttpSession session) {
        List<Category> categories = categoryService.queryAllCategory();
        model.addAttribute("categories", categories);
        User user= userService.queryById((int)session.getAttribute("userId")) ;
        switch (user.getStatus()){
            case 0:
                model.addAttribute("status", 0);
                break;
            case 1:
                model.addAttribute("status", 1);
                break;
            case 2:
                model.addAttribute("status", 2);
                break;
        }
        return "index/article/add";
    }

    @GetMapping("/article/index")
    public String indexPage(Model model) {
        List<Category> categories=categoryService.queryAllCategory();
        model.addAttribute("categories", categories);
        System.out.println(categories);
        return "index/article/index";
    }
    @GetMapping("/index/error")
    public String errorPage(Model model) {
        List<Category> categories=categoryService.queryAllCategory();
        model.addAttribute("categories", categories);
        System.out.println(categories);
        model.addAttribute("message", "对不起，您的权限不够，请升级您的权限");
        return "index/status";
    }
    /*跳转至文章详情页*/
    @GetMapping("/article/detail/{id}")
    public String detail(@PathVariable int id, Model model,HttpSession session) {
        ArticleInfo articleInfo =articleService.queryArticleInfoById(id);
        System.out.println("通过文章id查询文章相关的所有信息 文章 分类吃等");
        String content=articleInfo.getContent();
        articleInfo.setContent(MarkdownUtils.markdownToHtmlExtensions(
                content
        ));
        model.addAttribute("articleInfo", articleInfo);
        List<CommentInfo> commentInfos=commentService.queryCommentsByArticleId(id);
        model.addAttribute("commentInfos", commentInfos);
        System.out.println("通过文章id查询评论");
        List<Category> categories=categoryService.queryAllCategory();
        model.addAttribute("categories", categories);
        System.out.println("查询所有分类");
        if (session.getAttribute("userId")==null){
            model.addAttribute("message", "对不起，请先登录！！！");
            return "index/status";
        }
        if ((int)session.getAttribute("userStatus")>=articleInfo.getGrade()){
            return "index/article/detail";
        }
       else{
           /**/
            model.addAttribute("message", "对不起，您的权限不够，请升级您的权限");
            return "index/status";
        }
    }

    /*发帖*/
    @ResponseBody
    @PostMapping("/article/add")
    public Object addArticle(@RequestParam String title,
                             @RequestParam String summary,
                             @RequestParam String content,
                             @RequestParam String grade,
                             @RequestParam String category_id,
                             HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title",title);
        map.put("summary",summary);
        map.put("content",content);
        map.put("create_time",new Date());
        map.put("isTop",0);
        map.put("refinement",0);
        map.put("grade",grade);
        map.put("user_id",session.getAttribute("userId"));
        map.put("category_id",category_id);
        boolean result=articleService.addArticle(map);
        Map<String, Object> res = new HashMap<String, Object>();
        if (result){
            //不要把密码传到前端页面
            System.out.println("发帖成功");
            res.put("success",1);
            res.put("message","恭喜您，发帖成功");
        }else {
            System.out.println("发帖失败");
            res.put("success",0);
            res.put("message","对不起，发帖失败");
        }
        return res;
    }
}
