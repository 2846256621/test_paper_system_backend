package com.ydl.examantion.vo;


import lombok.Data;

import java.util.ArrayList;

@Data
public class ProblemResVo {

    private String subjectName;

    private String steam;

    private String problemType;

    private Double difficultyLevel;

    private ArrayList<String> knowledgePoints;

    private Integer score;
}
