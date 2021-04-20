package com.ydl.examantion.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydl.examantion.algorithm.Individual;
import com.ydl.examantion.algorithm.Paper;
import com.ydl.examantion.algorithm.PaperMaker;
import com.ydl.examantion.algorithm.Question;
import com.ydl.examantion.dao.*;
import com.ydl.examantion.model.Exam;
import com.ydl.examantion.model.ExamQuestion;
import com.ydl.examantion.model.Judgement;
import com.ydl.examantion.model.ShortAnswer;
import com.ydl.examantion.service.PaperService;
import com.ydl.examantion.vo.ProblemResVo;
import com.ydl.examantion.vo.ProblemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper,Exam> implements PaperService {

    @Autowired
    BlankMapper blankMapper;

    @Autowired
    SingleMapper singleMapper;

    @Autowired
    MultipleMapper multipleMapper;

    @Autowired
    ShortAnswerMapper shortAnswerMapper;

    @Autowired
    JudgementMapper judgementMapper;

    @Autowired
    PaperMapper paperMapper;

    @Override
    public Individual makePaper(Integer subject, List<Integer> kpList, Double diff, Map<String, Integer> typeCountMapping, Map<String, Integer> typeScoreMapping,List<Question> qList) {
        Paper userWantPaper = new Paper();
        userWantPaper.setId(0);
        userWantPaper.setKnowledgePoints(kpList);
        userWantPaper.setDifficulty(diff);
        userWantPaper.setTypeCountMapping(typeCountMapping);
        userWantPaper.setTypeScoreMapping(typeScoreMapping);
        return PaperMaker.make(userWantPaper,qList);
    }

    @Override
    public Integer saveExam(Exam exam) {
        return paperMapper.saveExam(exam);
    }

    @Override
    public boolean saveRelation(Exam exam, List<Question> questions) {
        for (Question q:questions) {
            ExamQuestion examQuestion = new ExamQuestion().setExamId(exam.getId()).setQuestionId(q.getId()).setQuestionType(q.getType());
            System.out.println(exam.getId());
            paperMapper.saveRelation(examQuestion);
        }
        return false;
    }

    @Override
    public ArrayList<ProblemResVo> getProblemList(List<ExamQuestion> questions) {
        ArrayList<ProblemResVo> problemResVos = new ArrayList<>();
        ProblemResVo problemResVo = new ProblemResVo();
        ProblemVo problemVo = new ProblemVo();
        for (ExamQuestion q:questions) {
            String type = q.getQuestionType();
            problemVo.setProblemId(q.getQuestionId());
            if(type.equals("choice")){
                problemResVo = singleMapper.viewById(problemVo);
            } else if(type.equals("blank")){
                problemResVo = blankMapper.viewById(problemVo);
            }else if(type.equals("judgement")){
                problemResVo = judgementMapper.viewById(problemVo);
            }else if(type.equals("shortAnswer")){
                problemResVo = shortAnswerMapper.viewById(problemVo);
            }else if(type.equals("multiple")){
                problemResVo = multipleMapper.viewById(problemVo);
            }
            problemResVos.add(problemResVo);
        }
        return problemResVos;
    }

    @Override
    public List<ExamQuestion> getByExamId(Integer id) {
        return paperMapper.getByExamId(id);
    }
}
