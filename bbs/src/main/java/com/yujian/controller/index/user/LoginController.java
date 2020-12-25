package com.yujian.controller.index.user;

import com.yujian.pojo.User;
import com.yujian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    /*
    * 页面跳转
    * */

    @GetMapping("/index/loginPage")
    public String loginPage() {
        return "index/user/login";
    }

    @GetMapping("/index/registerPage")
    public String registerPage() {
        return "index/user/reg";
    }
    /*
    * 登录
    * 前端传递username，password，
    * 通过调用queryUserByUsernameAndPassword方法判断是否登陆成功
    * */
    @ResponseBody
    @PostMapping("/index/login")
    public Object login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {
        System.out.println("===============跳转至登陆控制器中，判断用户名密码是否正确===============");
        Map<String, Object> res = new HashMap<String, Object>();
        User user = userService.queryUserByUsernameAndPassword(username, password);
        if (user != null) {
            //不要把密码传到前端页面
            System.out.println("登陆成功，登录账号："+username);
            user.setPassword(null);
            session.setAttribute("userId", user.getId());
            session.setAttribute("userName",user.getUsername());
            session.setAttribute("userStatus",user.getStatus());
            res.put("success",1);
            res.put("message","恭喜您，登录成功");
        } else {
            System.out.println("登录失败，用户名或密码错误");
            res.put("success",0);
            res.put("message","登录失败，用户名或密码错误");
        }
        return res;
    }
    /*注册
    *
    * */
    @ResponseBody
    @PostMapping("/index/register")
    public Object register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String nickname,
                           @RequestParam String tel,
                           @RequestParam String email) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username",username);
        map.put("password",password);
        map.put("nickname",nickname);
        map.put("tel",tel);
        map.put("status",0);
        map.put("email",email);
        boolean result= userService.addUser(map);
        Map<String, Object> res = new HashMap<String, Object>();
        if (result) {
            //不要把密码传到前端页面
            System.out.println("注册成功，登录账号："+username);
            res.put("success",1);
            res.put("message","恭喜您，注册成功，请重新登录");
        } else {
            res.put("success",0);
            res.put("message","注册失败，请重新注册");
        }
        return res;
    }
    /*注销
    * 清除session，重定向至首页
    * */
    @GetMapping("/index/loginout")
    public String loginout(HttpSession session) {
        session.removeAttribute("userId");
        session.removeAttribute("userName");
        session.removeAttribute("userStatus");
        return "redirect:/index";
    }
}
