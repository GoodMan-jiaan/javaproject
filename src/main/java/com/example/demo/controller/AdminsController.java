package com.example.demo.controller;

import com.example.demo.entity.Admins;
import com.example.demo.mapper.AdminsMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "管理员控制器")
public class AdminsController {
    @Autowired
    private AdminsMapper adminsMapper;

    @RequestMapping("/loginAdmins")
    @ApiOperation("管理员登录")
    public Admins loginAdmins(String adminAccount,String adminPassword){
        Admins admins=new Admins();
        if(adminAccount!=null&&adminPassword!=null){
            admins=adminsMapper.loginAdmins(adminAccount,adminPassword);
        }

        if(admins==null){
            Admins admins1=new Admins();
            admins1.setAdminId(0);
            return admins1;
        }else {
            return admins;
        }

    }

    @RequestMapping("/InsertAdmins")
    @ApiOperation("添加管理员信息")
    public boolean InsertAdmins(String adminAccount,String adminPassword){
        int result=adminsMapper.InsertAdmins(adminAccount,adminPassword);
        if(result==1){
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("/updateAdmins")
    @ApiOperation("更改管理员信息")
    public boolean updateAdmins(int adminId,String adminName,String adminPhone){
        Admins admins=new Admins();
        admins.setAdminId(adminId);
        admins.setAdminName(adminName);
        admins.setAdminPhone(adminPhone);
        int result=adminsMapper.updateAdmins(admins);
        if(result==1){
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("/deleteAdmins")
    @ApiOperation("删除管理员信息")
    public boolean deleteAdmins(int adminId){
        int result=adminsMapper.deleteAdmins(adminId);
        if(result==1){
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("/SelectAdminsByAdmins")
    @ApiOperation("根据管理员Id查看管理员信息")
    public Admins SelectAdminsByAdmins(int adminsId){
        Admins admins=adminsMapper.SelectAdminsByAdmins(adminsId);
        return admins;
    }

    @RequestMapping("/updateAdminsById")
    @ApiOperation("根据管理员Id更改管理员信息")
    public boolean updateAdminsById(int adminsId,String adminsPhone,String adminsName,String adminAccount){
        int result=adminsMapper.updateAdminsById(adminsId,adminsPhone,adminsName,adminAccount);
        if(result==1){
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("/updateAdminsPasswordById")
    @ApiOperation("根据管理员Id更改密码")
    public boolean updateAdminsPasswordById(int adminsId,String adminPassword){
        int result=adminsMapper.updatePasswordById(adminsId,adminPassword);
        if(result==1){
            return true;
        }else {
            return false;
        }
    }

}
