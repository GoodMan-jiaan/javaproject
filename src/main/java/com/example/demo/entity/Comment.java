package com.example.demo.entity;

import java.util.Date;

//用户评论表
public class Comment {
    private int commentId;
    private int userId;
    private int videoesGatherId;
    private String commenttext;
    private Date commentUptime;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVideoesGatherId() {
        return videoesGatherId;
    }

    public void setVideoesGatherId(int videoesGatherId) {
        this.videoesGatherId = videoesGatherId;
    }

    public String getCommenttext() {
        return commenttext;
    }

    public void setCommenttext(String commenttext) {
        this.commenttext = commenttext;
    }

    public Date getCommentUptime() {
        return commentUptime;
    }

    public void setCommentUptime(Date commentUptime) {
        this.commentUptime = commentUptime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", userId=" + userId +
                ", videoesGatherId=" + videoesGatherId +
                ", commenttext='" + commenttext + '\'' +
                ", commentUptime=" + commentUptime +
                '}';
    }
}
