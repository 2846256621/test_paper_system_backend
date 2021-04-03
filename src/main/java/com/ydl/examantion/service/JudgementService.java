package com.ydl.examantion.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ydl.examantion.model.Judgement;
import com.ydl.examantion.vo.ProblemResVo;
import com.ydl.examantion.vo.ProblemVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YDL
 * @since 2021-02-01
 */
public interface JudgementService extends IService<Judgement> {

    ProblemResVo viewById(ProblemVo problemVo);
}
