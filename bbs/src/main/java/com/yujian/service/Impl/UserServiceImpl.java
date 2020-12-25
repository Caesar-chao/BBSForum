package com.yujian.service.Impl;

import com.yujian.mapper.UserMapper;
import com.yujian.pojo.User;
import com.yujian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /*登录验证*/
    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        User user = userMapper.queryUserByUsernameAndPassword(username, password);
        return user;
    }
    /*账号注册 ，增加一个用户*/
    @Override
    public boolean addUser(Map<String,Object> map){
      return  userMapper.addUser(map);
    }

    @Override
    public boolean updateUser(Map<String, Object> map) {
        return  userMapper.updateUser(map);
    }
    /*根据id查询用户*/
    @Override
    public User queryById(int id) {
        return userMapper.queryById(id);
    }

    /*==================后台======================*/
    @Override
    public List<User> queryAllUser() {
        return userMapper.queryAllUser();
    }

    @Override
    public boolean deleteUserById(int id) {
        return userMapper.deleteUserById(id);
    }
}
