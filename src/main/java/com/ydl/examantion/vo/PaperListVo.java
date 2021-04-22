package com.ydl.examantion.vo;


import lombok.Data;

@Data
public class PaperListVo {

    private Integer subjectId;

    private String examName;

    private Integer userId;

    private Integer examId;

    private Integer currentPage  = 1;

    private Integer pageSize = 10;
}
