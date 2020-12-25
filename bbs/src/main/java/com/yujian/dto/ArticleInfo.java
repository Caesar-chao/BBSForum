package com.yujian.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleInfo {
    private int id;
    private String title;
    private String summary;
    private String content;
    private Date create_time;
    private int goodNum;
    private int commentNum;
    private int isTop;
    private int grade;
    private int refinement;
    private int user_id;
    private int category_id;
    private String nickname;
    private int integral;
    private int status;

    private String tel;
    private String name;
}
