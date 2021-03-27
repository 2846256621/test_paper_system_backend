package com.ydl.examantion.vo;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ProblemMappResVo {

    private Integer subjectId;
    private String subjectName;

    private Integer userId;
    private String steam;

    private String problemType;

    private Double difficultyLevel;

    private String knowledgePoint;

    private ArrayList<String> knowledgePoints;

    private Integer score;

}
