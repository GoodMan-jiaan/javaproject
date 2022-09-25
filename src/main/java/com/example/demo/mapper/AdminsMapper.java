package com.example.demo.mapper;

import com.example.demo.entity.Admins;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AdminsMapper {

    //登录管理员信息
    @Select("select * from admins where adminAccount=#{adminAccount} and adminPassword=#{adminPassword}")
    public Admins loginAdmins(String adminAccount,String adminPassword);

    //根据管理员Id查看管理员信息
    @Select("select * from admins where adminId=#{adminId}")
    public Admins SelectAdminsByAdmins(int adminId);

    //添加管理员
    @Insert("insert into admins(adminAccount,adminPassword) value(#{adminAccount},#{adminPassword})")
    public int InsertAdmins(String adminAccount,String adminPassword);

    //更新管理员
    @Update("update admins set adminName=#{adminName},adminPhone=#{adminPhone} where adminId=#{adminId} ")
    public int updateAdmins(Admins admins);

    //通过管理员Id更新管理员信息
    @Update("update admins set adminName=#{adminName},adminPhone=#{adminPhone},adminAccount=#{adminAccount} where adminId=#{adminId}")
    public int updateAdminsById(int adminId,String adminName,String adminPhone,String adminAccount);

    //根据管理员Id更新管理员密码
    @Update("update admins set adminPassword=#{adminPassword} where adminId=#{adminId}")
    public int updatePasswordById(int adminId,String adminPassword);

    //删除管理员
    @Delete("delete from admins where adminId=#{adminId}")
    public int deleteAdmins(int adminId);
}
