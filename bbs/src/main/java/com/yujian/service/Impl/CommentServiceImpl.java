package com.yujian.service.Impl;

import com.yujian.dto.CommentInfo;
import com.yujian.mapper.CommentMapper;
import com.yujian.pojo.Comment;
import com.yujian.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public List<CommentInfo> queryCommentsByArticleId(int article_id) {
        return commentMapper.queryCommentsByArticleId(article_id);
    }

    @Override
    public boolean addComment(Map<String, Object> map) {
        return commentMapper.addComment(map);
    }

    @Override
    public boolean deleteCommentById(int id) {
        return commentMapper.deleteCommentById(id);
    }

    @Override
    public List<Comment> queryCommentByUserId(int user_id) {
        return commentMapper.queryCommentByUserId(user_id);
    }

    @Override
    public List<CommentInfo> queryAllCommentInfo() {
        return commentMapper.queryAllCommentInfo();
    }

    @Override
    public boolean updateCommentById(Map<String,Object> map) {
        return commentMapper.updateCommentById(map);
    }

    @Override
    public CommentInfo queryCommentsById(int id) {
        return commentMapper.queryCommentsById(id);
    }

}
