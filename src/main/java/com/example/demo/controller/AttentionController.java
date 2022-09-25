package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.mapper.AttentionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.VideoesgatherMapper;
import com.example.demo.service.AttentionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//视频操作
@RestController
@Api(tags = "用户关注控制器")
public class AttentionController {

    @Autowired
    private AttentionMapper attentionMapper;

    @Autowired
    private VideoesgatherMapper videoesgatherMapper;

    @Autowired
    private AttentionService attentionService;

    @Autowired
    private UserMapper userMapper;


    @RequestMapping("/getPageAttention")
    @ApiOperation("分页查询关注信息")
    public Object getPageAttention(int pageNum,int pageSize){
        PageRequest pageRequest=new PageRequest();
        pageRequest.setPageNum(pageNum);
        pageRequest.setPageSize(pageSize);
        return attentionService.getPageAttention(pageRequest);
    }


    @RequestMapping("/SelectAttentionByUserId")
    @ApiOperation("根据用户ID与被关注用户ID查找点赞表，查看用户是否已经关注,当存在关注是返回true")
    public boolean SelectAttentionByUserId(int followUser,int beFollowUser){
        Attention attention=new Attention();
        attention.setFollowUser(followUser);
        attention.setBeFollowUser(beFollowUser);

        Attention attention1= attentionMapper.SelectAttentionByUserId(attention);
        if(attention1==null){
            return false;
        }else {
            return true;
        }
    }

    @RequestMapping("/AddOrDelectAttention")
    @ApiOperation("删除或添加关注表，judge为标识，1为添加，0为删除")
    public boolean AddOrDelectAttention(int followUser,int beFollowUser,int judge){

        Attention attention=new Attention();
        int result=0;
        int result1=0;
        int result2=0;
        attention.setFollowUser(followUser);
        attention.setBeFollowUser(beFollowUser);
        if(judge==0){
            result=attentionMapper.DeleteAttention(attention);
            if(result==1){
                //操作关注人的信息表
                User user=new User();
                user.setUserId(followUser);
                User user1=userMapper.SelectUserById(user);
                user.setUserFollowTotal(user1.getUserFollowTotal()-1);
                result1=userMapper.UpdateUserFollowTotal(user);
                //操作被关注人的信息表
                User user2=new User();
                user2.setUserId(beFollowUser);
                User user3=userMapper.SelectUserById(user2);
                user2.setUserBeFollowTotal(user3.getUserBeFollowTotal()-1);
                result2=userMapper.UpdateUserBeFollowTotal(user2);
            }
        }else if (judge==1){
            result=attentionMapper.InsertAttention(attention);
            if(result==1){
                //操作关注人的信息表
                User user=new User();
                user.setUserId(followUser);
                User user1=userMapper.SelectUserById(user);
                user.setUserFollowTotal(user1.getUserFollowTotal()+1);
                result1=userMapper.UpdateUserFollowTotal(user);
                //操作被关注人的信息表
                User user2=new User();
                user2.setUserId(beFollowUser);
                User user3=userMapper.SelectUserById(user2);
                user2.setUserBeFollowTotal(user3.getUserBeFollowTotal()+1);
                result2=userMapper.UpdateUserBeFollowTotal(user2);
            }
        }
        if (result1==1&&result2==1){
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("/getPageAttentionById")
    @ApiOperation("根据用户Id分页查询")
    public Object getPageAttentionById(int pageNum,int pageSize,int userId){
        PageRequest pageRequest=new PageRequest();
        pageRequest.setPageNum(pageNum);
        pageRequest.setPageSize(pageSize);
        return attentionService.getPageAttentionById(pageRequest,userId);
    }

}
