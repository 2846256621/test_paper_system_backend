package com.ydl.examantion.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserAddVo implements Serializable {

    private String userName;

    private String passWord;
}
