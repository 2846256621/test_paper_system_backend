package com.ydl.examantion.service.impl;

import com.ydl.examantion.dao.*;
import com.ydl.examantion.service.HomeService;
import com.ydl.examantion.vo.StatisticRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    BlankMapper blankMapper;

    @Autowired
    PaperMapper paperMapper;

    @Autowired
    SubjectMapper subjectMapper;

    @Autowired
    PointMapper pointMapper;

    @Autowired
    UsersMapper usersMapper;

    @Override
    public StatisticRes getStatistic() {
        StatisticRes statisticRes = new StatisticRes();
        statisticRes.setTotalQuestion(blankMapper.questionStatistic());
        statisticRes.setTotalPaper(paperMapper.paperStatistic());
        statisticRes.setTotalSubject(subjectMapper.subjectStatistic());
        statisticRes.setTotalKnowledge(pointMapper.pointStatistic());
        statisticRes.setTotalUser(usersMapper.usersStatistic());
        return statisticRes;
    }
}
