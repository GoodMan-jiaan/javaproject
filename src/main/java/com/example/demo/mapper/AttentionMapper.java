package com.example.demo.mapper;


import com.example.demo.entity.Attention;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AttentionMapper {
    //根据用户ID与用户ID查找关注表，查看用户是否关注
    @Select("select * from attention where followUser=#{followUser} and beFollowUser=#{beFollowUser}")
    public Attention SelectAttentionByUserId(Attention attention);

    @Select("select * from attention where followUser=#{userId}")
    public List<Attention> getPageAttentionById(int userId);

    //添加关注信息
    @Insert("insert into attention(attentionId,followUser,beFollowUser) value (#{attentionId},#{followUser},#{beFollowUser})")
    public int InsertAttention(Attention attention);

    //删除关注信息
    @Delete("delete from attention where followUser=#{followUser} and beFollowUser=#{beFollowUser}")
    public int DeleteAttention(Attention attention);

    //根据用户Id删除关注信息
    @Delete("delete from attention where followUser=#{userId}")
    public int DeleteAttentionFollowUser(int userId);

    //根据用户Id删除被关注信息
    @Delete("delete from attention where beFollowUser=#{userId}")
    public int DeleteAttentionBeFollowUser(int userId);

    //分页查询关注信息
    @Select("select * from attention")
    public List<Attention> getPageAttention();
}
