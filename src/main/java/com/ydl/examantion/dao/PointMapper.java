package com.ydl.examantion.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ydl.examantion.model.Point;
import com.ydl.examantion.vo.PointResVo;
import com.ydl.examantion.vo.PointVo;
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
public interface PointMapper extends BaseMapper<Point> {

    List<PointResVo> selectPoint(@Param("pointReqVo") PointVo pointReqVo, Page page);

    Integer pointStatistic();
}
