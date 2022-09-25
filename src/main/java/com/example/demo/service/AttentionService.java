package com.example.demo.service;

import com.example.demo.entity.PageRequest;
import com.example.demo.entity.PageResult;

public interface AttentionService {

    PageResult getPageAttention(PageRequest pageRequest);

    PageResult getPageAttentionById(PageRequest pageRequest,int userId);
}
