package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.example.demo.service.SendSms;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SendSmsImpl implements SendSms {
    @Override
    public boolean send(String phoneNum,String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI5t77EWuyUJbD6AdVKBzN", "pEQfTNSkhe74aPJ6syMEz4qx51m6Ey");
        /** use STS Token
         DefaultProfile profile = DefaultProfile.getProfile(
         "<your-region-id>",           // The region ID
         "<your-access-key-id>",       // The AccessKey ID of the RAM account
         "<your-access-key-secret>",   // The AccessKey Secret of the RAM account
         "<your-sts-token>");          // STS Token
         **/
        IAcsClient client = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();
        request.setSignName("阿里云短信测试");
        request.setTemplateCode("SMS_154950909");
        request.setPhoneNumbers(phoneNum);
//        request.setTemplateParam("{\"code\":\"1234\"}");
        JSONObject resultJson=new JSONObject(); //JSONObject是对象形式
        resultJson.put("code",code);
        request.setTemplateParam(resultJson.toJSONString());

        try {
            SendSmsResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
            return response.getCode().equals("OK");
        } catch (ServerException e) {
            e.printStackTrace();
            System.out.println("发送成功");
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }

        return false;
    }
}
