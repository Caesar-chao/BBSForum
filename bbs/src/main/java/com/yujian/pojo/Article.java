package com.yujian.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private int id;
    private String title;
    private String summary;
    private String content;
    private String createTime;
    private int goodNum;
    private int commentNum;
    private int isTop;
    private int grade;
    /*是否加精*/
    private int refinement;
    private int user_id;
    private int category_id;
}
