package com.example.demo.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//保存图片返回名称
public class SavePicture {
    public String uploadfile(MultipartFile file, HttpServletRequest request )throws IOException{

        String realPath="D:\\a_22_HangJia\\graduation\\videoesdownload";
        String dateString=null;
        System.out.println("进入get方法！");

        MultipartHttpServletRequest req =(MultipartHttpServletRequest)request;
        MultipartFile multipartFile =  req.getFile("file");

        try {
            File dir = new File(realPath);
            if (!dir.exists()) {
                dir.mkdir();
            }
            //获得当前时间戳
            Date currentTime = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            dateString = "z_lja"+df.format(currentTime)+".mp4";// 当前时间戳做文件名

            System.out.println(dateString);
            File file1=  new File(realPath,dateString);
            multipartFile.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        //返回文件名
        return dateString;
    }
}




//ArticleController.java
//@Controller
//@RequestMapping("/article")
//public class ArticleController {
//    @Autowired
//    private ArticleServiceImpl articleServiceImpl;
//
//    @GetMapping("/getArticle")
//    @ResponseBody
//    public Article getArticle(@RequestParam("id") Integer id){
//        return articleServiceImpl.findArticle(id);
//    }
//
//    @GetMapping("/getArticles")
//    @ResponseBody
//    public ArticleList getArticles(@RequestParam("page")Integer page){
//        return articleServiceImpl.findArticleList(page);
//    }
//    //上传
//    @RequestMapping("/insert")
//    @ResponseBody
//    public String setArticle(Article article){
//        System.out.println(article.toString());
//        int flag = articleServiceImpl.insertArticle(article);
//        if(flag>0){
//            return article.getUserName()+"上传成功";
//        }
//        return article.getUserName()+"上传失败";
//    }
//
//    @GetMapping("/selectLike")
//    @ResponseBody
//    public int selectLike(@RequestParam("id")Integer id,@RequestParam("userId")String userId){
//        return articleServiceImpl.selectLike(id,userId);
//    }
//    //点赞更新
//    @GetMapping("/updateAlikeNums")
//    @ResponseBody
//    public String updateAlikeNums(@RequestParam("id")Integer id,@RequestParam("num")Integer num){
//        int flag = articleServiceImpl.updateAlikeNums(id,num);
//        if(flag>0){
//            return id+"点赞更新成功";
//        }
//        return id+"点赞更新失败";
//    }
//    //评论更新
//    @GetMapping("/updateCommentCount")
//    @ResponseBody
//    public String updateCommentCount(@RequestParam("id")Integer id,@RequestParam("num")Integer num){
//        int flag = articleServiceImpl.updateCommentCount(id,num);
//        if(flag>0){
//            return id+"评论总数更新成功";
//        }
//        return id+"品论总数更新失败";
//    }
//    //删除
//    @GetMapping("/findMyArticleList")
//    @ResponseBody
//    public List<Article> findMyArticleList(@RequestParam("userId")String userId){
//        return articleServiceImpl.findMyArticleList(userId);
//    }
//    //删除文章，点赞记录，评论列表
//    @GetMapping("/deleteAll")
//    @ResponseBody
//    public String deleteAll(@RequestParam("id")Integer id){
//        int flag = articleServiceImpl.deleteAll(id);
//        if(flag>0){
//            return id+"文章删除成功";
//        }
//        return id+"文章删除失败";
//    }
//}









