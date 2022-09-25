package com.example.demo.controller;

import com.example.demo.entity.Course;

import com.example.demo.entity.PageRequest;
import com.example.demo.entity.Videoesgather;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.mapper.VideoesgatherMapper;
//import com.example.demo.utils.QiniuCloudUtil;


import com.example.demo.service.MovieService;
import com.example.demo.utils.QiniuFileUtil;
import com.example.demo.utils.QiniuImageUtil;
import com.example.demo.utils.QiniuUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//视频操作
@RestController
@Api(tags = "文件上传控制器")
public class CourseController {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private MovieService movieService;


    @RequestMapping("/getPageCourse")
    @ApiOperation("分页查询视频")
    public Object getPageCourse(int pageNum,int pageSize){
        PageRequest pageRequest=new PageRequest();
        pageRequest.setPageSize(pageSize);
        pageRequest.setPageNum(pageNum);
        return movieService.getPageCourse(pageRequest);
    }

    @RequestMapping("/SelectCourseById")
    @ApiOperation("根据视频Id查找视频信息")
    public Course SelectCourseById(int courseId){
        return courseMapper.SelectCourseById(courseId);
    }

    @RequestMapping("/getPageCourseByName")
    @ApiOperation("模糊分页查询")
    public Object getPageCourseByName(int pageNum,int pageSize,String courseVideoesName){
        PageRequest pageRequest=new PageRequest();
        pageRequest.setPageNum(pageNum);
        pageRequest.setPageSize(pageSize);
        return movieService.getPageCourseByName(pageRequest,courseVideoesName);
    }

