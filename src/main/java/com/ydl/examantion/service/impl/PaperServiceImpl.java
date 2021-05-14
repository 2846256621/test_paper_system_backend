package com.ydl.examantion.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydl.examantion.algorithm.Individual;
import com.ydl.examantion.algorithm.Paper;
import com.ydl.examantion.algorithm.PaperMaker;
import com.ydl.examantion.algorithm.Question;
import com.ydl.examantion.dao.*;
import com.ydl.examantion.model.Exam;
import com.ydl.examantion.model.ExamQuestion;
import com.ydl.examantion.model.Point;
import com.ydl.examantion.model.Subject;
import com.ydl.examantion.service.PaperService;
import com.ydl.examantion.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper,Exam> implements PaperService {

    @Autowired
    BlankMapper blankMapper;

    @Autowired
    SubjectMapper subjectMapper;

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

    @Autowired
    PointMapper pointMapper;

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
    public boolean saveRelation(Exam exam, List<Question> questions,Map<String,Integer> typeScoreMapping) {
        for (Question q:questions) {
            ExamQuestion examQuestion = new ExamQuestion().setExamId(exam.getId()).setQuestionId(q.getId()).setQuestionType(q.getType()).setQuestionScore(typeScoreMapping.get(q.getType()));
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
            problemResVo.setScore(q.getQuestionScore());
            problemResVos.add(problemResVo);
        }
        return problemResVos;
    }

    @Override
    public List<ExamQuestion> getByExamId(Integer id) {
        return paperMapper.getByExamId(id);
    }


    @Override
    public Page selectPaperList(PaperListVo paperListVo) {
        Page page = new Page(paperListVo.getCurrentPage(), paperListVo.getPageSize());
        ArrayList<PaperListRes> paperListRes1 = paperMapper.selectPaperList(paperListVo,page);
        Subject subject = new Subject();
        for (PaperListRes p: paperListRes1) {
            subject = subjectMapper.viewById(p.getSubjectId());
            p.setSubject(subject.getName());
            List<Integer> pointLs = Arrays.asList(p.getKnowledge().split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
            ArrayList<String> pointNames = new ArrayList<String>();
            for (Integer pointId:pointLs) {
                pointNames.add(pointMapper.selectById(pointId).getName());
            }
            p.setKnowledgePoints(pointNames);
        }
        return page.setRecords(paperListRes1);
    }
}
