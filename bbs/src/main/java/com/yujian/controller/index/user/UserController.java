package com.yujian.controller.index.user;

import com.yujian.pojo.Article;
import com.yujian.pojo.Comment;
import com.yujian.pojo.User;
import com.yujian.service.ArticleService;
import com.yujian.service.CommentService;
import com.yujian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    /*页面跳转*/
    @GetMapping("/user/active")
    public String activePage() {
        return "index/user/active";
    }
    @GetMapping("/user/forget")
    public String forgetPage() {
        return "index/user/forget";
    }
    @GetMapping("/user/index")
    public String indexPage(HttpSession session,
                            Model model) {
        /*获取用户信息，并传递至 用户个人主页页面*/
        User user=userService.queryById((int) session.getAttribute("userId"));
        model.addAttribute("user", user);
        /*根据用户id获取用户最新发布的十条帖子*/
        List<Article> articles = articleService.queryArticleByUserId((int) session.getAttribute("userId"));
        model.addAttribute("articles", articles);

        /*根据用户id获取用户最新发布的十条评论*/
        List<Comment> comments = commentService.queryCommentByUserId((int) session.getAttribute("userId"));
        model.addAttribute("comments", comments);
        return "index/user/index";
    }
    @GetMapping("/user/message")
    public String loginPage() {
        return "index/user/message";
    }
    @GetMapping("/user/home")
    public String homePage(HttpSession session,
                           Model model) {
        /*获取用户信息，并传递至 用户个人主页页面*/
        User user=userService.queryById((int) session.getAttribute("userId"));
        model.addAttribute("user", user);
        /*根据用户id获取用户最新发布的十条帖子*/
        List<Article> articles = articleService.queryArticleByUserId((int) session.getAttribute("userId"));
        model.addAttribute("articles", articles);

        /*根据用户id获取用户最新发布的十条评论*/
        List<Comment> comments = commentService.queryCommentByUserId((int) session.getAttribute("userId"));
        model.addAttribute("comments", comments);
        return "index/user/home";
    }
    @GetMapping("/user/set")
    public String setPage(HttpSession session,
                          Model model) {
        User user=userService.queryById((int) session.getAttribute("userId"));
        model.addAttribute("user", user);
        return "index/user/set";
    }
    /*信息修改*/
    @ResponseBody
    @PostMapping("/user/update")
    public Object update(@RequestParam String nickname,
                           @RequestParam String tel,
                           @RequestParam String email,
                           @RequestParam String signature,
                           HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",session.getAttribute("userId"));
        map.put("nickname",nickname);
        map.put("signature",signature);
        map.put("tel",tel);
        map.put("email",email);
        boolean result= userService.updateUser(map);
        Map<String, Object> res = new HashMap<String, Object>();
        if (result) {
            //不要把密码传到前端页面
            System.out.println("信息修改成功");
            res.put("success",1);
            res.put("message","恭喜您，您的信息修改成功");
        } else {
            System.out.println("信息修改失败");
            res.put("success",0);
            res.put("message","对不起，您的信息修改失败");
        }
        return res;
    }
    @ResponseBody
    @PostMapping("/user/setPass")
    public Object setPass(@RequestParam String nowpass,
                          @RequestParam String password,
                          @RequestParam String repass,
                          HttpSession session){
        User user=userService.queryById((int) session.getAttribute("userId"));
        Map<String, Object> res = new HashMap<String, Object>();
        if (!user.getPassword().equals(nowpass)){
            System.out.println("密码输入错误");
            res.put("success",0);
            res.put("message","对不起，您的密码输入错误");
        }else {
            if (!repass.equals(password)){
                res.put("success",0);
                res.put("message","对不起，两次密码输入不一致");
                System.out.println("两次密码输入不一致");
            }else{
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id",session.getAttribute("userId"));
                map.put("password",password);
                boolean result= userService.updateUser(map);
                if (result){
                    //不要把密码传到前端页面
                    System.out.println("密码修改成功");
                    res.put("success",1);
                    res.put("message","恭喜您，您的密码修改成功，请重新登录");
                    session.removeAttribute("userId");
                    session.removeAttribute("userName");
                    session.removeAttribute("userStatus");
                }else{
                    System.out.println("密码修改失败");
                    res.put("success",0);
                    res.put("message","对不起，您的密码修改失败");
                }
            }
        }
        return res;
    }
}
