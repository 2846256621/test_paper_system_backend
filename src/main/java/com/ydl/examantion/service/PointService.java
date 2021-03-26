package com.ydl.examantion.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ydl.examantion.model.Point;
import com.ydl.examantion.vo.PointResVo;
import com.ydl.examantion.vo.PointVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YDL
 * @since 2021-02-01
 */
public interface PointService extends IService<Point> {



    Page selectPoint(PointVo pointReqVo);
}
