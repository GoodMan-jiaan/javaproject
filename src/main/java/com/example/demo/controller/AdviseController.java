package com.example.demo.controller;

import com.example.demo.entity.Advise;
import com.example.demo.entity.PageRequest;
import com.example.demo.mapper.AdviseMapper;
import com.example.demo.service.AdviseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Api(tags = "建议关注器")
public class AdviseController {

    @Autowired
    private AdviseMapper adviseMapper;

    @Autowired
    private AdviseService adviseService;

    @RequestMapping("/InsertAdvise")
    @ApiOperation("添加意见信息")
    public boolean InsertAdvise(String phone,String content){
        Advise advise=new Advise();
        advise.setPhone(phone);
        advise.setContent(content);
        advise.setIsread("0");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        advise.setUptime(date);
        int result=adviseMapper.InsertAdvise(advise);
        if(result==1){
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("/getPageAdvise")
    @ApiOperation("分页查询建议表")
    public Object getPageAdvise(int pageNum,int pageSize,int just){
        PageRequest pageRequest=new PageRequest();
        pageRequest.setPageNum(pageNum);
        pageRequest.setPageSize(pageSize);

        return adviseService.getPageAdvise(pageRequest,just);
    }

    @RequestMapping("/getPageAdviseByPhone")
    @ApiOperation("根据手机分页查询邮件，just等于0时为未读，1为已读")
    public Object getPageAdviseByPhone(int pageNum,int pageSize,int just,String phone){
        PageRequest pageRequest=new PageRequest();
        pageRequest.setPageNum(pageNum);
        pageRequest.setPageSize(pageSize);
        return adviseService.getPageAdviseByPhone(pageRequest,phone,just);
    }

    @RequestMapping("/updateIsreadByadminId")
    @ApiOperation("根据建议Id更改为已读")
    public boolean updateIsreadByadminId(int adviseId){
        int result=adviseMapper.updateIsreadByadminId("1",adviseId);
        if(result==1){
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("/deleteAdvise")
    @ApiOperation("根据建议Id删除建议")
    public boolean deleteAdmins(int adviseId){
        int result=adviseMapper.deleteAdvise(adviseId);
        if(result==1){
            return true;
        }else {
            return false;
        }
    }
}
