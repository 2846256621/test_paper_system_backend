package com.ydl.examantion.service.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydl.examantion.dao.JudgementMapper;
import com.ydl.examantion.model.Judgement;
import com.ydl.examantion.service.JudgementService;
import com.ydl.examantion.vo.ProblemResVo;
import com.ydl.examantion.vo.ProblemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class JudgementServiceImpl extends ServiceImpl<JudgementMapper, Judgement> implements JudgementService {

    @Autowired
    JudgementMapper judgementMapper;

    @Override
    public ProblemResVo viewById(ProblemVo problemVo) {
        ProblemResVo problemResVo = judgementMapper.viewById(problemVo);
        String pointIds = problemResVo.getKnowledgePoint();
        problemResVo.setKnowledgePoints(Arrays.asList(pointIds.split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList()));
        return problemResVo;
    }
}
