package com.ydl.examantion.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProblemReqVo {

    private Integer subjectId;

    private String problemType;

    private Double difficultyLevel;

    private String PointId;

    private Integer score;

    private Integer currentPage  = 1;

    private Integer pageSize = 10;
}
