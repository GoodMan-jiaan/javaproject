package com.example.demo.entity;

import java.util.Date;

//视频合集
public class Videoesgather {
    private int videoesGatherId;
    private int userId;
    private String videoesGatherKind;
    private String videoesGatherName;
    private String videoesGatherAuthorName;
    private String videoesGatherIntroduce;
    private String videoesGatherImage;
    private String videoesGatherOrgan;
    private int videoesGatherTotalAttention;
    private int videoesGatherTotalCollect;
    private Date videoesGatherUptime;
    private int videoesGatherTotalComment;


    @Override
    public String toString() {
        return "Videoesgather{" +
                "videoesGatherId=" + videoesGatherId +
                ", userId=" + userId +
                ", videoesGatherKind='" + videoesGatherKind + '\'' +
                ", videoesGatherName='" + videoesGatherName + '\'' +
                ", videoesGatherAuthorName='" + videoesGatherAuthorName + '\'' +
                ", videoesGatherIntroduce='" + videoesGatherIntroduce + '\'' +
                ", videoesGatherImage='" + videoesGatherImage + '\'' +
                ", videoesGatherOrgan='" + videoesGatherOrgan + '\'' +
                ", videoesGatherTotalAttention=" + videoesGatherTotalAttention +
                ", videoesGatherTotalCollect=" + videoesGatherTotalCollect +
                ", videoesGatherUptime=" + videoesGatherUptime +
                ", videoesGatherTotalComment=" + videoesGatherTotalComment +
                '}';
    }

    public int getVideoesGatherTotalComment() {
        return videoesGatherTotalComment;
    }

    public void setVideoesGatherTotalComment(int videoesGatherTotalComment) {
        this.videoesGatherTotalComment = videoesGatherTotalComment;
    }

    public Date getVideoesGatherUptime() {
        return videoesGatherUptime;
    }

    public void setVideoesGatherUptime(Date videoesGatherUptime) {
        this.videoesGatherUptime = videoesGatherUptime;
    }

    public int getVideoesGatherTotalAttention() {
        return videoesGatherTotalAttention;
    }

    public void setVideoesGatherTotalAttention(int videoesGatherTotalAttention) {
        this.videoesGatherTotalAttention = videoesGatherTotalAttention;
    }

    public int getVideoesGatherTotalCollect() {
        return videoesGatherTotalCollect;
    }

    public void setVideoesGatherTotalCollect(int videoesGatherTotalCollect) {
        this.videoesGatherTotalCollect = videoesGatherTotalCollect;
    }

    public String getVideoesGatherOrgan() {
        return videoesGatherOrgan;
    }

    public void setVideoesGatherOrgan(String videoesGatherOrgan) {
        this.videoesGatherOrgan = videoesGatherOrgan;
    }

    public String getVideoesGatherImage() {
        return videoesGatherImage;
    }

    public void setVideoesGatherImage(String videoesGatherImage) {
        this.videoesGatherImage = videoesGatherImage;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getVideoesGatherIntroduce() {
        return videoesGatherIntroduce;
    }

    public void setVideoesGatherIntroduce(String videoesGatherIntroduce) {
        this.videoesGatherIntroduce = videoesGatherIntroduce;
    }

    public int getVideoesGatherId() {
        return videoesGatherId;
    }

    public void setVideoesGatherId(int videoesGatherId) {
        this.videoesGatherId = videoesGatherId;
    }

    public String getVideoesGatherKind() {
        return videoesGatherKind;
    }

    public void setVideoesGatherKind(String videoesGatherKind) {
        this.videoesGatherKind = videoesGatherKind;
    }

    public String getVideoesGatherName() {
        return videoesGatherName;
    }

    public void setVideoesGatherName(String videoesGatherName) {
        this.videoesGatherName = videoesGatherName;
    }

    public String getVideoesGatherAuthorName() {
        return videoesGatherAuthorName;
    }

    public void setVideoesGatherAuthorName(String videoesGatherAuthorName) {
        this.videoesGatherAuthorName = videoesGatherAuthorName;
    }
}
