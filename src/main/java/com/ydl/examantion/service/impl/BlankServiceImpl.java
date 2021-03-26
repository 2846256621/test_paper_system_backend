package com.ydl.examantion.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydl.examantion.dao.BlankMapper;
import com.ydl.examantion.dao.PointMapper;
import com.ydl.examantion.model.Blank;
import com.ydl.examantion.service.BlankService;
import com.ydl.examantion.vo.PointResVo;
import com.ydl.examantion.vo.ProblemMappResVo;
import com.ydl.examantion.vo.ProblemReqVo;
import com.ydl.examantion.vo.ProblemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlankServiceImpl extends ServiceImpl<BlankMapper, Blank> implements BlankService {

    @Autowired
    BlankMapper blankMapper;

    @Autowired
    PointMapper pointMapper;

    @Override
    public Page selectBlank(ProblemReqVo problemReqVo) {
        Page page = new Page(problemReqVo.getCurrentPage(), problemReqVo.getPageSize());
        List<ProblemMappResVo> pointResVos = blankMapper.selectBlank(problemReqVo, page);
        page.setRecords(pointResVos);
        return page;
    }
}
