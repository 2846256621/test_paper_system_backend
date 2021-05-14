package com.ydl.examantion.vo;

import lombok.Data;
import java.io.Serializable;

@Data
public class SubjectResponseVo implements Serializable {

    private Long subjectId;

    private  Integer userId;

    private String subjectName;

    private Integer pointCount;

    private Integer choiceCount;


    private Integer judgementCount;

    private Integer blankCount;

    private Integer shortAnswerCount;

    private Integer multipleCount;
}
