package com.ydl.examantion.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydl.examantion.dao.ShortAnswerMapper;
import com.ydl.examantion.model.ShortAnswer;
import com.ydl.examantion.service.ShortAnswerService;
import com.ydl.examantion.vo.ProblemResVo;
import com.ydl.examantion.vo.ProblemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class ShortAnswerServiceImpl extends ServiceImpl<ShortAnswerMapper, ShortAnswer> implements ShortAnswerService {

    @Autowired
    ShortAnswerMapper shortAnswerMapper;

    @Override
    public ProblemResVo viewById(ProblemVo problemVo) {
        ProblemResVo problemResVo = shortAnswerMapper.viewById(problemVo);
        String pointIds = problemResVo.getKnowledgePoint();
        problemResVo.setKnowledgePoints(Arrays.asList(pointIds.split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList()));
        return problemResVo;
    }
}
