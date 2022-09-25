package com.example.demo.controller;

import com.example.demo.entity.Givelike;
import com.example.demo.entity.PageRequest;
import com.example.demo.entity.Videoesgather;
import com.example.demo.mapper.GivelikeMapper;
import com.example.demo.mapper.VideoesgatherMapper;
import com.example.demo.service.GiveLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "用户点赞控制器")
public class GivelikeController {

    @Autowired
    private GivelikeMapper givelikeMapper;

    @Autowired
    private VideoesgatherMapper videoesgatherMapper;

    @Autowired
    private GiveLikeService giveLikeService;


    //测试使用
    @RequestMapping("/ljatext")
    public boolean ljatext(int videoesGatherId){
        Videoesgather videoesgather=new Videoesgather();
        videoesgather.setVideoesGatherId(videoesGatherId);

        Videoesgather videoesgather1=videoesgatherMapper.selectVideoesgatherById(videoesgather);
        if(videoesgather1!=null){
            videoesgather.setVideoesGatherTotalAttention(videoesgather1.getVideoesGatherTotalAttention()+1);
        }

        int result= videoesgatherMapper.updateAttentionById(videoesgather);
        if(result==1){
            return true;
        }else {
            return false;
        }

    }



    @RequestMapping("/SelectGivelikeByUserId")
    @ApiOperation("根据用户ID与视频集ID查找点赞表，查看用户是否有点赞视频集,当存在点赞是返回true")
    public boolean SelectGivelikeByUserId(int userId,int videoesGatherId){
        Givelike givelike=new Givelike();
        givelike.setUserId(userId);
        givelike.setVideoesGatherId(videoesGatherId);
        Givelike givelike1= givelikeMapper.SelectGivelikeByUserId(givelike);
        if(givelike1==null){
            return false;
        }else {
            return true;
        }
    }

    @RequestMapping("/AddOrDelectGivelike")
    @ApiOperation("删除或添加点赞表，judge为标识，1为添加，0为删除")
    public boolean AddOrDelectGivelike(int userId,int videoesGatherId,int judge){
        Givelike givelike=new Givelike();
        int result=0;
        int result1=0;

        givelike.setUserId(userId);
        givelike.setVideoesGatherId(videoesGatherId);
        if(judge==0){
            //删除点赞信息
            result=givelikeMapper.DeleteGivelike(givelike);
            if(result==1){
                Videoesgather videoesgather=new Videoesgather();
                videoesgather.setVideoesGatherId(videoesGatherId);
                //查看视频集的点赞总数并-1
                Videoesgather videoesgather1=videoesgatherMapper.selectVideoesgatherById(videoesgather);
                videoesgather.setVideoesGatherTotalAttention(videoesgather1.getVideoesGatherTotalAttention()-1);
                //更改视频集信息
                result1=videoesgatherMapper.updateAttentionById(videoesgather);
            }
        }else if (judge==1){
            //添加点赞信息
            result=givelikeMapper.InsertGivelike(givelike);
            if(result==1){
                Videoesgather videoesgather=new Videoesgather();
                videoesgather.setVideoesGatherId(videoesGatherId);
                //查看视频集的点赞总数并+1
                Videoesgather videoesgather1=videoesgatherMapper.selectVideoesgatherById(videoesgather);
                videoesgather.setVideoesGatherTotalAttention(videoesgather1.getVideoesGatherTotalAttention()+1);
                //更改视频集信息
                result1=videoesgatherMapper.updateAttentionById(videoesgather);
            }
        }
        if (result1==1){
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("/SelectGiveLikeById")
    @ApiOperation("通过分布查询用户ID查看点赞信息")
    public Object SelectGiveLikeById(int pageNum,int pageSize,int userId){
        PageRequest pageRequest=new PageRequest();
        pageRequest.setPageNum(pageNum);
        pageRequest.setPageSize(pageSize);

        return giveLikeService.SelectGiveLikeById(pageRequest,userId);
    }

    @RequestMapping("/getPageGiveLike")
    @ApiOperation("分页查询点赞信息")
    public Object getPageGiveLike(int pageNum,int pageSize){
        PageRequest pageRequest=new PageRequest();
        pageRequest.setPageNum(pageNum);
        pageRequest.setPageSize(pageSize);
        return giveLikeService.getPageGiveLike(pageRequest);
    }
}
