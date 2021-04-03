package com.ydl.examantion.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ydl.examantion.model.Blank;
import com.ydl.examantion.vo.ProblemMappResVo;
import com.ydl.examantion.vo.ProblemReqVo;
import com.ydl.examantion.vo.ProblemResVo;
import com.ydl.examantion.vo.ProblemVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YDL
 * @since 2021-02-01
 */
@Mapper
public interface BlankMapper extends BaseMapper<Blank> {

    List<ProblemMappResVo> selectBlank(@Param("problemReqVo") ProblemReqVo problemReqVo, Page page);

    ProblemResVo viewById(@Param("problemVo") ProblemVo problemVo);
}
