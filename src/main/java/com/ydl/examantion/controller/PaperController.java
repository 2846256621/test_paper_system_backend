package com.ydl.examantion.controller;


import com.google.common.base.Joiner;
import com.ydl.examantion.algorithm.Individual;
import com.ydl.examantion.algorithm.Paper;
import com.ydl.examantion.algorithm.Question;
import com.ydl.examantion.model.Exam;
import com.ydl.examantion.model.ExamQuestion;
import com.ydl.examantion.model.Subject;
import com.ydl.examantion.response.ResponseResult;
import com.ydl.examantion.service.BlankService;
import com.ydl.examantion.service.PaperService;
import com.ydl.examantion.service.SubjectService;
import com.ydl.examantion.vo.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = {"卷子接口"})
@RestController
@RequestMapping("/paper")
public class PaperController {

    @Autowired
    PaperService paperService;

    @Autowired
    BlankService blankService;

    @Autowired
    SubjectService subjectService;

    @PostMapping(value = "/makePaper")
    public ResponseResult makePaper(@RequestBody PaperVo paperVo){
        Integer problemNum = 0;
        Map<String, Integer> typeCountMapping = new HashMap<String, Integer>();
        Map<String, Integer> typeScoreMapping = new HashMap<String, Integer>();
        for(int i=0; i<paperVo.getProblemTypeInfo().size(); i++){
            typeCountMapping.put(paperVo.getProblemTypeInfo().get(i).getProblemType(), paperVo.getProblemTypeInfo().get(i).getProblemTypeNum());
            problemNum+=paperVo.getProblemTypeInfo().get(i).getProblemTypeNum();
            typeScoreMapping.put(paperVo.getProblemTypeInfo().get(i).getProblemType(), paperVo.getProblemTypeInfo().get(i).getProblemTypeScore());
        }
        List<Question> qList = blankService.selectProblemByPoint(paperVo.getPoints());
        Individual paper = paperService.makePaper(paperVo.getSubjectId(), paperVo.getPoints(), paperVo.getDifficulty(), typeCountMapping, typeScoreMapping,qList);
        List<Question> questions = paper.getQuestions();
        Exam exam = new Exam().setExamName(paperVo.getExamName()).setSubjectId(paperVo.getSubjectId()).setUserId(paperVo.getUserId()).setDifficulty(paper.getDifficulty()).setFitness(paper.getFitness()).setPointCoverage(paper.getKpCoverage()).setPointId(Joiner.on(",").join(paperVo.getPoints())).setProblemNum(problemNum).setScore(paper.getTotalScore()).setStartTime(paperVo.getStartTime()).setEndTime(paperVo.getEndTime());
        paperService.saveExam(exam);
        paperService.saveRelation(exam,questions);

        return ResponseResult.data(exam.getId());
    }

    @GetMapping(value = "/paperDetails")
    public ResponseResult paperDetails(@RequestParam("examId") Integer examId){
        Exam exam = paperService.getById(examId);
        PaperAttribute paperAttribute = new PaperAttribute().setDifficulty(exam.getDifficulty()).setFitness(exam.getFitness()).setPointCoverage(exam.getPointCoverage()).setProblemNum(exam.getProblemNum()).setScore(exam.getScore());
        Subject subject = subjectService.getBySubjectId(exam.getSubjectId());
        PaperDetails paperDetails = new PaperDetails().setTestPaperName(exam.getExamName()).setSubject(subject.getName()).setStartTime(exam.getStartTime()).setEndTime(exam.getEndTime());
        List<ExamQuestion> questions = paperService.getByExamId(exam.getId());
        ArrayList<ProblemResVo> problemList = paperService.getProblemList(questions);
        PaperResVo paperResVo = new PaperResVo().setPaperAttribute(paperAttribute).setPaperDetails(paperDetails).setProblemList(problemList);

        return ResponseResult.data(paperResVo);
    }
}
