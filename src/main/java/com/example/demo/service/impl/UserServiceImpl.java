package com.example.demo.service.impl;


import com.example.demo.entity.*;
import com.example.demo.mapper.*;
import com.example.demo.service.UserService;
import com.example.demo.utils.PageUtils;
import com.example.demo.utils.QiniuFileUtil;
import com.example.demo.utils.QiniuImageUtil;
import com.example.demo.utils.QiniuUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private GivelikeMapper givelikeMapper;

    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private HistoricRecordsMapper historicRecordsMapper;

    @Autowired
    private AttentionMapper attentionMapper;

    @Autowired
    private VideoesgatherMapper videoesgatherMapper;

    @Override
    public PageResult findUserByPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest,findUserByPage1(pageRequest));
    }

    private PageInfo<User> findUserByPage1(PageRequest pageRequest){
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();

        PageHelper.startPage(pageNum, pageSize);
        List<User> sysMenus = userMapper.findUserAll();
        return new PageInfo<User>(sysMenus);
    }

    //根据用户昵称分页查询用户
    @Override
    public PageResult getUserListByUserName(PageRequest pageRequest, String userName) {
        return PageUtils.getPageResult(pageRequest,getUserListByUserName1(pageRequest,userName));
    }
    private PageInfo<User> getUserListByUserName1(PageRequest pageRequest,String userName){
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();

        PageHelper.startPage(pageNum, pageSize);
        List<User> sysMenus = userMapper.getUserListByUserName(userName);
        return new PageInfo<User>(sysMenus);
    }

    @Override
    public boolean adminsDeleteByUserId(int userId) {
        //导入图片工具类
        QiniuImageUtil qiniuImageUtil=new QiniuImageUtil();
        //导入视频类
        QiniuUtil qiniuUtil=new QiniuUtil();
        //导入文件类
        QiniuFileUtil qiniuFileUtil=new QiniuFileUtil();
        //删除一下信息
        int GivelikeNum=givelikeMapper.DeleteGivelikeByUserId(userId);
        int CollectNum=collectMapper.DeleteCollectByUserId(userId);
        int CommentNum=commentMapper.deleteCommentByUserId(userId);
        int HistoricRecordNum=historicRecordsMapper.DeleteHistoricByUserId(userId);
        int AttentionNum1=attentionMapper.DeleteAttentionFollowUser(userId);
        int AttentionNum2=attentionMapper.DeleteAttentionBeFollowUser(userId);

        Videoesgather videoesgather=new Videoesgather();
        videoesgather.setUserId(userId);
        //获取视频集Id
        List<Videoesgather> videoesgatherList=videoesgatherMapper.SelectVideoesgather(videoesgather);
        //判断视频集列表
        if(videoesgatherList.size()!=0){
            //循环获取视频集Id
            for(int i=0;i<videoesgatherList.size();i++){
                int videoGatherId=videoesgatherList.get(i).getVideoesGatherId();
                String videoGatherImage=videoesgatherList.get(i).getVideoesGatherImage();


                Course course=new Course();
                course.setVideoesGatherId(videoGatherId);
                List<Course> courseList=courseMapper.SelectCourseByVideoesGatherId(course);
                if(courseList.size()!=0){
                    for (int j=0;j<courseList.size();j++){
                        String CourseFileResources=courseList.get(j).getCourseFileResources();
                        String CourseVideoesResources=courseList.get(j).getCourseVideoesResources();
                        int CourseId=courseList.get(j).getCourseId();
                        //删除七牛云中的文件资料
                        if(CourseFileResources!=null){
                            int code=qiniuFileUtil.deleteFileFromQiniu(CourseFileResources);
                        }
                        //删除七牛云中的视频资料
                        if(CourseVideoesResources!=null){
                            int code=qiniuUtil.deleteFileFromQiniu(CourseVideoesResources);
                        }
                        //根据视频Id删除视频
                        int CourseNum=courseMapper.deleteByCourseId(CourseId);
                    }
                }
                //删除七牛云图片
                if(videoGatherImage!=null){
                    int code=qiniuImageUtil.deleteImageFromQiniu(videoGatherImage);
                }
                //删除视频集
                int VideoGatherNum=videoesgatherMapper.deletevideoesGatherById(videoGatherId);
            }
        }
        int UserNum=userMapper.deleteUserById(userId);
        if(UserNum==1){
            return true;
        }else {
            return false;
        }
    }

}
