package com.ydl.examantion.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@TableName("knowledge_point")
@ApiModel(value="Point对象", description="")
public class Point implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 知识点id
     */
    @ApiModelProperty(value = "知识点id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 知识点所属学科id
     */
    @ApiModelProperty(value = "知识点所属学科id")
    private Integer subjectId;

    /**
     * 知识点名称
     */
    @ApiModelProperty(value = "知识点名称")
    private String name;

    /**
     * 知识点所属章节
     */
    @ApiModelProperty(value="知识点所属章节")
    private String chapter;

    /**
     * 知识点状态
     */
    private Integer status;

    /**
     * 用户编号
      */
    private Integer userId;
}
