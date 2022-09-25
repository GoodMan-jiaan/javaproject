package com.example.demo.service;

import com.example.demo.entity.PageRequest;
import com.example.demo.entity.PageResult;

public interface UserService {
    //分页查询用户
    PageResult findUserByPage(PageRequest pageRequest);
    //根据用户昵称查询用户
    PageResult getUserListByUserName(PageRequest pageRequest,String userName);
    //根据用户Id删除用户
    boolean adminsDeleteByUserId(int userId);
}
