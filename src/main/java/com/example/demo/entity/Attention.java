package com.example.demo.entity;
//用户关注表
public class Attention {
    private int attentionId;
    private int followUser;
    private int beFollowUser;

    public int getAttentionId() {
        return attentionId;
    }

    public void setAttentionId(int attentionId) {
        this.attentionId = attentionId;
    }

    public int getFollowUser() {
        return followUser;
    }

    public void setFollowUser(int followUser) {
        this.followUser = followUser;
    }

    public int getBeFollowUser() {
        return beFollowUser;
    }

    public void setBeFollowUser(int beFollowUser) {
        this.beFollowUser = beFollowUser;
    }

    @Override
    public String toString() {
        return "AttentionService{" +
                "attentionId=" + attentionId +
                ", followUser=" + followUser +
                ", beFollowUser=" + beFollowUser +
                '}';
    }
}
