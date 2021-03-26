package com.ydl.examantion.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydl.examantion.dao.SingleMapper;
import com.ydl.examantion.model.Single;
import com.ydl.examantion.service.SingleService;
import com.ydl.examantion.vo.ProblemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SingleServiceImpl extends ServiceImpl<SingleMapper, Single> implements SingleService {

    @Autowired
    SingleMapper singleMapper;

    @Override
    public Page selectPoint(ProblemVo problemVo) {
        return null;
    }
}
