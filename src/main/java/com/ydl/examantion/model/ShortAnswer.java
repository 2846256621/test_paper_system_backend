package com.ydl.examantion.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author YDL
 * @since 2021-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("short_answer")
@ApiModel(value="ShortAnswer对象", description="")
public class ShortAnswer {
    private static final long serialVersionUID = 1L;


    /**
     * 填空题id
     */
    @ApiModelProperty(value = "填空题id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 知识点所属学科id
     */
    @ApiModelProperty(value = "知识点所属学科id")
    private String pointId;


    /**
     * 学科id
     */
    @ApiModelProperty(value="学科id")
    private Integer subjectId;


    /**
     * 题干
     */
    @ApiModelProperty(value="题干")
    private String steam;

    /**
     * 答案
     */
    private String answer;

    /**
     * 难度系数
     */
    private Double difficulty;

    /**
     * 分数
     */
    private Integer score;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 所属用户id
     */
    private Integer userId;

    private Integer status;

    private String problemType;
}
