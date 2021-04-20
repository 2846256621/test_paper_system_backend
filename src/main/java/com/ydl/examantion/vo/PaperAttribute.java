package com.ydl.examantion.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PaperAttribute {
    private Double fitness;

    private Double difficulty;

    private Double pointCoverage;

    private Integer problemNum;

    private Integer score;
}
