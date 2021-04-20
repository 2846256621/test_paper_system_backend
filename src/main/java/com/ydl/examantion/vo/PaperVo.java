package com.ydl.examantion.vo;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PaperVo {
    private Double difficulty;

    private String endTime;

    private String examName;

    private ArrayList<Integer> points;

    private ArrayList<TypeInfo> problemTypeInfo;

    private String startTime;

    private Integer subjectId;

    private Integer userId;
}
