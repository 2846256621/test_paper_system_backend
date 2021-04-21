package com.ydl.examantion.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ydl.examantion.algorithm.Individual;
import com.ydl.examantion.algorithm.Paper;
import com.ydl.examantion.algorithm.Question;
import com.ydl.examantion.model.Exam;
import com.ydl.examantion.model.ExamQuestion;
import com.ydl.examantion.vo.ProblemResVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface PaperService extends IService<Exam> {

    Individual makePaper(Integer subject, List<Integer> kpList, Double diff, Map<String, Integer> typeCountMapping, Map<String, Integer> typeScoreMapping,List<Question> qList);

    Integer saveExam(Exam exam);

    boolean saveRelation(Exam exam,List<Question> questions,Map<String,Integer> typeScoreMapping);

    ArrayList<ProblemResVo> getProblemList(List<ExamQuestion> questions);

    List<ExamQuestion> getByExamId(Integer id);
}
