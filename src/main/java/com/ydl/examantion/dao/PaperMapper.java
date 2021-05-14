package com.ydl.examantion.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ydl.examantion.algorithm.Paper;
import com.ydl.examantion.model.Exam;
import com.ydl.examantion.model.ExamQuestion;
import com.ydl.examantion.vo.PaperListRes;
import com.ydl.examantion.vo.PaperListVo;
import com.ydl.examantion.vo.PaperVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface PaperMapper extends BaseMapper<Exam> {
    Integer saveExam(@Param("exam") Exam exam);

    Integer saveRelation(@Param("examQuestion") ExamQuestion examQuestion);

    List<ExamQuestion> getByExamId(Integer id);

    ArrayList<PaperListRes> selectPaperList(@Param("paperListVo")PaperListVo paperListVo,Page page);

    Integer paperStatistic();
}
