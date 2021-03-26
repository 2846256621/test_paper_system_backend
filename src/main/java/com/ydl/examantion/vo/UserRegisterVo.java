package com.ydl.examantion.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterVo implements Serializable {
    private String verificationCode;
    private String userName;
    private String passWord;
    private Integer type;

}
