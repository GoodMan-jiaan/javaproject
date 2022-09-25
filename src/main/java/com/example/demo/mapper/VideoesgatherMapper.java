package com.example.demo.mapper;

import com.example.demo.entity.Videoesgather;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VideoesgatherMapper {


    //添加视频集
    @Insert("insert into videoesgather(videoesGatherId,userId,videoesGatherKind," +
            "videoesGatherName,videoesGatherAuthorName,videoesGatherIntroduce,videoesGatherImage,videoesGatherOrgan)" +
            "value(#{videoesGatherId},#{userId},#{videoesGatherKind}," +
            "#{videoesGatherName},#{videoesGatherAuthorName},#{videoesGatherIntroduce},#{videoesGatherImage},#{videoesGatherOrgan})")
    public int InsertVideoesgather(Videoesgather videoesgather);

    //通过视频集Id删除视频集
    @Delete("delete from videoesgather where videoesGatherId=#{videoesGatherId}")
    public int deletevideoesGatherById(int videoesGatherId);

    //根据用户Id删除视频集
    @Delete("delete from videoesgather where userId=#{userId} ")
    public int deleteVideoGatherByUserId(int userId);


    //查找用户上传的视频
    @Select("select * from videoesgather where userId=#{userId}")
    public List<Videoesgather> SelectVideoesgather(Videoesgather videoesgather);

    //根据信息查找视频集合
    @Select("select * from videoesgather where userId=#{userId} and videoesGatherName=#{videoesGatherName} and " +
            "videoesGatherKind=#{videoesGatherKind} and videoesGatherImage=#{videoesGatherImage}")
    public List<Videoesgather> SelectVideoesId(Videoesgather videoesgather);

    //查找首页信息
    @Select("select * from videoesgather")
    public List<Videoesgather> pageAll();

    //通过视频集名称查看视频集
    @Select("select * from videoesgather where videoesGatherName like concat('%',#{videoesGatherName},'%')")
    public List<Videoesgather> getVideoGatherByName(String videoesGatherName);


    //根据视频集Id查找视频集信息
    @Select("select * from videoesgather where videoesGatherId=#{videoesGatherId}")
    public Videoesgather selectVideoesgatherById(Videoesgather videoesgather);

    //通过用户Id与视频集的名称搜索
    @Select("select * from videoesgather where userId=#{userId} and videoesGatherName like concat('%',#{title},'%')")
    public List<Videoesgather> getVideoGatherByUserIdAndTitle(int userId,String title);

    //更改视频集的图片封面
    @Update("update videoesgather set videoesGatherImage=#{videoesGatherImage} where videoesGatherId=#{videoesGatherId}")
    public int updateVideoesGatherImage(String videoesGatherImage,int videoesGatherId);

    //更改视频集信息
    @Update("update videoesgather set videoesGatherName=#{videoesGatherName},videoesGatherAuthorName=#{videoesGatherAuthorName}," +
            "videoesGatherIntroduce=#{videoesGatherIntroduce},videoesGatherOrgan=#{videoesGatherOrgan},videoesGatherKind=#{videoesGatherKind} where videoesGatherId=#{videoesGatherId}")
    public int updateVideoGatherMassage(Videoesgather videoesgather);

    //根据视频集Id更新视频集信息
    @Update("update videoesgather set videoesGatherKind=#{videoesGatherKind},videoesGatherName=#{videoesGatherName},videoesGatherAuthorName=#{videoesGatherAuthorName}," +
            "videoesGatherIntroduce=#{videoesGatherIntroduce},videoesGatherImage=#{videoesGatherImage},videoesGatherOrgan=#{videoesGatherOrgan}," +
            "videoesGatherTotalAttention=#{videoesGatherTotalAttention},videoesGatherTotalCollect=#{videoesGatherTotalCollect} where videoesGatherId=#{videoesGatherId}")
    public int updateVideoesgatherById(Videoesgather videoesgather);

    //根据视频集Id更改点赞总数
    @Update("update videoesgather set videoesGatherTotalAttention=#{videoesGatherTotalAttention} where videoesGatherId=#{videoesGatherId}")
    public int updateAttentionById(Videoesgather videoesgather);
    //根据视频集Id更改收藏总数
    @Update("update videoesgather set videoesGatherTotalCollect=#{videoesGatherTotalCollect} where videoesGatherId=#{videoesGatherId}")
    public int updateCollectById(Videoesgather videoesgather);
    //根据视频集Id更改评论总数
    @Update("update videoesgather set videoesGatherTotalComment=#{videoesGatherTotalComment} where videoesGatherId=#{videoesGatherId}")
    public int updateCommentById(Videoesgather videoesgather);

    //根据条件查找视频集（三者均存在的）videoesGatherId,videoesGatherKind,videoesGatherName,videoesGatherIntroduce,videoesGatherOrgan,videoesGatherImage
    //R2.videoesGatherId,R2.videoesGatherKind,R2.videoesGatherName,R2.videoesGatherIntroduce,R2.videoesGatherOrgan,R2.videoesGatherImage
    @Select("select * " +
            "from (select distinct videoesGatherId from course where courseVideoesName like concat('%',#{contentType},'%')) as R1 " +
            "inner join (select  * " +
            "from videoesgather where videoesGatherOrgan=#{organType} and videoesGatherKind=#{classType}) as R2 " +
            "on R1.videoesGatherId = R2.videoesGatherId")
    public List<Videoesgather> getVideoesgatherByMassage(String classType,String organType,String contentType);

    @Select("select * from videoesgather where videoesGatherOrgan=#{organType} and videoesGatherKind=#{classType} ")
    public List<Videoesgather> getVideoesgatherByMassage2(String classType,String organType);

    @Select("select * from videoesgather where videoesGatherKind=#{classType}")
    public List<Videoesgather> getVideoesgatherByMassage3(String classType);

    @Select("select * " +
            "from (select distinct videoesGatherId from course where courseVideoesName like concat('%',#{contentType},'%')) as R1 " +
            "inner join (select  * " +
            "from videoesgather where videoesGatherKind=#{classType}) as R2 " +
            "on R1.videoesGatherId = R2.videoesGatherId")
    public List<Videoesgather> getVideoesgatherByMassage4(String classType,String contentType);

    @Select("select * " +
            "from (select distinct videoesGatherId from course where courseVideoesName like concat('%',#{contentType},'%')) as R1 " +
            "inner join videoesgather as R2 " +
            "on R1.videoesGatherId = R2.videoesGatherId")
    public List<Videoesgather> getVideoesgatherByMassage5(String contentType);

    @Select("select * from videoesgather where videoesGatherOrgan=#{organType}")
    public List<Videoesgather> getVideoesgatherByMassage6(String organType);

    @Select("select * " +
            "from (select distinct videoesGatherId from course where courseVideoesName like concat('%',#{contentType},'%')) as R1 " +
            "inner join (select  * " +
            "from videoesgather where videoesGatherOrgan=#{organType}) as R2 " +
            "on R1.videoesGatherId = R2.videoesGatherId")
    public List<Videoesgather> getVideoesgatherByMassage7(String organType,String contentType);
}
