package com.yujian.service.Impl;

import com.yujian.dto.ArticleInfo;
import com.yujian.dto.CommentInfo;
import com.yujian.mapper.ArticleMapper;
import com.yujian.pojo.Article;
import com.yujian.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public ArticleInfo queryArticleInfoById(int id) {
        return articleMapper.queryArticleInfoById(id);
    }

    @Override
    public List<ArticleInfo> queryAllArticleInfoByTime() {
        return  articleMapper.queryAllArticleInfoByTime();
    }

    @Override
    public List<ArticleInfo> queryArticleByIsTop() {
        return articleMapper.queryArticleByIsTop();
    }

    @Override
    public boolean addArticle(Map<String, Object> map) {
        return articleMapper.addArticle(map);
    }

    @Override
    public List<ArticleInfo> queryArticleByCategory(int id) {
        return articleMapper.queryArticleByCategory(id);
    }

    @Override
    public List<ArticleInfo> queryArticleByStatus(int id) {
        return articleMapper.queryArticleByStatus(id);
    }
    /*用户个人主页 根据用户id查询文章*/
    public List<Article> queryArticleByUserId(int id){
        return articleMapper.queryArticleByUserId(id);
    }

    @Override
    public List<Article> queryArticleByCommentNum() {
        return articleMapper.queryArticleByCommentNum();
    }

    @Override
    public boolean updateCommentNum(int id) {
        return articleMapper.updateCommentNum(id);
    }

    @Override
    public List<ArticleInfo> queryAllArticleInfoById() {
        return articleMapper.queryAllArticleInfoById();
    }

    @Override
    public boolean updateArticle(Map<String, Object> map) {
        return articleMapper.updateArticle(map);
    }

    @Override
    public boolean deleteArticleById(int id) {
        return articleMapper.deleteArticleById(id);
    }

}
