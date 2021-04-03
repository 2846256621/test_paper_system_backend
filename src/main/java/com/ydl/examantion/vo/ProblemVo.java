package com.ydl.examantion.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class ProblemVo implements Serializable {

    private Integer problemId;

    private String answer;

    private String choiceOptionA;

    private String choiceOptionB;

    private String choiceOptionC;

    private String choiceOptionD;

    private Double difficultyLevel;

    private ArrayList<Integer> knowledgePoints;

    private String multipleOptionA;

    private String multipleOptionB;

    private String multipleOptionC;

    private String multipleOptionD;

    private String multipleOptionE;

    private String multipleOptionF;

    private String problemText;

    private String problemType;

    private Integer score;

    private Integer subject;

    private Integer userId;

    private Integer pageSize;
}
