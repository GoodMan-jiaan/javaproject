package com.example.demo.service.impl;

import com.example.demo.entity.Comment;
import com.example.demo.entity.PageRequest;
import com.example.demo.entity.PageResult;

import com.example.demo.mapper.CommentMapper;
import com.example.demo.service.CommentService;
import com.example.demo.utils.PageUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public PageResult getCommentById(PageRequest pageRequest, int videoesGatherId) {
        return PageUtils.getPageResult(pageRequest,getCommentById1(pageRequest, videoesGatherId));
    }

    private PageInfo<Comment> getCommentById1(PageRequest pageRequest,int videoesGatherId){
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();

        Comment comment=new Comment();
        comment.setVideoesGatherId(videoesGatherId);
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> sysMenus =commentMapper.SelectCommentByVideoID(comment);
        return new PageInfo<Comment>(sysMenus);
    }

    @Override
    public PageResult SelectCommentByTwoTable(PageRequest pageRequest, int userId) {
        return PageUtils.getPageResult(pageRequest,SelectCommentByTwoTable1(pageRequest,userId));
    }

    private PageInfo<Comment> SelectCommentByTwoTable1(PageRequest pageRequest,int userId){
        int pageNum =pageRequest.getPageNum();
        int pageSize=pageRequest.getPageSize();

        PageHelper.startPage(pageNum,pageSize);
        List<Comment> sysMenus =commentMapper.SelectCommentByTwoTable(userId);
        return new PageInfo<Comment>(sysMenus);

    }

    @Override
    public PageResult SelectCommentByUserId(PageRequest pageRequest, int userId) {
        return PageUtils.getPageResult(pageRequest,SelectCommentByUserId1(pageRequest,userId));
    }

    private PageInfo<Comment> SelectCommentByUserId1(PageRequest pageRequest,int userId){
        int pageNum = pageRequest.getPageNum();
        int pageSize=pageRequest.getPageSize();

        PageHelper.startPage(pageNum,pageSize);
        List<Comment> sysMenus=commentMapper.SelectCommentByUserId(userId);
        return new PageInfo<Comment>(sysMenus);
    }

    @Override
    public PageResult getPageComment(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest,getPageComment1(pageRequest));
    }

    private PageInfo<Comment> getPageComment1(PageRequest pageRequest){
        int pageNum = pageRequest.getPageNum();
        int pageSize=pageRequest.getPageSize();

        PageHelper.startPage(pageNum,pageSize);
        List<Comment> sysMenus=commentMapper.getPageComment();
        return new PageInfo<Comment>(sysMenus);
    }
}
