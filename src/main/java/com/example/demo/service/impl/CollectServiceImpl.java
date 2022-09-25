package com.example.demo.service.impl;

import com.example.demo.entity.Collect;
import com.example.demo.entity.Comment;
import com.example.demo.entity.PageRequest;
import com.example.demo.entity.PageResult;
import com.example.demo.mapper.CollectMapper;
import com.example.demo.service.CollectService;
import com.example.demo.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public PageResult SelectCollect(PageRequest pageRequest, int userId) {
        return PageUtils.getPageResult(pageRequest,SelectCollect1(pageRequest, userId));
    }

    private PageInfo<Collect> SelectCollect1(PageRequest pageRequest, int userId){
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();

        Collect collect=new Collect();
        collect.setUserId(userId);

        PageHelper.startPage(pageNum, pageSize);
        List<Collect> sysMenus =collectMapper.SelectCollect(collect);
        return new PageInfo<Collect>(sysMenus);
    }

    @Override
    public PageResult getPageCollect(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest,getPageCollect1(pageRequest));
    }

    private PageInfo<Collect> getPageCollect1(PageRequest pageRequest){
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();


        PageHelper.startPage(pageNum, pageSize);
        List<Collect> sysMenus =collectMapper.getPageCollect();
        return new PageInfo<Collect>(sysMenus);
    }

}
