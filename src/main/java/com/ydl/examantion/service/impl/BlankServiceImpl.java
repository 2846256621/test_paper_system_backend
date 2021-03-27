package com.ydl.examantion.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Joiner;
import com.ydl.examantion.dao.BlankMapper;
import com.ydl.examantion.dao.PointMapper;
import com.ydl.examantion.model.Blank;
import com.ydl.examantion.service.BlankService;
import com.ydl.examantion.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlankServiceImpl extends ServiceImpl<BlankMapper, Blank> implements BlankService {

    @Autowired
    BlankMapper blankMapper;

    @Autowired
    PointMapper pointMapper;

    @Override
    public Page selectBlank(ProblemReqVo problemReqVo) {
        Page page = new Page(problemReqVo.getCurrentPage(), problemReqVo.getPageSize());
        List<ProblemMappResVo> dataList = blankMapper.selectBlank(problemReqVo, page);
//        ArrayList<ProblemResVo> problemResVos = new ArrayList<ProblemResVo>();
        for(int i=0;i<dataList.size();i++){
//            ProblemResVo problemResVo = new ProblemResVo();
            String pointIds = dataList.get(i).getKnowledgePoint();
            List<Integer> pointLs = Arrays.asList(pointIds.split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
            ArrayList<String> pointNames = new ArrayList<String>();
            for (Integer pointId:pointLs) {
                pointNames.add(pointMapper.selectById(pointId).getName());
            }
            dataList.get(i).setKnowledgePoints(pointNames);
        }
        page.setRecords(dataList);
        return page;
    }
}
