package com.example.demo.controller;

import com.example.demo.entity.PageRequest;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController()
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public Object test() {
        return "hello";
    }

    //根据用户Id查找用户信息
    @RequestMapping("/SelectUserById")
    @ApiOperation("根据用户Id查找用户信息")
    public User SelectUserById(int userId){
        User user=new User();
        if (userId!=0){
            user.setUserId(userId);
        }
        return userMapper.SelectUserById(user);
    }

    //根据用户Id查看用户是否存在
    @RequestMapping("/adminsSelectUserById")
    @ApiOperation("根据用户Id查看用户是否存在")
    public boolean adminsSelectUserById(int userId){
        User user=new User();
        user.setUserId(userId);
        User user1=userMapper.SelectUserById(user);
        if(user1==null){
            return false;
        }else {
            return true;
        }
    }


    @RequestMapping("/adminsDeleteByUserId")
    @ApiOperation("根据用户Id删除用户")
    public boolean adminsDeleteByUserId(int userId){
        return userService.adminsDeleteByUserId(userId);
    }


    //总的用户
    @RequestMapping("/Alluser")
    public List<User> Alluser(){
        List<User> list=userMapper.findUserAll();
        return list;
    }

    //分页查询用户
    @RequestMapping("/findUserByPage")
    public Object findUserByPage(int pageNum,int pageSize){

        PageRequest pageRequest=new PageRequest();
        pageRequest.setPageNum(pageNum);
        pageRequest.setPageSize(pageSize);
        return userService.findUserByPage(pageRequest);
    }

    @RequestMapping("/getUserListByUserName")
    @ApiOperation("根据用户昵称分页查询用户")
    public Object getUserListByUserName(int pageNum,int pageSize,String userName){
        PageRequest pageRequest=new PageRequest();
        pageRequest.setPageNum(pageNum);
        pageRequest.setPageSize(pageSize);
        return userService.getUserListByUserName(pageRequest,userName);
    }

    //登录功能
    @RequestMapping("/loginBack")
    public User login(String userPhone,String userPassword){
        User user=new User();
        if (userPassword !=null && userPhone !=null) {
            user=userMapper.selectUser(userPhone,userPassword);
        }
        if(user==null){
            User user1=new User();
            user1.setUserId(0);
            return user1;
        }else {
            return user;
        }

    }

    //注册功能中查找用户是否存在
    @RequestMapping("/selectByuserPhone")
    public User selectUserByuserPhone(String userPhone){
        User user=new User();
        if(userPhone!=null){
            user=userMapper.selectUserByuserPhone(userPhone);
            if(user==null){
                User user1=new User();
                user1.setUserId(0);
                return user1;
            }
        }
        return user;
    }


    //添加用户
    @RequestMapping("/insertUser")
    public User insertUser(String userPhone,String userPassword){
        User user=new User();
        if(userPhone!=null&&userPassword!=null){
            int result=userMapper.InsertUser(userPhone,userPassword);
            if(result==1){
                user=userMapper.selectUser(userPhone,userPassword);
                return user;
            }else {
                return null;
            }
        }else {
            return null;
        }
    }

    //更改用户图片
    @RequestMapping("/UpdateUserImageUrl")
    public User UpdateUserImageUrl(String userImageUrl,int userId){
        User user=new User();
        if(!userImageUrl.isEmpty()){
            user.setUserId(userId);
            user.setUserImageUrl(userImageUrl);
            int result=userMapper.UpdateUserImageUrl(user);
            if(result==1){
                user=userMapper.SelectUserById(user);
                return user;
            }else {
                return null;
            }
        }else {
            return null;
        }
    }


    //更改用户信息
    @RequestMapping("/updateUser")
    public boolean updateUser(int userId,String userPhone,String userPassword,String userName,String userPracticalName,
    int userAge,String userSex,String userIdcard){
        User user=new User();
        if(userPassword!=null&&userPhone!=null){
            user.setUserId(userId);
            user.setUserPassword(userPassword);
            user.setUserPhone(userPhone);
            user.setUserAge(userAge);
            user.setUserIdcard(userIdcard);
            user.setUserPracticalName(userPracticalName);
            user.setUserName(userName);
            user.setUserSex(userSex);

            int result=userMapper.UpdateUser(user);
            if(result==1){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }

    }

    @RequestMapping("/updatePassword")
    @ApiOperation("更改用户密码")
    public boolean updatePassword(String userPassword,String userPhone){
        int result=userMapper.updatePassword(userPassword,userPhone);
        if(result==1){
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("/updatePasswordById")
    @ApiOperation("根据用户Id更改密码")
    public boolean updatePasswordById(int userId,String userPassword){
        int result=userMapper.updatePasswordById(userId,userPassword);
        if(result==1){
            return true;
        }else {
            return false;
        }
    }

    //更改用户信息（个人中心中更改用户信息）
    @RequestMapping("/updateMoreUserMassage")
    public User updateMoreUserMassage(int userId,String userName,int userAge,String userSex,String userIdcard,String userPracticalName){
        User user=new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setUserAge(userAge);
        user.setUserSex(userSex);
        user.setUserIdcard(userIdcard);
        user.setUserPracticalName(userPracticalName);
        int result=userMapper.updateMoreUserMassage(user);
        if(result==1){
            User user2=new User();
            user2.setUserId(userId);
            return userMapper.SelectUserById(user2);
        }else {
            return null;
        }
    }
}
