package com.example.demo.service;

import com.example.demo.entity.PageRequest;
import com.example.demo.entity.PageResult;

public interface GiveLikeService {
    //通过用户ID查看点赞信息
    PageResult SelectGiveLikeById(PageRequest pageRequest,int userId);

    //分页查询点赞信息
    PageResult getPageGiveLike(PageRequest pageRequest);
}
