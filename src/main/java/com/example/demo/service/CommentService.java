package com.example.demo.service;


import com.example.demo.entity.PageRequest;
import com.example.demo.entity.PageResult;

public interface CommentService {

    //通过视频集Id分页查找评论，
    PageResult getCommentById(PageRequest pageRequest,int videoesGatherId);

    //通过用户Id查看被评论的信息
    PageResult SelectCommentByTwoTable(PageRequest pageRequest,int userId);

    //通过用户Id查看用户评论的信息
    PageResult SelectCommentByUserId(PageRequest pageRequest,int userId);

    //分页查询评论
    PageResult getPageComment(PageRequest pageRequest);
}
