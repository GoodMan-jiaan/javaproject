package com.example.demo.mapper;


import com.example.demo.entity.Collect;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollectMapper {
    //根据用户ID与视频集ID查找收藏表，查看用户是否收藏视频集
    @Select("select * from collect where userId=#{userId} and videoesGatherId=#{videoesGatherId}")
    public Collect SelectCollectByUserId(Collect collect);

    //根据用户表查看收藏信息
    @Select("select * from collect where userId=#{userId}")
    public List<Collect> SelectCollect(Collect collect);

    //添加收藏信息
    @Insert("insert into collect(collectId,videoesGatherId,userId) value (#{collectId},#{videoesGatherId},#{userId})")
    public int InsertCollect(Collect collect);

    //删除收藏信息
    @Delete("delete from collect where userId=#{userId} and videoesGatherId=#{videoesGatherId}")
    public int DeleteCollect(Collect collect);

    //通过视频集Id删除收藏信息
    @Delete("delete from collect where videoesGatherId=#{videoesGatherId}")
    public int DeleteCollectById(int videoesGatherId);

    //根据用户Id删除收藏信息
    @Delete("delete from collect where userId=#{userId}")
    public int DeleteCollectByUserId(int userId);

    @Select("select * from collect")
    public List<Collect> getPageCollect();
}
