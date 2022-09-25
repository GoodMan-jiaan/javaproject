package com.example.demo.mapper;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Videoesgather;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    //分页查询评论信息
    @Select("select * from comment")
    public List<Comment> getPageComment();


    //更改评论内容
    @Update("update comment set commenttext=#{commenttext} where commentId=#{commentId}")
    public int updateCommentText(int commentId,String commenttext);

    //通过视频集ID查找评论信息
    @Select("select * from comment where videoesGatherId=#{videoesGatherId}")
    public List<Comment> SelectCommentByVideoID(Comment comment);

    //通过视频集Id与用户ID发表评论
    @Insert("insert into comment(commentId,userId,videoesGatherId,commenttext,commentUptime) " +
            "value(#{commentId},#{userId},#{videoesGatherId},#{commenttext},#{commentUptime})")
    public int insertComment(Comment comment);

    //通过评论ID删除评论
    @Delete("delete from comment where commentId=#{commentId}")
    public int deleteCommentByID(Comment comment);

    //通过视频集Id删除评论
    @Delete("delete from comment where videoesGatherId=#{videoesGatherId}")
    public int deleteByVideoesGatherId(int videoesGatherId);

    //通过用户Id删除信息
    @Delete("delete from comment where userId=#{userId}")
    public int deleteCommentByUserId(int userId);

//    @Select("select * " +
//            "from (select distinct videoesGatherId from course where courseVideoesName like concat('%',#{contentType},'%')) as R1 " +
//            "inner join (select  * " +
//            "from videoesgather where videoesGatherOrgan=#{organType} and videoesGatherKind=#{classType}) as R2 " +
//            "on R1.videoesGatherId = R2.videoesGatherId")
//    public List<Videoesgather> getVideoesgatherByMassage(String classType, String organType, String contentType);

    //根据用户Id查看用户发送的视频集Id，在根据其视频集Id搜索评论表
    @Select("select * from (select videoesGatherId from videoesgather where userId=#{userId}) as R1 " +
            "inner join (select * from comment) as R2 " +
            "on R1.videoesGatherId=R2.videoesGatherId order by commentUptime desc")
    public List<Comment> SelectCommentByTwoTable(int userId);



    @Select("select * from comment where userId=#{userId} order by commentUptime desc")
    public List<Comment> SelectCommentByUserId(int userId);

}
