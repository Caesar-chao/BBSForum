package com.yujian.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentInfo {
    /*评论表信息*/
    private int id;
    private int user_id;
    private int article_id;
    private String comment_content;
    private Date comment_time;
    /*用户表信息*/
    private String username;
    private String nickname;
    private String status;
    /*文章信息*/
    private String title;
}
