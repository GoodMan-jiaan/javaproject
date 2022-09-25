package com.example.demo.entity;
//用户收藏表
public class Collect {
    private int collectId;
    private int videoesGatherId;
    private int userId;

    public int getCollectId() {
        return collectId;
    }

    public void setCollectId(int collectId) {
        this.collectId = collectId;
    }

    public int getVideoesGatherId() {
        return videoesGatherId;
    }

    public void setVideoesGatherId(int videoesGatherId) {
        this.videoesGatherId = videoesGatherId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Collect{" +
                "collectId=" + collectId +
                ", videoesGatherId=" + videoesGatherId +
                ", userId=" + userId +
                '}';
    }
}
