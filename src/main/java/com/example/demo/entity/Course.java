package com.example.demo.entity;

//单个资源信息表
import java.util.Date;
public class Course {
    private int courseId;
    private int videoesGatherId;
    private String courseVideoesName;
    private String courseVideoesTime;
    private Date courseVideoesUpTime;
    private String courseVideoesResources;
    private String courseFileTitle;
    private String courseFileResources;
    private int videoesNumber;

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", videoesGatherId=" + videoesGatherId +
                ", courseVideoesName='" + courseVideoesName + '\'' +
                ", courseVideoesTime='" + courseVideoesTime + '\'' +
                ", courseVideoesUpTime=" + courseVideoesUpTime +
                ", courseVideoesResources='" + courseVideoesResources + '\'' +
                ", courseFileTitle='" + courseFileTitle + '\'' +
                ", courseFileResources='" + courseFileResources + '\'' +
                ", videoesNumber=" + videoesNumber +
                '}';
    }

    public int getVideoesNumber() {
        return videoesNumber;
    }

    public void setVideoesNumber(int videoesNumber) {
        this.videoesNumber = videoesNumber;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }


    public int getVideoesGatherId() {
        return videoesGatherId;
    }

    public void setVideoesGatherId(int videoesGatherId) {
        this.videoesGatherId = videoesGatherId;
    }

    public String getCourseVideoesName() {
        return courseVideoesName;
    }

    public void setCourseVideoesName(String courseVideoesName) {
        this.courseVideoesName = courseVideoesName;
    }

    public String getCourseVideoesTime() {
        return courseVideoesTime;
    }

    public void setCourseVideoesTime(String courseVideoesTime) {
        this.courseVideoesTime = courseVideoesTime;
    }

    public Date getCourseVideoesUpTime() {
        return courseVideoesUpTime;
    }

    public void setCourseVideoesUpTime(Date courseVideoesUpTime) {
        this.courseVideoesUpTime = courseVideoesUpTime;
    }

    public String getCourseVideoesResources() {
        return courseVideoesResources;
    }

    public void setCourseVideoesResources(String courseVideoesResources) {
        this.courseVideoesResources = courseVideoesResources;
    }

    public String getCourseFileTitle() {
        return courseFileTitle;
    }

    public void setCourseFileTitle(String courseFileTitle) {
        this.courseFileTitle = courseFileTitle;
    }

    public String getCourseFileResources() {
        return courseFileResources;
    }

    public void setCourseFileResources(String courseFileResources) {
        this.courseFileResources = courseFileResources;
    }
}
