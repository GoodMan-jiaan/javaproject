package com.example.demo.controller;

import com.example.demo.entity.HistoricRecords;
import com.example.demo.entity.PageRequest;
import com.example.demo.mapper.HistoricRecordsMapper;
import com.example.demo.service.HistoricRecordsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "用户历史记录控制器")
public class HistoricRecordsController {

    @Autowired
    private HistoricRecordsService historicRecordsService;

    @Autowired
    private HistoricRecordsMapper historicRecordsMapper;

    @RequestMapping("/getPageHistoric")
    @ApiOperation("分页查询历史记录")
    public Object getPageHistoric(int pageNum,int pageSize){
        PageRequest pageRequest=new PageRequest();
        pageRequest.setPageSize(pageSize);
        pageRequest.setPageNum(pageNum);
        return historicRecordsService.getPageHistoric(pageRequest);
    }


    @RequestMapping("/InsertHistoric")
    @ApiOperation("添加历史记录，成功返回true")
    public boolean InsertHistoric(int userId,int videoesGatherId){
        HistoricRecords historicRecords=new HistoricRecords();
        historicRecords.setUserId(userId);
        historicRecords.setVideoesGatherId(videoesGatherId);
        return historicRecordsService.InsertHistoric(historicRecords);
    }

    @RequestMapping("/DeleteHistoric")
    @ApiOperation("根据用户Id与视频集Id删除信息")
    public boolean DeleteHistoric(int userId,int videoesGatherId){
        HistoricRecords historicRecords=new HistoricRecords();
        historicRecords.setUserId(userId);
        historicRecords.setVideoesGatherId(videoesGatherId);
        int result=historicRecordsMapper.DeleteHistoric(historicRecords);
        if(result==1){
            return true;
        }else {
            return false;
        }
    }


    @RequestMapping("/SelectHistoricByUserId")
    @ApiOperation("根据用户Id进行分页逆序查询")
    public Object SelectHistoricByUserId(int pageNum,int pageSize,int userId){
        PageRequest pageRequest=new PageRequest();
        pageRequest.setPageSize(pageSize);
        pageRequest.setPageNum(pageNum);
        return historicRecordsService.SelectHistoricByUserId(pageRequest,userId);
    }
}
