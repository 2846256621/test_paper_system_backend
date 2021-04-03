package com.ydl.examantion.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ydl.examantion.model.Single;
import com.ydl.examantion.vo.ProblemResVo;
import com.ydl.examantion.vo.ProblemVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SingleMapper extends BaseMapper<Single> {
    ProblemResVo viewById(@Param("problemVo") ProblemVo problemVo);
}
