package com.example.demo.service;

import com.example.demo.entity.PageRequest;
import com.example.demo.entity.PageResult;

public interface  MovieService {
    //分页查询视频集
    PageResult findPage(PageRequest pageRequest);

    //通过视频类型查询视频集信息
    PageResult getVideoesgatherByMassage(String classType,String organType,String contentType,int total,PageRequest pageQuery);

    //分页查询视频
    PageResult getPageCourse(PageRequest pageRequest);

    //模糊查询分页视频
    PageResult getPageCourseByName(PageRequest pageRequest,String courseVideoesName);
}
