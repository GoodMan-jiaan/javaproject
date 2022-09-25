package com.example.demo.service;

import java.util.Map;

/**
 * 阿里短信接口
 */

public interface SendSms {
    public boolean send(String phoneNum,String code);
}
