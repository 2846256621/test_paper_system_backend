package com.ydl.examantion.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ydl.examantion.model.Judgement;
import com.ydl.examantion.vo.ProblemResVo;
import com.ydl.examantion.vo.ProblemVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YDL
 * @since 2021-02-01
 */
@Mapper
public interface JudgementMapper extends BaseMapper<Judgement> {
    ProblemResVo viewById(@Param("problemVo") ProblemVo problemVo);
}
