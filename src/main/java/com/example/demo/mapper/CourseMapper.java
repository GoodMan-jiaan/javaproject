package com.example.demo.mapper;

import com.example.demo.entity.Course;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {

    @Select("select * from course")
    public List<Course> getPageCourse();

    //添加视频单个视频
    @Insert("insert into course(courseId, videoesGatherId, " +
            "courseVideoesName, courseVideoesUpTime, " +
            "courseVideoesResources, courseFileTitle, courseFileResources,videoesNumber)" +
            "value(#{courseId},#{videoesGatherId},#{courseVideoesName}," +
            "#{courseVideoesUpTime},#{courseVideoesResources},#{courseFileTitle}," +
            "#{courseFileResources},#{videoesNumber})")
    public int InsertCourse(Course course);
    //没有文件添加视频
    @Insert("insert into course(courseId,videoesGatherId,courseVideoesName,courseVideoesUpTime,courseVideoesResources,videoesNumber)" +
            "value(#{courseId},#{videoesGatherId},#{courseVideoesName},#{courseVideoesUpTime},#{courseVideoesResources},#{videoesNumber})")
    public int InsertCourseNoFile(Course course);

    //根据视频集Id查看视频，根据videoesNumber降
    @Select("select * from course where videoesGatherId=#{videoesGatherId} order by videoesNumber desc")
    public List<Course> SelectCourseByVideoesGatherId(Course course);

    @Select("select * from course where videoesGatherId=#{videoesGatherId}")
    public List<Course> getCourseByVideoesGatherId(Course course);

    //根据视频集Id删除视频
    @Delete("delete from course where videoesGatherId=#{videoesGatherId}")
    public int deleteCourseById(int videoesGatherId);

    @Delete("delete from course where courseId=#{courseId}")
    public int deleteByCourseId(int courseId);

    @Select("select * from course where courseId=#{courseId}")
    public Course SelectCourseById(int courseId);

    //根据视频名称模糊查询视频
    @Select("select * from course where courseVideoesName like concat('%',#{courseVideoesName},'%')")
    public List<Course> getPageCourseByName(String courseVideoesName);

    //更改视频文件内容
    @Update("update course set courseFileTitle=#{courseFileTitle},courseFileResources=#{courseFileResources} where courseId=#{courseId}")
    public int updateCourseFile(Course course);

    //更改视频文件内容
    @Update("update course set courseFileResources=#{courseFileResources} where courseId=#{courseId}")
    public int updateCourseFile1(Course course);

    //更改视频内容
    @Update("update course set courseVideoesResources=#{courseVideoesResources} where courseId=#{courseId}")
    public int UpdateCourseImageUrl(int courseId,String courseVideoesResources);

    //更改视频标题
    @Update("update course set courseVideoesName=#{courseVideoesName},videoesNumber=#{videoesNumber} where courseId=#{courseId}")
    public int updateCourseTitle(int courseId,String courseVideoesName,int videoesNumber);

    //在管理界面更改视频信息
    @Update("update course set courseVideoesName=#{courseVideoesName},videoesNumber=#{videoesNumber},courseFileTitle=#{courseFileTitle} " +
            "where courseId=#{courseId}")
    public int updateAdminsCourse(Course course);
}
