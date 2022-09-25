package com.example.demo.entity;

import java.util.Date;

public class Advise {
    private int adviseId;
    private String phone;
    private String content;
    private String isread;
    private Date uptime;

    @Override
    public String toString() {
        return "Advise{" +
                "adviseId=" + adviseId +
                ", phone='" + phone + '\'' +
                ", content='" + content + '\'' +
                ", isread='" + isread + '\'' +
                ", uptime=" + uptime +
                '}';
    }

    public String getIsread() {
        return isread;
    }

    public void setIsread(String isread) {
        this.isread = isread;
    }

    public int getAdviseId() {
        return adviseId;
    }

    public void setAdviseId(int adviseId) {
        this.adviseId = adviseId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }
}
