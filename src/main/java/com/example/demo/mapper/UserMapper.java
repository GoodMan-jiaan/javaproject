package com.example.demo.mapper;

import com.example.demo.entity.Admins;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {


    //总的用户
    @Select("select * from user")
    public List<User> findUserAll();

    //根据用户昵称查看用户
    @Select("select * from user where userName like concat('%',#{userName},'%')")
    public List<User> getUserListByUserName(String userName);


    //更改用户密码
    @Update("update user set userPassword=#{userPassword} where userPhone=#{userPhone}")
    public int updatePassword(String userPassword,String userPhone);


    //登录功能
    @Select("select * from user where userPhone=#{userPhone} and userPassword=#{userPassword}")
    public User selectUser(String userPhone,String userPassword);

    //注册功能中查找用户是否存在
    @Select("select * from user where userPhone=#{userPhone}")
    public User selectUserByuserPhone(String userPhone);


    //根据用户Id查找用户信息
    @Select("select * from user where userId=#{userId}")
    public User SelectUserById(User user);

    //根据用户Id删除用户信息
    @Delete("delete from user where userId=#{userId}")
    public int deleteUserById(int userId);

    //添加用户
//    @Insert("insert into user(userId,userPhone,userPassword,userName,userAge" +
//            "userSex,userIdcard,userKinds)" +
//            "value(#{userId},#{userPhone},#{userPassword},#{userName}," +
//            "#{userAge},#{userSex},#{userIdcard},#{userKinds})")
//    public int InsertUser(User user);

    @Insert("insert into user(userPhone,userPassword)" +
            "value(#{userPhone},#{userPassword})")
    public int InsertUser(String userPhone,String userPassword);


    //更改用户信息
    @Update("update user set userPhone=#{userPhone},userPassword=#{userPassword},userName=#{userName}," +
            "userAge=#{userAge},userSex=#{userSex},userIdcard=#{userIdcard},userPracticalName=#{userPracticalName} " +
            "where userId=#{userId}")
    public int UpdateUser(User user);

    //更改用户头像
    @Update("update user set userImageUrl=#{userImageUrl} where userId=#{userId}")
    public int UpdateUserImageUrl(User user);


    //更改用户被关注总数信息
    @Update("update user set userBeFollowTotal=#{userBeFollowTotal} where userId=#{userId}")
    public int UpdateUserBeFollowTotal(User user);

    //更改用户关注总数信息
    @Update("update user set userFollowTotal=#{userFollowTotal} where userId=#{userId}")
    public int UpdateUserFollowTotal(User user);

    //更改用户上传视频集的总数
    @Update("update user set userUpdateTotal=#{userUpdateTotal} where userId=#{userId}")
    public int UpdateuserUpdateTotal(int userId,int userUpdateTotal);

    //更改用户部分信息
    @Update("update user set userName=#{userName},userAge=#{userAge},userSex=#{userSex},userIdcard=#{userIdcard},userPracticalName=#{userPracticalName} " +
            "where userId=#{userId}")
    public int updateMoreUserMassage(User user);

    //根据用户Id更改用户密码
    @Update("update user set userPassword=#{userPassword} where userId=#{userId}")
    public int updatePasswordById(int userId,String userPassword);
}
