package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.mapper.*;
import com.example.demo.service.VideoGatherService;
import com.example.demo.utils.PageUtils;
import com.example.demo.utils.QiniuFileUtil;
import com.example.demo.utils.QiniuImageUtil;
import com.example.demo.utils.QiniuUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VideoGatherServiceImpl implements VideoGatherService {
    @Autowired
    private VideoesgatherMapper videoesgatherMapper;

    @Autowired
    private GivelikeMapper givelikeMapper;

    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private HistoricRecordsMapper historicRecordsMapper;


    @Override
    public PageResult getVideoGatherByUserId(PageRequest pageRequest, int userId) {
        return PageUtils.getPageResult(pageRequest,getVideoGatherByUserId1(pageRequest, userId));
    }

    private PageInfo<Videoesgather> getVideoGatherByUserId1(PageRequest pageRequest, int userId){
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();

        Videoesgather videoesgather=new Videoesgather();
        videoesgather.setUserId(userId);
        PageHelper.startPage(pageNum, pageSize);
        List<Videoesgather> sysMenus =videoesgatherMapper.SelectVideoesgather(videoesgather);
        return new PageInfo<Videoesgather>(sysMenus);
    }

    @Override
    public PageResult getVideoGatherByUserIdAndTitle(PageRequest pageRequest, int userId, String title) {
        return PageUtils.getPageResult(pageRequest,getVideoGatherByUserIdAndTitle1(pageRequest,userId,title));
    }

    private PageInfo<Videoesgather> getVideoGatherByUserIdAndTitle1(PageRequest pageRequest, int userId,String title){
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();

        Videoesgather videoesgather=new Videoesgather();
        videoesgather.setUserId(userId);
        PageHelper.startPage(pageNum, pageSize);
        List<Videoesgather> sysMenus =videoesgatherMapper.getVideoGatherByUserIdAndTitle(userId,title);
        return new PageInfo<Videoesgather>(sysMenus);
    }

    @Override
    public boolean deleteVideoGatherById(int videoesGatherId) {
        int GivelikeNum=givelikeMapper.DeleteGicelikeById(videoesGatherId);
        int CollectNum=collectMapper.DeleteCollectById(videoesGatherId);
        int CommentNum=commentMapper.deleteByVideoesGatherId(videoesGatherId);
        int HistoricNum=historicRecordsMapper.deleteHistoricById(videoesGatherId);

        QiniuFileUtil qiniuFileUtil=new QiniuFileUtil();
        QiniuUtil qiniuUtil=new QiniuUtil();
        QiniuImageUtil qiniuImageUtil=new QiniuImageUtil();

        Course course=new Course();
        course.setVideoesGatherId(videoesGatherId);
        List<Course> courseList=courseMapper.SelectCourseByVideoesGatherId(course);
        if(courseList.size()!=0){
            for (int i=0;i<courseList.size();i++){
                int courseId=courseList.get(i).getCourseId();
                String CourseFileResources=courseList.get(i).getCourseFileResources();
                String CourseVideoResources=courseList.get(i).getCourseVideoesResources();
                if(CourseFileResources!=null){
                    int code=qiniuFileUtil.deleteFileFromQiniu(CourseFileResources);
                }
                if(CourseVideoResources!=null){
                    int code=qiniuUtil.deleteFileFromQiniu(CourseVideoResources);
                }

                int CourseNum=courseMapper.deleteByCourseId(courseId);
            }
        }
        Videoesgather videoesgather=new Videoesgather();
        videoesgather.setVideoesGatherId(videoesGatherId);

        Videoesgather videoesgather1=videoesgatherMapper.selectVideoesgatherById(videoesgather);
        if(videoesgather1.getVideoesGatherImage()!=null){
            int code=qiniuImageUtil.deleteImageFromQiniu(videoesgather1.getVideoesGatherImage());
        }
        int videoGatherNum=videoesgatherMapper.deletevideoesGatherById(videoesGatherId);
        if(videoGatherNum==1){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public PageResult getVideoGatherByName(PageRequest pageRequest, String videoesGatherName) {
        return PageUtils.getPageResult(pageRequest,getVideoGatherByName1(pageRequest,videoesGatherName));
    }

    private PageInfo<Videoesgather> getVideoGatherByName1(PageRequest pageRequest, String videoesGatherName){
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();

        PageHelper.startPage(pageNum, pageSize);
        List<Videoesgather> sysMenus =videoesgatherMapper.getVideoGatherByName(videoesGatherName);
        return new PageInfo<Videoesgather>(sysMenus);
    }
}
