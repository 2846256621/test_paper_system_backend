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
@TableName("multiple")
@ApiModel(value="Multiple对象", description="")
public class Multiple {
    private static final long serialVersionUID = 1L;

    /**
     * 多选题id
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
     * 选项
     */
    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private String optionE;

    private String optionF;

    /**
     * 答案
     */
    private Integer answer;

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
}
