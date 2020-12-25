package com.yujian.service.Impl;

import com.yujian.mapper.AdminMapper;
import com.yujian.pojo.Admin;
import com.yujian.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Admin queryAdminByUsernameAndPassword(String username, String password) {
        return adminMapper.queryAdminByUsernameAndPassword(username,password);
    }
}
