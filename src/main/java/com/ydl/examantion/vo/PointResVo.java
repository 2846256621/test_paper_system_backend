package com.ydl.examantion.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PointResVo implements Serializable {

    private Integer pointId;

    private String pointName;

    private Integer userId;

    private String subjectName;

    private String chapter;
}
