package com.ydl.examantion.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("exam_question")
public class ExamQuestion {

    private Integer id;

    private Integer examId;

    private String questionType;

    private Integer questionId;
}
