package com.ydl.examantion.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydl.examantion.dao.ShortAnswerMapper;
import com.ydl.examantion.model.ShortAnswer;
import com.ydl.examantion.service.ShortAnswerService;
import com.ydl.examantion.vo.ProblemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortAnswerServiceImpl extends ServiceImpl<ShortAnswerMapper, ShortAnswer> implements ShortAnswerService {

    @Autowired
    ShortAnswerMapper shortAnswerMapper;

    @Override
    public Page selectPoint(ProblemVo problemVo) {
        return null;
    }
}
