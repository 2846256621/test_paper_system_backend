package com.ydl.examantion.model;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("exam")
@ApiModel(value="exam对象", description="")
public class Exam {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer subjectId;

    private String pointId;

    private Integer userId;

    private String examName;

    private Integer problemNum;


    private Double difficulty;

    private Double pointCoverage;

    private Double fitness;

    private Integer score;

    private String startTime;

    private String endTime;

    private LocalDateTime createTime;

}
