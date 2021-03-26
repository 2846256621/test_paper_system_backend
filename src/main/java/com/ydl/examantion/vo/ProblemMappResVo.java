package com.ydl.examantion.vo;

import lombok.Data;

@Data
public class ProblemMappResVo {

    private Integer subjectId;
    private String subjectName;

    private String steam;

    private String problemType;

    private Double difficultyLevel;

    private String knowledgePoints;

    private Integer score;
}
