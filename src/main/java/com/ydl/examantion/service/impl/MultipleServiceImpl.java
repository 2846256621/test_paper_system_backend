package com.ydl.examantion.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydl.examantion.dao.MultipleMapper;
import com.ydl.examantion.model.Multiple;
import com.ydl.examantion.service.MultipleService;
import com.ydl.examantion.vo.ProblemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultipleServiceImpl extends ServiceImpl<MultipleMapper, Multiple> implements MultipleService {

    @Autowired
    MultipleMapper multipleMapper;

    @Override
    public Page selectPoint(ProblemVo problemVo) {
        return null;
    }
}
