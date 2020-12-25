package com.yujian.mapper;

import com.yujian.dto.ArticleInfo;
import com.yujian.dto.CommentInfo;
import com.yujian.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/*注解Mapper：表示这是一个mybatis的mapper类*/
@Mapper
@Repository
public interface ArticleMapper {

    /*根据id查询文章*/
    ArticleInfo queryArticleInfoById(int id);
    /* 时间排序 文章列表*/
   List<ArticleInfo> queryAllArticleInfoByTime();
   /*得到置顶文章*/
   List<ArticleInfo> queryArticleByIsTop();
   /*发表新的文章*/
   boolean addArticle(Map<String,Object> map);

   /*根据类别查询文章*/
    List<ArticleInfo> queryArticleByCategory(int id);
    /*根据类别查询文章*/
    List<ArticleInfo> queryArticleByStatus(int id);


    /*用户个人主页 根据用户id查询文章*/
    List<Article> queryArticleByUserId(int id);
    /*首页 根据评论数查询评论数前十的文章*/
    List<Article> queryArticleByCommentNum();

    /*发表评论，文章表中 评论数字段加一*/
    boolean updateCommentNum(int id);
    /*===============后台================*/
    /*id排序  文章列表*/
    List<ArticleInfo> queryAllArticleInfoById();
    /*修改文章*/
    boolean updateArticle(Map<String,Object> map);
    /*删除文章*/
    boolean deleteArticleById(int id);
}
