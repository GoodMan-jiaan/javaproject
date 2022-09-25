package com.example.demo.controller;

import com.example.demo.entity.PageRequest;
import com.example.demo.entity.User;
import com.example.demo.entity.Videoesgather;
import com.example.demo.entity.VideoesgatherResult;
import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.VideoesgatherMapper;
import com.example.demo.service.MovieService;
import com.example.demo.service.VideoGatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//视频操作
@RestController
@Api(tags = "文件集控制器")
public class VideoesgatherController {

    @Autowired
    private VideoesgatherMapper videoesgatherMapper;

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VideoGatherService videoGatherService;

    //新建视频集
    @RequestMapping("/insertVideoesgather")
    public int insertVideoesgather (int userId, String videoesGatherKind, String videoesGatherName,
                                                   String videoesGatherAuthorName, String videoesGatherIntroduce,
                                                   String videoesGatherImage,String videoesGatherOrgan){
        Videoesgather videoesgather =new Videoesgather();
        if(userId!=0 && videoesGatherKind!=null && videoesGatherName!=null && videoesGatherAuthorName!=null
                &&videoesGatherImage!=null &&videoesGatherIntroduce!=null ){
            videoesgather.setUserId(userId);
            videoesgather.setVideoesGatherKind(videoesGatherKind);
            videoesgather.setVideoesGatherName(videoesGatherName);
            videoesgather.setVideoesGatherAuthorName(videoesGatherAuthorName);
            videoesgather.setVideoesGatherIntroduce(videoesGatherIntroduce);
            videoesgather.setVideoesGatherImage(videoesGatherImage);
            videoesgather.setVideoesGatherOrgan(videoesGatherOrgan);
            videoesgather.setVideoesGatherTotalAttention(0);
            videoesgather.setVideoesGatherTotalCollect(0);
            videoesgather.setVideoesGatherTotalComment(0);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            System.out.println(dateFormat.format(date));
            videoesgather.setVideoesGatherUptime(date);
            int result=videoesgatherMapper.InsertVideoesgather(videoesgather);
            //判断是否添加成功，是则返回添加成功的信息
            if(result==1){
                List<Videoesgather> videoesgatherList= videoesgatherMapper.SelectVideoesId(videoesgather);
                //更改用户上传视频集的总数
                User user=new User();
                user.setUserId(userId);
                User user1=userMapper.SelectUserById(user);
                int result1=userMapper.UpdateuserUpdateTotal(userId,user1.getUserUpdateTotal()+1);
                if(result1==1){
                    return videoesgatherList.get(0).getVideoesGatherId();
                }else {
                    return 0;
                }

            }else {
                return 0;
            }

        }else {
            return 0;
        }

    }


    @RequestMapping("/selectVideoesgatherById")
    @ApiOperation("根据视频视频集Id查找查找视频集主要信息")
    public Videoesgather selectVideoesgatherById(int videoesGatherId){
       Videoesgather videoesgather=new Videoesgather();
       if(videoesGatherId!=0){
           videoesgather.setVideoesGatherId(videoesGatherId);
       }
       return videoesgatherMapper.selectVideoesgatherById(videoesgather);
    }

    @RequestMapping("/adminsSelectVideogatherById")
    @ApiOperation("管理员根据视频集Id查找视频集是否存在")
    public boolean adminsSelectVideogatherById(int videoesGatherId){
        Videoesgather videoesgather=new Videoesgather();
        videoesgather.setVideoesGatherId(videoesGatherId);
        Videoesgather videoesgather1=videoesgatherMapper.selectVideoesgatherById(videoesgather);
        if(videoesgather1==null){
            return false;
        }else {
            return true;
        }
    }


    @RequestMapping("/getVideoGatherByName")
    @ApiOperation("根据视频名称搜查视频集")
    public Object getVideoGatherByName(int pageNum,int pageSize,String videoesGatherName){
        PageRequest pageQuery=new PageRequest();
        pageQuery.setPageNum(pageNum);
        pageQuery.setPageSize(pageSize);
        return videoGatherService.getVideoGatherByName(pageQuery,videoesGatherName);
    }


    /**
     * 根据用户id查找视频集，用于上传视频时的视频集选集
     * @param userId
     * @return
     */
    @RequestMapping("/selectVideoesByUserId")
    public List<VideoesgatherResult> selectVideoesByUserId(int userId){
        Videoesgather videoesgather=new Videoesgather();
        List<VideoesgatherResult> videoesgatherResultList=new ArrayList<>();
        if(userId!=0){
            videoesgather.setUserId(userId);
        }
        List<Videoesgather> videoesgatherList= videoesgatherMapper.SelectVideoesgather(videoesgather);
        if (videoesgatherList!=null){

            for(int i=0;i<videoesgatherList.size();i++){
                VideoesgatherResult videoesgatherResult=new VideoesgatherResult();
                String value= videoesgatherList.get(i).getVideoesGatherId()+"";
                videoesgatherResult.setValue(value);
                videoesgatherResult.setLabel(videoesgatherList.get(i).getVideoesGatherName());
                videoesgatherResultList.add(videoesgatherResult);
            }
            return videoesgatherResultList;
        }else {
            return null;
        }
    }

