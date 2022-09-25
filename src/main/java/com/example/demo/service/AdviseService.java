package com.example.demo.service;

import com.example.demo.entity.PageRequest;
import com.example.demo.entity.PageResult;

public interface AdviseService {
    //分页查询建议
    PageResult getPageAdvise(PageRequest pageRequest,int just);

    //根据手机号分页查询建议
    PageResult getPageAdviseByPhone(PageRequest pageRequest,String phone,int just);

}
