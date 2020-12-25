package com.yujian.controller.index.comment;

import com.yujian.service.ArticleService;
import com.yujian.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ArticleService articleService;

    /*增加*/
    @ResponseBody
    @PostMapping("/comment/add")
    public Object addComment(@RequestParam int user_id ,
                             @RequestParam int article_id,
                             @RequestParam String comment_content){
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("user_id",user_id);
        map.put("article_id",article_id);
        map.put("comment_content",comment_content);
        map.put("comment_time",new Date());

        boolean result= commentService.addComment(map);
        articleService.updateCommentNum(article_id);
        Map<String, Object> res = new HashMap<String, Object>();
        if (result) {
            //不要把密码传到前端页面
            System.out.println("评论成功");
            res.put("success",1);
            res.put("message","恭喜您，评论成功");
        } else {
            System.out.println("评论失败");
            res.put("success",0);
            res.put("message","对不起，评论失败");
        }
        return res;
        /*return "redirect:/article/detail/"+article_id;*/
    }
    /*删除*/
    @GetMapping("/comment/delete/{id}")
    public String deleteCommentById(@PathVariable int id,RedirectAttributes attributes){
        boolean result=commentService.deleteCommentById(id);
        if (result) {
            System.out.println("删除评论成功");
            attributes.addFlashAttribute("message", "删除评论成功");
        } else {
            System.out.println("评论失败");
            //因为这是redirect到index页面，如果用model会得不到这个数据
            attributes.addFlashAttribute("message", "删除评论失败");
        }
        return "redirect:/index";
    }


}
