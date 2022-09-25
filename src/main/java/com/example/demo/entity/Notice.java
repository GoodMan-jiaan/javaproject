package com.example.demo.entity;

import java.util.Date;

public class Notice {

    private int noticeId;
    private int adminId;
    private String noticeTitle;
    private String noticeContent;
    private Date noticeUptime;

    @Override
    public String toString() {
        return "Notice{" +
                "noticeId=" + noticeId +
                ", adminId=" + adminId +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", noticeUptime=" + noticeUptime +
                '}';
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Date getNoticeUptime() {
        return noticeUptime;
    }

    public void setNoticeUptime(Date noticeUptime) {
        this.noticeUptime = noticeUptime;
    }
}
