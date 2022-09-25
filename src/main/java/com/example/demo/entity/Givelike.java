package com.example.demo.entity;
//点赞信息表
public class Givelike {
    private int giveLikeId;
    private int videoesGatherId;
    private int userId;

    @Override
    public String toString() {
        return "Givelike{" +
                "giveLikeId=" + giveLikeId +
                ", videoesGatherId=" + videoesGatherId +
                ", userId=" + userId +
                '}';
    }

    public int getGiveLikeId() {
        return giveLikeId;
    }

    public void setGiveLikeId(int giveLikeId) {
        this.giveLikeId = giveLikeId;
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
}
