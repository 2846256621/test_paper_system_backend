package com.ydl.examantion.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="学科管理对象", description="")
@TableName( value = "subject")
public class Subject {
    /**
     * 学科主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学科姓名
     */
    private String name;

    /**
     * 学科状态
     */
    private Integer status;

    /***
     * 用户编号
     */
    private Integer userId;
}
