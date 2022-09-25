package com.example.demo.service.impl;

import com.example.demo.entity.Comment;
import com.example.demo.entity.HistoricRecords;
import com.example.demo.entity.PageRequest;
import com.example.demo.entity.PageResult;
import com.example.demo.mapper.HistoricRecordsMapper;
import com.example.demo.service.HistoricRecordsService;
import com.example.demo.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class HistoricRecordsServiceImpl implements HistoricRecordsService {

    @Autowired
    private HistoricRecordsMapper historicRecordsMapper;

    @Override
    public boolean InsertHistoric(HistoricRecords historicRecords) {
        //判断表里是否含有记录
        HistoricRecords historicRecords1=new HistoricRecords();
        historicRecords1=historicRecordsMapper.SelectHistoricByUserIdAndVi(historicRecords);
        //获取当前时间
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        historicRecords.setHestoricRecordTime(date);
        if(historicRecords1!=null){
           int result=historicRecordsMapper.UpdateHistoric(historicRecords);
           if(result==1){
               return true;
           }else {
               return false;
           }
        }else {
            int result=historicRecordsMapper.InsertHistoric(historicRecords);
            if(result==1){
                return true;
            }else {
                return false;
            }
        }
    }

    //根据用户Id进行分页逆序查询
    @Override
    public PageResult SelectHistoricByUserId(PageRequest pageRequest, int userId) {
        return PageUtils.getPageResult(pageRequest,SelectHistoricByUserId1(pageRequest,userId));
    }


    private PageInfo<HistoricRecords> SelectHistoricByUserId1(PageRequest pageRequest, int userId){

        int pageNum =pageRequest.getPageNum();
        int pageSize=pageRequest.getPageSize();

        PageHelper.startPage(pageNum,pageSize);
        List<HistoricRecords> sysMenus =historicRecordsMapper.SelectHistoricByUserId(userId);
        return new PageInfo<HistoricRecords>(sysMenus);

    }

    @Override
    public PageResult getPageHistoric(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest,getPageHistoric1(pageRequest));
    }

    private PageInfo<HistoricRecords> getPageHistoric1(PageRequest pageRequest){

        int pageNum =pageRequest.getPageNum();
        int pageSize=pageRequest.getPageSize();

        PageHelper.startPage(pageNum,pageSize);
        List<HistoricRecords> sysMenus =historicRecordsMapper.getPageHistoric();
        return new PageInfo<HistoricRecords>(sysMenus);

    }
}
