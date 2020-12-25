package com.yujian.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private String nickname;
    private String signature;
    private String sclupture;
    private String tel;
    private String email;
    private int integral;
    private int status;
}
