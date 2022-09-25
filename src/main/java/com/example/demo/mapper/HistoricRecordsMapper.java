package com.example.demo.mapper;

import com.example.demo.entity.Collect;
import com.example.demo.entity.HistoricRecords;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HistoricRecordsMapper {

    @Select("select * from historicrecords")
    public List<HistoricRecords> getPageHistoric();

    //添加通过用户Id，视频集Id添加历史记录
    @Insert("insert into historicrecords(userId,videoesGatherId,hestoricRecordTime) value (#{userId},#{videoesGatherId},#{hestoricRecordTime})")
    public int InsertHistoric(HistoricRecords historicRecords);

    //通过用户ID查找历史信息,并实现逆序排序
    @Select("select * from historicrecords where userId=#{userId} order by hestoricRecordTime desc")
    public List<HistoricRecords> SelectHistoricByUserId(int userId);

    //根据用户Id与视频集Id搜索历史记录
    @Select("select * from historicrecords where userId=#{userId} and videoesGatherId=#{videoesGatherId}")
    public HistoricRecords SelectHistoricByUserIdAndVi(HistoricRecords historicRecords);


    //更新历史信息
    @Update("update historicrecords set hestoricRecordTime=#{hestoricRecordTime} where userId=#{userId} and videoesGatherId=#{videoesGatherId}")
    public int UpdateHistoric(HistoricRecords historicRecords);

    //删除历史记录
    @Delete("delete from historicrecords where userId=#{userId} and videoesGatherId=#{videoesGatherId}")
    public int DeleteHistoric(HistoricRecords historicRecords);

    //根据视频集Id删除历史记录
    @Delete("delete from historicrecords where videoesGatherId=#{videoesGatherId}")
    public int deleteHistoricById(int videoesGatherId);
    //根据用户Id删除历史记录
    @Delete("delete from historicrecords where userId=#{userId}")
    public int DeleteHistoricByUserId(int userId);
}
