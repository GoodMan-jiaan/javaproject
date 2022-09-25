package com.example.demo.service.impl;

import com.example.demo.entity.Advise;
import com.example.demo.entity.PageRequest;
import com.example.demo.entity.PageResult;
import com.example.demo.mapper.AdviseMapper;
import com.example.demo.service.AdviseService;
import com.example.demo.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdviseServiceImpl implements AdviseService {

    @Autowired
    private AdviseMapper adviseMapper;

    @Override
    public PageResult getPageAdvise(PageRequest pageRequest,int just) {
        return PageUtils.getPageResult(pageRequest,getPageAdvise1(pageRequest,just));
    }


    private PageInfo<Advise> getPageAdvise1(PageRequest pageRequest,int just){
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        if(just==0){
            List<Advise> sysMenus =adviseMapper.getPageAdvise("0");
            return new PageInfo<Advise>(sysMenus);
        }else{
            List<Advise> sysMenus =adviseMapper.getPageAdvise("1");
            return new PageInfo<Advise>(sysMenus);
        }
    }

    @Override
    public PageResult getPageAdviseByPhone(PageRequest pageRequest, String phone, int just) {
        return PageUtils.getPageResult(pageRequest,getPageAdviseByPhone1(pageRequest,phone,just));
    }

    private PageInfo<Advise> getPageAdviseByPhone1(PageRequest pageRequest,String phone,int just){
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        if(just==0){
            List<Advise> sysMenus =adviseMapper.getPageAdviseByPhone("0",phone);
            return new PageInfo<Advise>(sysMenus);
        }else{
            List<Advise> sysMenus =adviseMapper.getPageAdviseByPhone("1",phone);
            return new PageInfo<Advise>(sysMenus);
        }
    }
}
