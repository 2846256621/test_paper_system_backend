package com.ydl.examantion.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Joiner;
import com.ydl.examantion.algorithm.Individual;
import com.ydl.examantion.algorithm.Paper;
import com.ydl.examantion.algorithm.Question;
import com.ydl.examantion.model.Exam;
import com.ydl.examantion.model.ExamQuestion;
import com.ydl.examantion.model.Point;
import com.ydl.examantion.model.Subject;
import com.ydl.examantion.response.ResponseResult;
import com.ydl.examantion.service.BlankService;
import com.ydl.examantion.service.PaperService;
import com.ydl.examantion.service.SubjectService;
import com.ydl.examantion.service.UsersService;
import com.ydl.examantion.service.impl.UsersServiceImpl;
import com.ydl.examantion.vo.*;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    UsersService usersService;


    /**
     * 组卷
     * @param paperVo
     * @return
     */
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
        paperService.saveRelation(exam,questions,typeScoreMapping);

        return ResponseResult.data(exam.getId());
    }

    /**
     * 试卷详情
     * @param examId
     * @return
     */
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

    /**
     * 试卷列表
     * @param paperListVo
     * @return
     */
    @PostMapping(value = "/paperList")
    public ResponseResult paperList(@RequestBody PaperListVo paperListVo){
        Page page = paperService.selectPaperList(paperListVo);
        return ResponseResult.page(page);
    }

    /**
     * 删除试卷
     * @param paperListVo
     * @return
     */
    @PostMapping(value = "/dropPaper")
    public ResponseResult dropPaper(@RequestBody PaperListVo paperListVo){
        boolean flag = usersService.isJurisdiction(paperListVo.getUserId());
        UpdateWrapper<Exam> queryWrapper = new  UpdateWrapper<Exam>();
        queryWrapper.set("status",1);
        queryWrapper.eq("id",paperListVo.getExamId());
        if(!flag) {
            queryWrapper.eq("user_id", paperListVo.getUserId());
        }
        boolean deletedStatus = paperService.update(queryWrapper);
        if(deletedStatus){
        return ResponseResult.success();
    }
    return ResponseResult.fail("不具有删除权限");
}

    /**
     * 修改试卷
     * @param paperVo
     * @return
     */
    @PostMapping(value = "/modifyPaper")
    public ResponseResult dropPaper(@RequestBody PaperVo paperVo){
        Exam exam = new Exam();
        if(!StringUtils.isEmpty(paperVo.getExamName())) {
            exam.setExamName(paperVo.getExamName());
        }
        if(!StringUtils.isEmpty(paperVo.getStartTime())) {
            exam.setStartTime(paperVo.getStartTime());
        }
        if(!StringUtils.isEmpty(paperVo.getEndTime())) {
            exam.setEndTime(paperVo.getEndTime());
        }
        exam.setId(paperVo.getExamId());
        boolean modifyStatus = paperService.saveOrUpdate(exam);
        if(modifyStatus){
            return ResponseResult.success();
        }
        return ResponseResult.fail("修改试卷失败");
    }
}
