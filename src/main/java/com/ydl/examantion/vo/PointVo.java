package com.ydl.examantion.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PointVo implements Serializable {

    private Integer userId;

    private Integer pointId;

    private Long subjectId;

    private String pointName;
    /**
     * !!!
     */
    private String chapter;

    private Integer currentPage  = 1;

    private Integer pageSize = 10;
}
