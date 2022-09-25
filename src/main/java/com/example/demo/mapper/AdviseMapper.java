package com.example.demo.mapper;

import com.example.demo.entity.Advise;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdviseMapper {

    //添加建议信息
    @Insert("insert into advise(adviseId,phone,content,uptime) value (#{adviseId},#{phone},#{content},#{uptime})")
    public int InsertAdvise(Advise advise);

    //分页查询建议信息
    @Select("select * from advise where isread=#{isread}")
    public List<Advise> getPageAdvise(String isread);

    //根据手机号分页查询建议信息courseVideoesName like concat('%',#{courseVideoesName},'%')
    @Select("select * from advise where isread=#{isread} and phone like concat('%',#{phone},'%')")
    public List<Advise> getPageAdviseByPhone(String isread,String phone);

    //根据建议Id更改已读
    @Update("update advise set isread=#{isread} where adviseId=#{adviseId} ")
    public int updateIsreadByadminId(String isread,int adviseId);

    //根据建议Id删除
    @Delete("delete from advise where adviseId=#{adviseId}")
    public int deleteAdvise(int adviseId);
}
