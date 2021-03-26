package com.ydl.examantion.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ydl.examantion.model.Blank;
import com.ydl.examantion.vo.ProblemReqVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YDL
 * @since 2021-02-01
 */
public interface BlankService extends IService<Blank> {

    Page selectBlank(ProblemReqVo problemReqVo);
}
