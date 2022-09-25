package com.example.demo.mapper;

import com.example.demo.entity.Course;
import com.example.demo.entity.Givelike;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GivelikeMapper {

    //分页查询点赞信息
    @Select("select * from givelike")
    public List<Givelike> getPageGiveLike();


    //根据用户ID与视频集ID查找点赞表，查看用户是否有点赞视频集
    @Select("select * from givelike where userId=#{userId} and videoesGatherId=#{videoesGatherId}")
    public Givelike SelectGivelikeByUserId(Givelike givelike);

    //根据用户Id查看用点赞列表
    @Select("select * from givelike where userId=#{userId} order by giveLikeId desc")
    public List<Givelike> SelectGiveLikeById(int userId);

    //添加点赞信息
    @Insert("insert into givelike(giveLikeId,videoesGatherId,userId) value (#{giveLikeId},#{videoesGatherId},#{userId})")
    public int InsertGivelike(Givelike givelike);

    //删除点赞信息
    @Delete("delete from givelike where userId=#{userId} and videoesGatherId=#{videoesGatherId}")
    public int DeleteGivelike(Givelike givelike);

    //根据视频集Id删除点赞表
    @Delete("delete from givelike where videoesGatherId=#{videoesGatherId}")
    public int DeleteGicelikeById(int videoesGatherId);

    //根据用户Id删除点赞
    @Delete("delete from givelike where userId=#{userId}")
    public int DeleteGivelikeByUserId(int userId);
}
