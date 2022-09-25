package com.example.demo.service.impl;

import com.example.demo.entity.Attention;
import com.example.demo.entity.PageRequest;
import com.example.demo.entity.PageResult;
import com.example.demo.mapper.AttentionMapper;
import com.example.demo.service.AttentionService;
import com.example.demo.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AttentionServiceImpl implements AttentionService {


    @Autowired
    private AttentionMapper attentionMapper;

    @Override
    public PageResult getPageAttention(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest,getPageAttention1(pageRequest));
    }

    private PageInfo<Attention> getPageAttention1(PageRequest pageRequest){
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Attention> sysMenus =attentionMapper.getPageAttention();
        return new PageInfo<Attention>(sysMenus);
    }

    @Override
    public PageResult getPageAttentionById(PageRequest pageRequest, int userId) {
        return PageUtils.getPageResult(pageRequest,getPageAttentionById1(pageRequest,userId));
    }
    private PageInfo<Attention> getPageAttentionById1(PageRequest pageRequest,int userId){
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Attention> sysMenus =attentionMapper.getPageAttentionById(userId);
        return new PageInfo<Attention>(sysMenus);
    }
}
