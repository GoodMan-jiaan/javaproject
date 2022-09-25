package com.example.demo.service;

import com.example.demo.entity.PageRequest;
import com.example.demo.entity.PageResult;

public interface CollectService {

    //通过用户ID查看收藏信息
    PageResult SelectCollect(PageRequest pageRequest, int userId);

    //分页获取收藏
    PageResult getPageCollect(PageRequest pageRequest);
}
