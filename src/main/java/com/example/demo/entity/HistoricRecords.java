package com.example.demo.entity;

import java.util.Date;
//历史记录信息表
public class HistoricRecords {
    private int historicrecordsId;
    private int userId;
    private int videoesGatherId;
    private Date hestoricRecordTime;

    @Override
    public String toString() {
        return "HistoricRecords{" +
                "historicrecordsId=" + historicrecordsId +
                ", userId=" + userId +
                ", videoesGatherId=" + videoesGatherId +
                ", hestoricRecordTime=" + hestoricRecordTime +
                '}';
    }

    public int getHistoricrecordsId() {
        return historicrecordsId;
    }

    public void setHistoricrecordsId(int historicrecordsId) {
        this.historicrecordsId = historicrecordsId;
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

    public Date getHestoricRecordTime() {
        return hestoricRecordTime;
    }

    public void setHestoricRecordTime(Date hestoricRecordTime) {
        this.hestoricRecordTime = hestoricRecordTime;
    }
}
