package com.ydl.examantion.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ydl.examantion.model.Single;
import com.ydl.examantion.vo.ProblemVo;


public interface SingleService extends IService<Single> {

    Page selectPoint(ProblemVo problemVo);
}
