package com.ydl.examantion.model;


import com.baomidou.mybatisplus.annotation.TableName;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.PrimitiveIterator;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("exam")
public class Exam {
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