    /**
     * 对视频集进行分页查询
     */
    @RequestMapping("/pageAll")
    public Object findPage(int pageNum,int pageSize){
        PageRequest pageQuery=new PageRequest();
        pageQuery.setPageNum(pageNum);
        pageQuery.setPageSize(pageSize);
        return movieService.findPage(pageQuery);
    }

    /**
     * 根据类型对视频进行分页查询
     */
    @RequestMapping("/getVideoesgatherByMassage")
    public Object findPage(String classType,String organType,String contentType,int pageNum,int pageSize){

        int a=1,b=1,c=1;
        PageRequest pageQuery=new PageRequest();
        pageQuery.setPageSize(pageSize);
        pageQuery.setPageNum(pageNum);
        if(classType.equals("全部"))
            a=2;
        if (organType.equals("全部"))
            b=2;
        if(contentType.equals("全部"))
            c=2;

        //根据传来的数据对数据库进行不一样的查询
        if(a==1&&b==1&&c==1)
            return movieService.getVideoesgatherByMassage(classType,organType,contentType,1,pageQuery);
        else if(a==1&&b==1&&c==2)
            return movieService.getVideoesgatherByMassage(classType,organType,contentType,2,pageQuery);
        else if(a==1&&b==2&&c==2)
            return movieService.getVideoesgatherByMassage(classType,organType,contentType,3,pageQuery);
        else if(a==1&&b==2&&c==1)
            return movieService.getVideoesgatherByMassage(classType,organType,contentType,4,pageQuery);
        else if(a==2&&b==2&&c==1)
            return movieService.getVideoesgatherByMassage(classType,organType,contentType,5,pageQuery);
        else if(a==2&&b==1&&c==2)
            return movieService.getVideoesgatherByMassage(classType,organType,contentType,6,pageQuery);
        else if(a==2&&b==1&&c==1)
            return movieService.getVideoesgatherByMassage(classType,organType,contentType,7,pageQuery);
        else
            return movieService.getVideoesgatherByMassage(classType,organType,contentType,8,pageQuery);
    }

    /**
     * 根据用户Id分页查询视频集Id
     */
    @RequestMapping("/getVideoGatherByUserId")
    @ApiOperation("根据用户Id分页查询视频集Id")
    public Object getVideoGatherByUserId(int pageNum,int pageSize,int userId){
        PageRequest pageRequest=new PageRequest();
        pageRequest.setPageNum(pageNum);
        pageRequest.setPageSize(pageSize);

        return videoGatherService.getVideoGatherByUserId(pageRequest,userId);
    }

    @RequestMapping("/getVideoGatherByUserIdAndTitle")
    @ApiOperation("通过用户Id与视频集的名称搜索")
    public Object getVideoGatherByUserIdAndTitle(int pageNum,int pageSize,int userId,String title){
        PageRequest pageRequest=new PageRequest();
        pageRequest.setPageNum(pageNum);
        pageRequest.setPageSize(pageSize);

        return videoGatherService.getVideoGatherByUserIdAndTitle(pageRequest,userId,title);
    }

    @RequestMapping("/deleteVideoGatherById")
    @ApiOperation("通过视频集Id删除视频集")
    public boolean deleteVideoGatherById(int videoesGatherId){
        return videoGatherService.deleteVideoGatherById(videoesGatherId);
    }

    @RequestMapping("/updateVideoesGatherImage")
    @ApiOperation("更改视频集的图片封面")
    public boolean updateVideoesGatherImage(int videoesGatherId,String videoesGatherImage){
        int result=videoesgatherMapper.updateVideoesGatherImage(videoesGatherImage,videoesGatherId);
        if(result==1){
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("/updateVideoGatherMassage")
    @ApiOperation("更改视频集信息，通过视频集Id")
    public boolean updateVideoGatherMassage(int videoesGatherId,String videoesGatherName,String videoesGatherAuthorName,
                                            String videoesGatherIntroduce,String videoesGatherOrgan,String videoesGatherKind){
        Videoesgather videoesgather=new Videoesgather();
        videoesgather.setVideoesGatherId(videoesGatherId);
        videoesgather.setVideoesGatherName(videoesGatherName);
        videoesgather.setVideoesGatherAuthorName(videoesGatherAuthorName);
        videoesgather.setVideoesGatherOrgan(videoesGatherOrgan);
        videoesgather.setVideoesGatherIntroduce(videoesGatherIntroduce);
        videoesgather.setVideoesGatherKind(videoesGatherKind);
        int result=videoesgatherMapper.updateVideoGatherMassage(videoesgather);
        if(result==1){
            return true;
        }else {
            return false;
        }
    }
}
