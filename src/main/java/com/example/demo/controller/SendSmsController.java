package com.example.demo.controller;


import com.aliyuncs.utils.StringUtils;
import com.example.demo.service.SendSms;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@Api(tags = "阿里短信控制器")
public class SendSmsController {

    @Autowired
    private SendSms sendSms;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    @RequestMapping("/send")
    @ApiOperation("请求发送短信，并将发送的短信信息放在redis中，保存5分钟")
    public String send(String phone){
        //在redis查看是都已经存在验证码
        String code=redisTemplate.opsForValue().get(phone);
        System.out.println(!StringUtils.isEmpty(code));

        if(!StringUtils.isEmpty(code)){
            return code;
        }

        //生成验证码并存储到redis中
        //随机生成4位验证码
//        code= UUID.randomUUID().toString().substring(0,4);
        code=Math.round(Math.random()*10000)+"";
        System.out.println(code);
        boolean isSend=sendSms.send(phone,code);
        if(isSend){
//            添加数据进入redis中，5分钟自动删除
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return code;
        }else {
            return "发送失败";
        }
    }

    @RequestMapping("/ljaTest")
    public String ljaTest(String phone){
        String a=redisTemplate.opsForValue().get(phone);
        System.out.println(a);
        System.out.println(!StringUtils.isEmpty(a));
        if(!StringUtils.isEmpty(a)){
            return "已经存在";
        }

        redisTemplate.opsForValue().set(phone,"linjiaan",5,TimeUnit.MINUTES);

        return redisTemplate.opsForValue().get(phone);
    }
}
