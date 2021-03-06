package com.ydl.examantion.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ydl.examantion.algorithm.Question;
import com.ydl.examantion.model.Blank;
import com.ydl.examantion.vo.ProblemReqVo;
import com.ydl.examantion.vo.ProblemResVo;
import com.ydl.examantion.vo.ProblemVo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YDL
 * @since 2021-02-01
 */
public interface  BlankService extends IService<Blank> {

    Page selectProblem(ProblemReqVo problemReqVo);

    ProblemResVo viewById(ProblemVo problemVo);

    List<Question> selectProblemByPoint(List<Integer> points);
}