    @RequestMapping("/UpdateCourseImageUrl")
    @ApiOperation("根据视频Id更视频路径")
    public boolean UpdateCourseImageUrl(int courseId,String courseVideoesResources){
        int result=courseMapper.UpdateCourseImageUrl(courseId,courseVideoesResources);
        if(result==1){
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("/deleteByCourseId")
    @ApiOperation("根据视频id删除信息")
    public boolean deleteByCourseId (int courseId){
        QiniuImageUtil qiniuImageUtil=new QiniuImageUtil();
        QiniuUtil qiniuUtil=new QiniuUtil();
        QiniuFileUtil qiniuFileUtil=new QiniuFileUtil();
        //根据视频Id获取
        Course course=courseMapper.SelectCourseById(courseId);
        if(course.getCourseFileResources()!=null){
            qiniuFileUtil.deleteFileFromQiniu(course.getCourseFileResources());
        }
        if(course.getCourseVideoesResources()!=null){
            qiniuUtil.deleteFileFromQiniu(course.getCourseVideoesResources());
        }
        int result=courseMapper.deleteByCourseId(courseId);
        if(result==1){
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("/updateCourseTitle")
    @ApiOperation("根据视频Id更新视频标题")
    public boolean updateCourseTitle(int courseId,String courseVideoesName,int videoesNumber){
        int result=courseMapper.updateCourseTitle(courseId,courseVideoesName,videoesNumber);
        if(result==1){
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("/InsertCourseNext")
    @ApiOperation("有视频集的插入视频")
    public boolean InsertCourseNext(int videoesGatherId,String courseVideoesName,String courseVideoesResources,
                                    String courseFileTitle,String courseFileResources){
        Course course =new Course();
        if(videoesGatherId!=0&&courseVideoesName!=null&&courseVideoesResources!=null){
            course.setVideoesGatherId(videoesGatherId);
            course.setCourseVideoesName(courseVideoesName);
            course.setCourseVideoesResources(courseVideoesResources);
        }
        course.setCourseFileTitle(courseFileTitle);
        course.setCourseFileResources(courseFileResources);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        course.setCourseVideoesUpTime(date);
        //逆向排列集数生成集数
        List<Course> courseList=courseMapper.SelectCourseByVideoesGatherId(course);
        course.setVideoesNumber(courseList.get(0).getVideoesNumber()+1);
        int result=courseMapper.InsertCourse(course);
        if (result==1){
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("/InsertCourseNoFile")
    @ApiOperation("没有文件时插入视频")
    public boolean InsertCourseNoFile(int videoesGatherId,String courseVideoesName,String courseVideoesResources,int jude){
        Course course=new Course();
        if(videoesGatherId!=0&&courseVideoesName!=null&&courseVideoesResources!=null){
            course.setVideoesGatherId(videoesGatherId);
            course.setCourseVideoesName(courseVideoesName);
            course.setCourseVideoesResources(courseVideoesResources);
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        course.setCourseVideoesUpTime(date);
        if(jude==1){
            //逆向排列集数生成集数
            List<Course> courseList=courseMapper.SelectCourseByVideoesGatherId(course);
            course.setVideoesNumber(courseList.get(0).getVideoesNumber()+1);
        }else if (jude==0){
            course.setVideoesNumber(1);
        }

        int result=courseMapper.InsertCourseNoFile(course);
        if (result==1){
            return true;
        }else {
            return false;
        }
    }


    //在管理界面更改视频信息
    @RequestMapping("/updateAdminsCourse")
    @ApiOperation("在管理界面更改视频信息")
    public boolean updateAdminsCourse(int courseId,String courseVideoesName,int videoesNumber,String courseFileTitle){
        Course course=new Course();
        course.setCourseId(courseId);
        course.setCourseVideoesName(courseVideoesName);
        course.setVideoesNumber(videoesNumber);
        course.setCourseFileTitle(courseFileTitle);
        int result=courseMapper.updateAdminsCourse(course);
        if(result==1){
            return true;
        }else {
            return false;
        }
    }

    //根据视频集Id获取视频
    @RequestMapping("/getCourse")
    @ApiOperation("根据视频集Id获取视频逆序")
    public List<Course> getCourseByVideoesGatherId(int videoesGatherId){

        Course course=new Course();
        course.setVideoesGatherId(videoesGatherId);
        List<Course> courseList=courseMapper.SelectCourseByVideoesGatherId(course);
        return courseList;
    }

    //根据视频集Id获取视频
    @RequestMapping("/getCourse2")
    @ApiOperation("根据视频集Id获取视频正序")
    public List<Course> getCourseByVideoesGatherId2(int videoesGatherId){

        Course course=new Course();
        course.setVideoesGatherId(videoesGatherId);
        List<Course> courseList=courseMapper.getCourseByVideoesGatherId(course);
        return courseList;
    }

    //根据视频集Id获取文件信息
    @RequestMapping("/getFileByVideoGatherId")
    @ApiOperation("根据视频集ID过去文件信息")
    public List<Course> getFileByVideoGatherId(int videoesGatherId){
        List<Course> courseList=new ArrayList<>();

        Course course=new Course();
        course.setVideoesGatherId(videoesGatherId);
        List<Course> courseList1=courseMapper.getCourseByVideoesGatherId(course);
        for(int i=0;i<courseList1.size();i++){
            int titleNum=0;
            int urlNum=0;

            String title=courseList1.get(i).getCourseFileTitle();
            String url=courseList1.get(i).getCourseFileResources();

            Course course1=new Course();


            if(title==null){
                titleNum=1;
            }
            if(url==null){
                urlNum=1;
            }
            if(titleNum==0&&urlNum==0){
                course1.setCourseFileTitle(title);
                course1.setCourseFileResources(url);
                courseList.add(course1);
            }

        }

        return courseList;
    }

    @RequestMapping("/InsertCourse")
    @ApiOperation("没有视频集时添加信息到视频表")
    public boolean InsertCourse(int videoesGatherId,String courseVideoesName,String courseVideoesResources,
                                String courseFileTitle,String courseFileResources){
        Course course=new Course();
        if(videoesGatherId!=0&&courseVideoesResources!=null&&courseVideoesName!=null){
            course.setVideoesGatherId(videoesGatherId);
            course.setCourseVideoesResources(courseVideoesResources);
            course.setCourseVideoesName(courseVideoesName);
        }
        course.setCourseFileTitle(courseFileTitle);
        course.setCourseFileResources(courseFileResources);
        course.setVideoesNumber(1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        course.setCourseVideoesUpTime(date);
        int result=courseMapper.InsertCourse(course);
        if (result==1){
            return true;
        }else {
            return false;
        }
    }


    @PostMapping("/uploadFile")
    @ResponseBody
    @ApiOperation("文件上传")
    public String uploadFile(@RequestParam("file") MultipartFile file){
        try {
            byte[] fileBytes = file.getBytes();
            QiniuFileUtil qiniuFileUtil=new QiniuFileUtil();

            try {
                String temp=qiniuFileUtil.uploadFile(file);
                return temp;
            }catch (Exception e) {
                e.printStackTrace();
                return "上传出错";
            }
        } catch (IOException e){
            return "上传出错";
        }
    }
//http://r9o05za4e.hn-bkt.clouddn.com/%E6%A1%8C%E9%9D%A2.png

    @RequestMapping("/DeleteFile")
    @ApiOperation("删除文件")
    public int DeleteFile(String fileName){
        QiniuFileUtil qiniuFileUtil=new QiniuFileUtil();
        int code=qiniuFileUtil.deleteFileFromQiniu(fileName);
        return code;
    }



    @PostMapping("/uploadImage")
    @ResponseBody
    @ApiOperation("图片上传")
    public String uploadImage(@RequestParam("file") MultipartFile file){
        try {
            byte[] fileBytes = file.getBytes();
            QiniuImageUtil qiniuImageUtil=new QiniuImageUtil();

            try {
                String temp=qiniuImageUtil.uploadImage(file);
                return temp;
            }catch (Exception e) {
                e.printStackTrace();
                return "上传出错";
            }
        } catch (IOException e){
            return "上传出错";
        }
    }

    @RequestMapping("/DeleteImage")
    @ApiOperation("删除图片")
    public int DeleteImage(String fileName){
        QiniuImageUtil qiniuImageUtil=new QiniuImageUtil();
        int code=qiniuImageUtil.deleteImageFromQiniu(fileName);
        return code;
    }

    @PostMapping("/upload")
    @ResponseBody
    @ApiOperation("文件上传")
    public String upload(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
        try {
            byte[] fileBytes = file.getBytes();
//            QiniuCloudUtil qiniuCloudUtil=new QiniuCloudUtil();
            QiniuUtil qiniuUtil=new QiniuUtil();

            try {
                String temp=qiniuUtil.upload(file);
                return temp;
            }catch (Exception e) {
                e.printStackTrace();
                return "上传出错";
            }
        } catch (IOException e){
            return "上传出错";
        }
    }

    @RequestMapping("/deleteVideo")
    @ApiOperation("删除视频")
    public int deleteVideo(String fileName){
        QiniuUtil qiniuUtil=new QiniuUtil();
        int code=qiniuUtil.deleteFileFromQiniu(fileName);
        return code;
    }

    @RequestMapping("/updateCourseFile")
    @ApiOperation("更新文件信息")
    public boolean updateCourseFile(String courseFileTitle,String courseFileResources,int courseId){
        Course course=new Course();
        course.setCourseId(courseId);
        course.setCourseFileTitle(courseFileTitle);
        course.setCourseFileResources(courseFileResources);
        int result=courseMapper.updateCourseFile(course);
        if(result==1){
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("/updateCourseFile1")
    @ApiOperation("更新文件的路径")
    public boolean updateCourseFile1(String courseFileResources,int courseId){
        Course course=new Course();
        course.setCourseId(courseId);
        course.setCourseFileResources(courseFileResources);
        int result=courseMapper.updateCourseFile1(course);
        if(result==1){
            return true;
        }else {
            return false;
        }
    }
}
