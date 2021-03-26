package com.ydl.examantion.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ydl.examantion.model.Multiple;
import com.ydl.examantion.vo.ProblemVo;

public interface MultipleService extends IService<Multiple> {
    Page selectPoint(ProblemVo problemVo);
}
