package com.ydl.examantion.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Joiner;
import com.ydl.examantion.algorithm.Question;
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
    public Page selectProblem(ProblemReqVo problemReqVo) {
        Page page = new Page(problemReqVo.getCurrentPage(), problemReqVo.getPageSize());
        List<ProblemMappResVo> dataList = blankMapper.selectProblem(problemReqVo, page);
        for(int i=0;i<dataList.size();i++){
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

    @Override
    public ProblemResVo viewById(ProblemVo problemVo) {
        ProblemResVo problemResVo = blankMapper.viewById(problemVo);
        String pointIds = problemResVo.getKnowledgePoint();
        problemResVo.setKnowledgePoints(Arrays.asList(pointIds.split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList()));
        return problemResVo;
    }

    @Override
    public List<Question> selectProblemByPoint(List<Integer> points) {
        ProblemReqVo problemReqVo = new ProblemReqVo();
        Page page = new Page(problemReqVo.getCurrentPage(), problemReqVo.getPageSize());
        List<Question> list = new ArrayList<>();
        for(int j=0;j<points.size();j++) {
            problemReqVo.setPointId(points.get(j).toString());
            System.out.println(problemReqVo);
            List<ProblemMappResVo> dataList = blankMapper.selectProblem(problemReqVo, page);
            for (int i=0;i < dataList.size(); i++) {
                List<Integer> pointLs = Arrays.asList(dataList.get(i).getKnowledgePoint().split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
                Question question = new Question(dataList.get(i).getProblemId(), dataList.get(i).getProblemType(), dataList.get(i).getScore(), dataList.get(i).getDifficultyLevel(),pointLs);
                list.add(question);
            }
        }
        return list;
    }


}
