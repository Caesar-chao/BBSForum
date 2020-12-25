package com.yujian.controller.admin;

import com.yujian.pojo.Admin;
import com.yujian.pojo.User;
import com.yujian.service.AdminService;
import com.yujian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    /*页面跳转至登录页*/
    @GetMapping("/admin/loginPage")
    public String adminLoginPage(){

        return "admin/login";
    }
    /**/
    /*页面跳转至首页*/
    @GetMapping("/admin/index")
    public String adminIndexPage(){
        return "admin/index";
    }
    /*页面跳转至 欢迎页面*/
    @GetMapping("/admin/welcome")
    public String adminWelcomePage(){
        return "admin/welcome";
    }

    /*注销
     * 清除session，重定向至首页
     * */
    @GetMapping("/admin/loginout")
    public String loginout(HttpSession session) {
        session.removeAttribute("adminId");
        session.removeAttribute("adminName");
        session.removeAttribute("adminStatus");
        return "redirect:/admin/loginPage";
    }

    /*登录验证*/
    @ResponseBody
    @PostMapping("/admin/login")
    public Object adminLogin(@RequestParam String username, @RequestParam String password, HttpSession session) {
        System.out.println("===============跳转至登陆控制器中，判断用户名密码是否正确===============");
        Admin admin = adminService.queryAdminByUsernameAndPassword(username, password);
        Map<String, Object> res = new HashMap<String, Object>();
        if (admin != null) {
            //不要把密码传到前端页面
            System.out.println("登陆成功，登录账号："+username);
            admin.setPassword(null);
            session.setAttribute("adminId", admin.getId());
            session.setAttribute("adminName",admin.getUsername());
            session.setAttribute("adminStatus",admin.getStatus());
            res.put("success",1);
            res.put("message","管理员登陆成功");
        } else {
            System.out.println("登录失败，用户名或密码错误");
            res.put("success",0);
            res.put("message","登录失败，用户名或密码错误");
        }
        return res;
    }

}
