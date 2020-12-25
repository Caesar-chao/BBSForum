package com.yujian.mapper;

import com.yujian.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/*注解Mapper：表示这是一个mybatis的mapper类*/
@Mapper
@Repository
public interface UserMapper {

    /*登录 ，根据用户的username和password查找用户*/
    User queryUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);


    /*账号注册 ，增加一个用户*/
    boolean addUser(Map<String,Object> map);
    /* 修改*/
    boolean updateUser(Map<String,Object> map);
    /*根据id查询用户*/
    User queryById(int id);
    /*======================后台===============================*/
    /*查询所有用户*/
    List<User> queryAllUser();
    /*根据id删除用户*/
    boolean deleteUserById(int id);
}
