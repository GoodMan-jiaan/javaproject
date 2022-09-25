package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.PageRequest;
import com.example.demo.entity.PageResult;
import com.example.demo.entity.Videoesgather;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.mapper.VideoesgatherMapper;
import com.example.demo.service.MovieService;
import com.example.demo.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private VideoesgatherMapper videoesgatherMapper;

    @Autowired
    private CourseMapper courseMapper;

    //与控制类的那个名称对应
    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest,getPageInfo(pageRequest));
    }

    /*调用分页插件完成分页*/
    private PageInfo<Videoesgather> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Videoesgather> sysMenus = videoesgatherMapper.pageAll();
        return new PageInfo<Videoesgather>(sysMenus);
    }


    @Override
    public PageResult getVideoesgatherByMassage(String classType, String organType, String contentType,int total,PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest,getVideoesByMassage(classType,organType,contentType,total,pageRequest));
    }

    private PageInfo<Videoesgather> getVideoesByMassage(String classType, String organType, String contentType,int total, PageRequest pageRequest){

        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Videoesgather> sysMenus=new LinkedList<>();
        if(total==1)
            sysMenus = videoesgatherMapper.getVideoesgatherByMassage(classType,organType,contentType);
        else if(total==2)
            sysMenus = videoesgatherMapper.getVideoesgatherByMassage2(classType,organType);
        else if(total==3)
            sysMenus = videoesgatherMapper.getVideoesgatherByMassage3(classType);
        else if(total==4)
            sysMenus = videoesgatherMapper.getVideoesgatherByMassage4(classType,contentType);
        else if(total==5)
            sysMenus = videoesgatherMapper.getVideoesgatherByMassage5(contentType);
        else if(total==6)
            sysMenus = videoesgatherMapper.getVideoesgatherByMassage6(organType);
        else if(total==7)
            sysMenus = videoesgatherMapper.getVideoesgatherByMassage7(organType,contentType);
        else if (total==8)
            sysMenus=videoesgatherMapper.pageAll();
        return new PageInfo<Videoesgather>(sysMenus);
    }


    @Override
    public PageResult getPageCourse(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest,getPageCourse1(pageRequest));
    }

    private PageInfo<Course> getPageCourse1(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Course> sysMenus = courseMapper.getPageCourse();
        return new PageInfo<Course>(sysMenus);
    }

    @Override
    public PageResult getPageCourseByName(PageRequest pageRequest,String courseVideoesName) {
        return PageUtils.getPageResult(pageRequest,getPageCourseByName1(pageRequest,courseVideoesName));
    }

    private PageInfo<Course> getPageCourseByName1(PageRequest pageRequest,String courseVideoesName) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Course> sysMenus = courseMapper.getPageCourseByName(courseVideoesName);
        return new PageInfo<Course>(sysMenus);
    }
}
