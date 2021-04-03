package com.ydl.examantion.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Joiner;
import com.ydl.examantion.model.*;
import com.ydl.examantion.service.*;
import com.ydl.examantion.vo.ProblemReqVo;
import com.ydl.examantion.vo.ProblemResVo;
import com.ydl.examantion.vo.ProblemVo;
import org.apache.commons.lang3.StringUtils;
import com.ydl.examantion.response.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;
import java.util.ArrayList;

/**
 * <p>
 *  接口
 * </p>
 *
 * @author YDL
 * @since 2021-02-01
 */
@Api(tags = {"题目管理接口"})
@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    BlankService blankService;

    @Autowired
    MultipleService multipleService;

    @Autowired
    SingleService singleService;

    @Autowired
    ShortAnswerService shortAnswerService;

    @Autowired
    JudgementService judgementService;

    /**
     * 增加题目
     * @param problemVo
     * @return
     */
    @PostMapping(value = "/addProblem")
    public ResponseResult addPoint(@RequestBody ProblemVo problemVo) {
        ResponseResult responseResult = validateData(problemVo);
        if (!responseResult.isSuccess()) {
            return responseResult;
        }
        String type = problemVo.getProblemType();
        if(type.equals("choice")){
            if(StringUtils.isEmpty(problemVo.getChoiceOptionA())||StringUtils.isEmpty(problemVo.getChoiceOptionB())||StringUtils.isEmpty(problemVo.getChoiceOptionC())||StringUtils.isEmpty(problemVo.getChoiceOptionD())){
                return ResponseResult.fail("四个选项皆不能为空");
            }
            Single single = new Single();
            single.setAnswer(problemVo.getAnswer()).setPointId(Joiner.on(",").join(problemVo.getKnowledgePoints())).setSubjectId(problemVo.getSubject()).setDifficulty(problemVo.getDifficultyLevel()).setOptionA(problemVo.getChoiceOptionA()).setOptionB(problemVo.getChoiceOptionB()).setSteam(problemVo.getProblemText()).setOptionC(problemVo.getChoiceOptionC()).setOptionD(problemVo.getChoiceOptionD()).setScore(problemVo.getScore()).setUserId(problemVo.getUserId());
            singleService.save(single);
        } else if(type.equals("blank")){
            Blank blank = new Blank();
            blank.setAnswer(problemVo.getAnswer()).setPointId(Joiner.on(",").join(problemVo.getKnowledgePoints())).setSubjectId(problemVo.getSubject()).setSteam(problemVo.getProblemText()).setDifficulty(problemVo.getDifficultyLevel()).setScore(problemVo.getScore()).setUserId(problemVo.getUserId());
            blankService.save(blank);

        }else if(type.equals("judgement")){
            Judgement judgement = new Judgement();
            judgement.setAnswer(problemVo.getAnswer()).setPointId(Joiner.on(",").join(problemVo.getKnowledgePoints())).setSubjectId(problemVo.getSubject()).setDifficulty(problemVo.getDifficultyLevel()).setSteam(problemVo.getProblemText()).setScore(problemVo.getScore()).setUserId(problemVo.getUserId());
            judgementService.save(judgement);
        }else if(type.equals("shortAnswer")){
            ShortAnswer shortAnswer = new ShortAnswer();
            shortAnswer.setAnswer(problemVo.getAnswer()).setPointId(Joiner.on(",").join(problemVo.getKnowledgePoints())).setSubjectId(problemVo.getSubject()).setSteam(problemVo.getProblemText()).setDifficulty(problemVo.getDifficultyLevel()).setScore(problemVo.getScore()).setUserId(problemVo.getUserId());
           shortAnswerService.save(shortAnswer);
        }else if(type.equals("multiple")){
            if(StringUtils.isEmpty(problemVo.getMultipleOptionA())||StringUtils.isEmpty(problemVo.getMultipleOptionB())||StringUtils.isEmpty(problemVo.getMultipleOptionC())||StringUtils.isEmpty(problemVo.getMultipleOptionD())){
                return ResponseResult.fail("A,B,C,D四个选项皆不能为空");
            }
            Multiple multiple = new Multiple();
            multiple.setAnswer(problemVo.getAnswer()).setPointId(Joiner.on(",").join(problemVo.getKnowledgePoints())).setSubjectId(problemVo.getSubject()).setSteam(problemVo.getProblemText()).setDifficulty(problemVo.getDifficultyLevel()).setOptionA(problemVo.getMultipleOptionA()).setOptionB(problemVo.getMultipleOptionB()).setOptionC(problemVo.getMultipleOptionC()).setOptionD(problemVo.getMultipleOptionD()).setOptionE(problemVo.getMultipleOptionE()).setOptionF(problemVo.getMultipleOptionF()).setScore(problemVo.getScore()).setUserId(problemVo.getUserId());
            multipleService.save(multiple);
        }
        return ResponseResult.success();
    }

    @PostMapping(value = "/selectProblem")
    public ResponseResult selectPoint(@RequestBody ProblemVo problemVo){
        ProblemReqVo problemReqVo = new ProblemReqVo();
        if(problemVo.getKnowledgePoints()==null){
            problemReqVo.setSubjectId(problemVo.getSubject()).setDifficultyLevel(problemVo.getDifficultyLevel()).setProblemType(problemVo.getProblemType()).setScore(problemVo.getScore());
        }else {
            problemReqVo.setSubjectId(problemVo.getSubject()).setDifficultyLevel(problemVo.getDifficultyLevel()).setPointId(Joiner.on(",").join(problemVo.getKnowledgePoints())).setProblemType(problemVo.getProblemType()).setScore(problemVo.getScore());
        }
        Page page = blankService.selectProblem(problemReqVo);
        return ResponseResult.page(page);
    }

    @PostMapping(value = "/viewProblem")
    public ResponseResult viewProblem(@RequestBody ProblemVo problemVo){
        String type = problemVo.getProblemType();
        ProblemResVo problemResVo = new ProblemResVo();
        if(type.equals("choice")){
            problemResVo = singleService.viewById(problemVo);
        } else if(type.equals("blank")){
            problemResVo = blankService.viewById(problemVo);
        }else if(type.equals("judgement")){
            problemResVo = judgementService.viewById(problemVo);
        }else if(type.equals("shortAnswer")){
            problemResVo = shortAnswerService.viewById(problemVo);
        }else if(type.equals("multiple")){
            problemResVo = multipleService.viewById(problemVo);
        }
        return ResponseResult.data(problemResVo);
    }


    public ResponseResult validateData(ProblemVo problemVo) {
        String answer = problemVo.getAnswer();
        if (StringUtils.isEmpty(answer)) {
            return ResponseResult.fail("答案不能为空");
        }
        Double difficultyLevel = problemVo.getDifficultyLevel();
        if (difficultyLevel == null) {
            return ResponseResult.fail("困难度不能为空");
        }
        ArrayList<Integer> knowledgePoints = problemVo.getKnowledgePoints();
        if (knowledgePoints == null) {
            return ResponseResult.fail("包含的知识点不能为空");
        }
        String problemText = problemVo.getProblemText();
        if (StringUtils.isEmpty(problemText)) {
            return ResponseResult.fail("题干不能为空");
        }
        String problemType = problemVo.getProblemType();
        if (StringUtils.isEmpty(problemType)) {
            return ResponseResult.fail("题目类型不能为空");
        }
        Integer score = problemVo.getScore();
        if (score == null) {
            return ResponseResult.fail("分数不能为空");
        }
        Integer subject = problemVo.getSubject();
        if (subject == null) {
            return ResponseResult.fail("科目id不能为空");
        }
        return ResponseResult.success();
    }
}
