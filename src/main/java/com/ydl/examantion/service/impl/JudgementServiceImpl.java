package com.ydl.examantion.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydl.examantion.dao.JudgementMapper;
import com.ydl.examantion.model.Judgement;
import com.ydl.examantion.service.JudgementService;
import org.springframework.stereotype.Service;

@Service
public class JudgementServiceImpl extends ServiceImpl<JudgementMapper, Judgement> implements JudgementService {

}
