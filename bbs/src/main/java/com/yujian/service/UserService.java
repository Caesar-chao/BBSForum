package com.yujian.service;

import com.yujian.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    /*登录验证*/
    User queryUserByUsernameAndPassword(String username, String password);
    /*账号注册 ，增加一个用户*/
    boolean addUser(Map<String,Object> map);
    /*信息修改*/
    boolean updateUser(Map<String,Object> map);
    /*根据id查询用户*/
    User queryById(int id);
    /*======================后台===============================*/
    /*查询所有用户*/
    List<User> queryAllUser();
    /*根据id删除用户*/
    boolean deleteUserById(int id);
}
