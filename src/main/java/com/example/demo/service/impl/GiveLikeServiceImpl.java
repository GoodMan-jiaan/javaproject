package com.example.demo.service.impl;

import com.example.demo.entity.Givelike;
import com.example.demo.entity.PageRequest;
import com.example.demo.entity.PageResult;
import com.example.demo.mapper.GivelikeMapper;
import com.example.demo.service.GiveLikeService;
import com.example.demo.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiveLikeServiceImpl implements GiveLikeService {
    @Autowired
    private GivelikeMapper givelikeMapper;

    @Override
    public PageResult SelectGiveLikeById(PageRequest pageRequest, int userId) {
        return PageUtils.getPageResult(pageRequest,SelectGiveLikeById1(pageRequest, userId));
    }

    private PageInfo<Givelike> SelectGiveLikeById1(PageRequest pageRequest, int userId){
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Givelike> sysMenus =givelikeMapper.SelectGiveLikeById(userId);
        return new PageInfo<Givelike>(sysMenus);
    }

    @Override
    public PageResult getPageGiveLike(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest,getPageGiveLike1(pageRequest));
    }

    private PageInfo<Givelike> getPageGiveLike1(PageRequest pageRequest){
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Givelike> sysMenus =givelikeMapper.getPageGiveLike();
        return new PageInfo<Givelike>(sysMenus);
    }
}
