package com.example.demo.controller;

import com.example.demo.entity.Comment;
import com.example.demo.entity.PageRequest;
import com.example.demo.entity.Videoesgather;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.VideoesgatherMapper;
import com.example.demo.service.CommentService;
import com.example.demo.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "用户评论控制器")
public class CommentController {
    @Autowired
    private CommentMapper commentMapper;
    
    @Autowired 
    private VideoesgatherMapper videoesgatherMapper;

    @Autowired
    private CommentService commentService;

    @RequestMapping("/SelectCommentByVideoID")
    @ApiOperation("通过视频集ID查找评论信息")
    public Object SelectCommentByVideoID(int pageNum,int pageSize,int videoesGatherId){
        PageRequest pageRequest=new PageRequest();
        pageRequest.setPageNum(pageNum);
        pageRequest.setPageSize(pageSize);

        return commentService.getCommentById(pageRequest,videoesGatherId);
    }

//    public List<Comment> SelectCommentByVideoID(int videoesGatherId){
//        Comment comment=new Comment();
//        comment.setVideoesGatherId(videoesGatherId);
//        List<Comment> commentList=commentMapper.SelectCommentByVideoID(comment);
//        return commentList;
//    }

    @RequestMapping("/insertComment")
    @ApiOperation("通过视频集Id与用户ID发表评论")
    public boolean insertComment(int videoesGatherId,int userId,String commenttext){
        int result=0;

        Comment comment=new Comment();
        comment.setVideoesGatherId(videoesGatherId);
        comment.setUserId(userId);
        comment.setCommenttext(commenttext);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        comment.setCommentUptime(date);
        //添加信息到评论表
        result=commentMapper.insertComment(comment);
        if(result==1){
            //在视频集信息表中的中评论数+1
            Videoesgather videoesgather=new Videoesgather();
            videoesgather.setVideoesGatherId(videoesGatherId);
            Videoesgather videoesgather1=videoesgatherMapper.selectVideoesgatherById(videoesgather);
            videoesgather.setVideoesGatherTotalComment(videoesgather1.getVideoesGatherTotalComment()+1);
            int result1=videoesgatherMapper.updateCommentById(videoesgather);
            if(result1==1){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    @RequestMapping("/deleteCommentByID")
    @ApiOperation("通过视频集ID与用户ID删除评论")
    public boolean deleteCommentByID(int commentId,int videoesGatherId){
        int result=0;
        Comment comment=new Comment();
        comment.setCommentId(commentId);
        result=commentMapper.deleteCommentByID(comment);
        comment.setVideoesGatherId(videoesGatherId);
        if(result==1){
            //在视频集信息表中的中评论数+1
            Videoesgather videoesgather=new Videoesgather();
            videoesgather.setVideoesGatherId(videoesGatherId);
            Videoesgather videoesgather1=videoesgatherMapper.selectVideoesgatherById(videoesgather);
            videoesgather.setVideoesGatherTotalComment(videoesgather1.getVideoesGatherTotalComment()-1);
            int result1=videoesgatherMapper.updateCommentById(videoesgather);
            if(result1==1){
                return true;
            }else {
                return false;
            }

        }else {
            return false;
        }
    }

    @RequestMapping("/SelectCommentByTwoTable")
    @ApiOperation("通过用户Id查看被评论的信息")
    public Object SelectCommentByTwoTable(int pageNum,int pageSize,int userId){
        PageRequest pageRequest=new PageRequest();
        pageRequest.setPageNum(pageNum);
        pageRequest.setPageSize(pageSize);
        return commentService.SelectCommentByTwoTable(pageRequest,userId);
    }

    @RequestMapping("/SelectCommentByUserId")
    @ApiOperation("通过用户Id查看评论信息")
    public Object SelectCommentByUserId(int pageNum,int pageSize,int userId){
        PageRequest pageRequest=new PageRequest();
        pageRequest.setPageSize(pageSize);
        pageRequest.setPageNum(pageNum);
        return commentService.SelectCommentByUserId(pageRequest,userId);
    }

    @RequestMapping("/getPageComment")
    @ApiOperation("通过用户Id查看评论信息")
    public Object getPageComment(int pageNum,int pageSize){
        PageRequest pageRequest=new PageRequest();
        pageRequest.setPageSize(pageSize);
        pageRequest.setPageNum(pageNum);
        return commentService.getPageComment(pageRequest);
    }

    @RequestMapping("/updateCommentText")
    @ApiOperation("更改评论内容")
    public boolean updateCommentText(String commenttext,int commentId){
        int result=commentMapper.updateCommentText(commentId,commenttext);
        if(result==1){
            return true;
        }else {
            return false;
        }
    }

}
