package com.yujian.controller.admin.user;

import com.yujian.pojo.User;
import com.yujian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserManagementController {
    /*页面跳转*/
    /*页面跳转至 欢迎用户添加*/
    @Autowired
    private UserService userService;

    /*页面跳转至 用户管理页面*/
    @GetMapping("/admin/userManagement")
    public String adminUserManagementPage(Model model){
        List<User> users = userService.queryAllUser();
        model.addAttribute("users", users);
        System.out.println("查询所有用户，跳转至用户管理页面");
        System.out.println(users);
        return "admin/user/userManagement";
    }

    @GetMapping("/admin/user/page/add")
    public String adminAddUserPage(){
        return "admin/user/addUser";
    }
    @GetMapping("/admin/user/page/update/{id}")
    public String adminUpdateUserPage(@PathVariable int id,Model model){
        User user=userService.queryById(id);
        model.addAttribute("user", user);
        return "admin/user/updateUser";
    }
    @GetMapping("/admin/user/page/updatePassword/{id}")
    public String adminUpdateUserPasswordPage(@PathVariable int id,Model model){
        User user=userService.queryById(id);
        model.addAttribute("user", user);
        return "admin/user/updatePassword";
    }
    /*用户添加*/
    @ResponseBody
    @GetMapping("/admin/user/add")

    public Object add(@RequestParam String username,
                      @RequestParam String password,
                      @RequestParam String tel,
                      @RequestParam String email,
                       @RequestParam String status){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username",username);
        map.put("password",password);
        map.put("nickname",username);
        map.put("tel",tel);
        map.put("status",status);
        map.put("email",email);
        System.out.println("执行SQL语句前");
        boolean result= userService.addUser(map);
        System.out.println("执行SQL语句后");
        System.out.println(result);
        Map<String, Object> res = new HashMap<String, Object>();
        if (result){
            res.put("success",result);
            res.put("message","添加成功");
        }else{
            res.put("success",result);
            res.put("message","添加失败");
        }
        return res;
    }
    /*用户修改*/
    @ResponseBody
    @GetMapping("/admin/user/update")
    public Object update(@RequestParam String username,
                         @RequestParam String nickname,
                         @RequestParam String tel,
                         @RequestParam String email,
                         @RequestParam String integral,
                         @RequestParam String status, @RequestParam int id){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",id);
        map.put("username",username);
        map.put("nickname",nickname);
        map.put("tel",tel);
        map.put("integral",integral);
        map.put("status",status);
        map.put("email",email);
        System.out.println("执行SQL语句前");
        boolean result=userService.updateUser(map);
        System.out.println("执行SQL语句后");
        System.out.println(result);
        Map<String, Object> res = new HashMap<String, Object>();
        if (result){
            res.put("success",result);
            res.put("message","修改成功");
        }else{
            res.put("success",result);
            res.put("message","修改失败");
        }
        return res;
    }
    /*修改密码*/
    @ResponseBody
    @GetMapping("/admin/user/updatePassword")
    public Object updatePassword(@RequestParam String oldpass,
                         @RequestParam String newpass,
                         @RequestParam String nickname,
                         @RequestParam String repass,
                         @RequestParam int id){
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> res = new HashMap<String, Object>();
        User user=userService.queryById(id);
        if (!user.getPassword().equals(oldpass)){
            res.put("success",false);
            res.put("message","旧密码输入错误");
        }else{
            map.put("id",id);
            map.put("password",newpass);
            System.out.println("执行SQL语句前");
            boolean result=userService.updateUser(map);
            System.out.println("执行SQL语句后");
            System.out.println(result);
            if (result){
                res.put("success",result);
                res.put("message","密码修改成功");
            }else{
                res.put("success",result);
                res.put("message","密码修改失败");
            }
        }
        return res;
    }
    /*删除用户*/
    @ResponseBody
    @GetMapping("/admin/user/delete/{id}")
    public Object adminDelete(@PathVariable int id){
        boolean result=userService.deleteUserById(id);
        Map<String, Object> res = new HashMap<String, Object>();
        if (result){
            res.put("success",result);
            res.put("message","删除用户成功");
        }else{
            res.put("success",result);
            res.put("message","删除用户失败");
        }
        return res;
    }

}
