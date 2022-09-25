package com.example.demo.service;

import com.example.demo.entity.HistoricRecords;
import com.example.demo.entity.PageRequest;
import com.example.demo.entity.PageResult;

public interface HistoricRecordsService {
    //添加历史记录信息
    public boolean InsertHistoric(HistoricRecords historicRecords);

    //通过视频集Id分页查找评论，
    PageResult SelectHistoricByUserId(PageRequest pageRequest, int userId);

    //分页查询历史记录
    PageResult getPageHistoric(PageRequest pageRequest);
}
