package com.yujian.service;

import com.yujian.pojo.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminService {
    /*管理员登录*/
    Admin queryAdminByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
