package com.yujian.controller.admin.comment;

import com.yujian.dto.ArticleInfo;
import com.yujian.dto.CommentInfo;
import com.yujian.pojo.Category;
import com.yujian.service.ArticleService;
import com.yujian.service.CommentService;
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
public class CommentManagementController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ArticleService articleService;
    /*页面跳转至 评论管理页面*/
    @GetMapping("/admin/commentManagement")
    public String adminCommentManagementPage(Model model){
        List<CommentInfo> commentInfoList=commentService.queryAllCommentInfo();
        model.addAttribute("commentInfoList", commentInfoList);
        System.out.println("查询所有评论，跳转至评论管理页面");
        return "admin/comment/commentManagement";
    }
    @GetMapping("/admin/comment/page/add")
    public String adminAddCategoryPage(Model model){
        List<ArticleInfo> articleInfos= articleService.queryAllArticleInfoById();
        model.addAttribute("articleInfos", articleInfos);
        return "admin/comment/addComment";
    }
    /*分类添加*/
    @ResponseBody
    @GetMapping("/admin/comment/add")
    public Object add(@RequestParam String article_id,
                      @RequestParam String comment_content){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user_id",1);
        map.put("article_id",article_id);
        map.put("comment_content",comment_content);
        map.put("comment_time",new Date());
        System.out.println("执行SQL语句前");
        boolean result= commentService.addComment(map);
        System.out.println("执行SQL语句后");
        System.out.println(result);
        Map<String, Object> res = new HashMap<String, Object>();
        if (result){
            res.put("success",result);
            res.put("message","添加评论成功");
        }else{
            res.put("success",result);
            res.put("message","添加评论失败");
        }
        return res;
    }
    /*跳转修改评论页面*/
    @GetMapping("/admin/comment/page/update/{id}")
    public String adminUpdateCategoryPage(@PathVariable int id, Model model){
        CommentInfo commentInfo =commentService.queryCommentsById(id);
        model.addAttribute("commentInfo", commentInfo);
        System.out.println("根据id查询相应的评论信息，跳转至评论编辑页面");
        return "admin/comment/updateComment";
    }
    /*评论修改*/
    @ResponseBody
    @GetMapping("/admin/comment/update")
    public Object update(@RequestParam String comment_content, @RequestParam int id){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",id);
        map.put("comment_time",new Date());
        map.put("comment_content",comment_content);
        System.out.println("执行SQL语句前");
        boolean result=commentService.updateCommentById(map);
        System.out.println("执行SQL语句后");
        System.out.println(result);
        Map<String, Object> res = new HashMap<String, Object>();
        if (result){
            res.put("success",result);
            res.put("message","评论修改成功");
        }else{
            res.put("success",result);
            res.put("message","评论修改成功");
        }
        return res;
    }
    /*删除分类*/
    @ResponseBody
    @GetMapping("/admin/comment/delete/{id}")
    public Object adminDelete(@PathVariable int id){
        boolean result=commentService.deleteCommentById(id);
        Map<String, Object> res = new HashMap<String, Object>();
        if (result){
            res.put("success",result);
            res.put("message","删除评论成功");
        }else{
            res.put("success",result);
            res.put("message","删除评论失败");
        }
        return res;
    }
}
