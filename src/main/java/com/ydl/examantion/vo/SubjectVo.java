package com.ydl.examantion.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubjectVo implements Serializable {

    private String name;

    private  Integer userId;

    private Integer currentPage = 1;

    private Integer pageSize = 10;
}
