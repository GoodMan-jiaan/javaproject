package com.example.demo.service;

import com.example.demo.entity.PageRequest;
import com.example.demo.entity.PageResult;

public interface VideoGatherService {
    //通过用户ID查看收藏信息
    PageResult getVideoGatherByUserId(PageRequest pageRequest, int userId);

    //通过用户Id与视频集的名称搜索
    PageResult getVideoGatherByUserIdAndTitle(PageRequest pageRequest,int userId,String title);

    //通过视频集Id删除视频集
    boolean deleteVideoGatherById(int videoesGatherId);
    //通过视频集名称查看信息
    PageResult getVideoGatherByName(PageRequest pageRequest,String videoesGatherName);
}
