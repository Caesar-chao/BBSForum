package com.yujian.mapper;

import com.yujian.dto.CommentInfo;
import com.yujian.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/*注解Mapper：表示这是一个mybatis的mapper类*/
@Mapper
@Repository
public interface CommentMapper {
    /*根据文章id查询评论*/
    List<CommentInfo> queryCommentsByArticleId(int article_id);

    /* ========================*/
    /*评论*/
    boolean addComment(Map<String,Object> map);
    /*根据id删除评论*/
    boolean deleteCommentById(int id);
    /*==============================*/
    /*根据用户id获取用户最新发布的十条评论*/
    List<Comment> queryCommentByUserId(int user_id);


    /*=============后台=============*/
    /*获取所有评论及评论者，被评论文章信息*/
    List<CommentInfo> queryAllCommentInfo();
    /*修改评论*/
    boolean updateCommentById(Map<String,Object> map);
    /**/
    /*根据id查询评论*/
    CommentInfo queryCommentsById(int id);
}
