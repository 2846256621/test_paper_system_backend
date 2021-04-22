package com.ydl.examantion.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PaperListRes {
    private Integer id;

    private String testPaperName;

    private String subject;

    private Integer subjectId;

    private String startTime;

    private String endTime;

    private Integer score;

    private Double difficulty;

    private String knowledge;

    private List<String> knowledgePoints;

    private Integer userId;
}
