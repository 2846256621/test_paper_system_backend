package com.ydl.examantion.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ydl.examantion.model.ShortAnswer;
import com.ydl.examantion.vo.ProblemResVo;
import com.ydl.examantion.vo.ProblemVo;

public interface ShortAnswerService extends IService<ShortAnswer> {


    ProblemResVo viewById(ProblemVo problemVo);
}
