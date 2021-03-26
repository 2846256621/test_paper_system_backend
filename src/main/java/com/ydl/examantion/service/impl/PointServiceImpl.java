package com.ydl.examantion.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydl.examantion.dao.PointMapper;
import com.ydl.examantion.model.Point;
import com.ydl.examantion.service.PointService;
import com.ydl.examantion.vo.PointResVo;
import com.ydl.examantion.vo.PointVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YDL
 * @since 2021-02-01
 */
@Service
public class PointServiceImpl extends ServiceImpl<PointMapper, Point> implements PointService {

    @Autowired
    PointMapper pointMapper;
    @Override
    public Page selectPoint(PointVo pointReqVo) {
        Page page = new Page(pointReqVo.getCurrentPage(), pointReqVo.getPageSize());
        List<PointResVo> pointResVos = pointMapper.selectPoint(pointReqVo, page);
        page.setRecords(pointResVos);
        return page;
    }
}
