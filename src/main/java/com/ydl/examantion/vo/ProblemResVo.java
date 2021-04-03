package com.ydl.examantion.vo;


import lombok.Data;

import java.util.List;


@Data
public class ProblemResVo {

    private String problemType;
    private Integer subject;
    private Double difficultyLevel;
    private List<Integer> knowledgePoints;
    private String problemText;
    private String answer;
    private String choiceOptionA;
    private String choiceOptionB;
    private String choiceOptionC;
    private String choiceOptionD;

    private String multipleOptionA;
    private String multipleOptionB;
    private String multipleOptionC;
    private String multipleOptionD;
    private String multipleOptionE;
    private String multipleOptionF;
    private Integer score;

    private String knowledgePoint;
}
