package com.example.demo.controller;

import com.example.demo.entity.Collect;
import com.example.demo.entity.PageRequest;
import com.example.demo.entity.Videoesgather;
import com.example.demo.mapper.CollectMapper;
import com.example.demo.mapper.VideoesgatherMapper;
import com.example.demo.service.CollectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//视频操作
@RestController
@Api(tags = "用户收藏控制器")
public class CollectController {

    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private VideoesgatherMapper videoesgatherMapper;

    @Autowired
    private CollectService collectService;

    @RequestMapping("/getPageCollect")
    @ApiOperation("分页查询收藏表")
    public Object getPageCollect(int pageNum,int pageSize){
        PageRequest pageRequest=new PageRequest();
        pageRequest.setPageNum(pageNum);
        pageRequest.setPageSize(pageSize);

        return collectService.getPageCollect(pageRequest);
    }

    @RequestMapping("/SelectCollectByUserId")
    @ApiOperation("根据用户ID与视频集ID查找点赞表，查看用户是否有点赞视频集,当存在点赞是返回true")
    public boolean SelectCollectByUserId(int userId,int videoesGatherId){
        Collect collect=new Collect();
        collect.setUserId(userId);
        collect.setVideoesGatherId(videoesGatherId);
        Collect collect1= collectMapper.SelectCollectByUserId(collect);
        if(collect1==null){
            return false;
        }else {
            return true;
        }
    }

    @RequestMapping("/AddOrDelectCollect")
    @ApiOperation("删除或添加收藏表，judge为标识，1为添加，0为删除")
    public boolean AddOrDelectCollect(int userId,int videoesGatherId,int judge){
        Collect collect=new Collect();
        int result=0;
        int result1=0;

        collect.setUserId(userId);
        collect.setVideoesGatherId(videoesGatherId);
        if(judge==0){
            //删除收藏信息
            result=collectMapper.DeleteCollect(collect);
            if(result==1){
                Videoesgather videoesgather=new Videoesgather();
                videoesgather.setVideoesGatherId(videoesGatherId);
                //查看视频集的收藏总数并-1
                Videoesgather videoesgather1=videoesgatherMapper.selectVideoesgatherById(videoesgather);
                videoesgather.setVideoesGatherTotalCollect(videoesgather1.getVideoesGatherTotalCollect()-1);
                //更改视频集信息
                result1=videoesgatherMapper.updateCollectById(videoesgather);
            }
            //添加收藏信息
        }else if (judge==1){
            result=collectMapper.InsertCollect(collect);
            if(result==1){
                Videoesgather videoesgather=new Videoesgather();
                videoesgather.setVideoesGatherId(videoesGatherId);
                //查看视频集的收藏总数并+1
                Videoesgather videoesgather1=videoesgatherMapper.selectVideoesgatherById(videoesgather);
                videoesgather.setVideoesGatherTotalCollect(videoesgather1.getVideoesGatherTotalCollect()+1);
                //更改视频集信息
                result1=videoesgatherMapper.updateCollectById(videoesgather);
            }
        }
        if (result1==1){
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("/SelectCollect")
    @ApiOperation("通过用户ID查看收藏信息,分页查询")
    public Object SelectCollect(int pageNum,int pageSize,int userId){
        PageRequest pageRequest=new PageRequest();
        pageRequest.setPageNum(pageNum);
        pageRequest.setPageSize(pageSize);

        return collectService.SelectCollect(pageRequest,userId);
    }
}
