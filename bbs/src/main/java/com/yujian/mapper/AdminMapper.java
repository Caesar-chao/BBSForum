package com.yujian.mapper;

import com.yujian.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/*注解Mapper：表示这是一个mybatis的mapper类*/
@Mapper
@Repository
public interface AdminMapper {
    /*管理员登录*/
    Admin queryAdminByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
