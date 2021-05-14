package com.ydl.examantion.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
    public ResponseResult addProblem(@RequestBody ProblemVo problemVo) {
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

    /**
     * 按条件查询
     * @param problemVo
     * @return
     */
    @PostMapping(value = "/selectProblem")
    public ResponseResult selectPoint(@RequestBody ProblemVo problemVo){
        ProblemReqVo problemReqVo = new ProblemReqVo();
        if(problemVo.getKnowledgePoints()==null){
            problemReqVo.setSubjectId(problemVo.getSubject()).setDifficultyLevel(problemVo.getDifficultyLevel()).setProblemType(problemVo.getProblemType()).setScore(problemVo.getScore()).setProblemText(problemVo.getProblemText());
        }else {
            problemReqVo.setSubjectId(problemVo.getSubject()).setDifficultyLevel(problemVo.getDifficultyLevel()).setPointId(Joiner.on(",").join(problemVo.getKnowledgePoints())).setProblemType(problemVo.getProblemType()).setScore(problemVo.getScore()).setProblemText(problemVo.getProblemText());
        }
        problemReqVo.setCurrentPage(problemVo.getCurrentPage()).setPageSize(problemVo.getPageSize());
        Page page = blankService.selectProblem(problemReqVo);
        return ResponseResult.page(page);
    }

    /**
     * 查看题目
     * @param problemVo
     * @return
     */
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

    /**
     * 修改题目
     */
    @PostMapping(value = "/modifyProblem")
    public ResponseResult modifyProblem(@RequestBody ProblemVo problemVo){
        ResponseResult responseResult = validateData(problemVo);
        String type = problemVo.getProblemType();
        boolean saveStatus = false;
        if (!responseResult.isSuccess()) {
            return responseResult;
        }
        if(type.equals("choice")){
            Single single = new Single();
            single.setId(problemVo.getProblemId()).setScore(problemVo.getScore()).setOptionA(problemVo.getChoiceOptionA()).setOptionB(problemVo.getChoiceOptionB()).setOptionC(problemVo.getChoiceOptionC()).setOptionD(problemVo.getChoiceOptionD()).setDifficulty(problemVo.getDifficultyLevel()).setSubjectId(problemVo.getSubject()).setAnswer(problemVo.getAnswer()).setPointId(Joiner.on(",").join(problemVo.getKnowledgePoints())).setSteam(problemVo.getProblemText());
            saveStatus = singleService.saveOrUpdate(single);
        } else if(type.equals("blank")){
            Blank blank = new Blank();
            blank.setId(problemVo.getProblemId()).setScore(problemVo.getScore()).setDifficulty(problemVo.getDifficultyLevel()).setSubjectId(problemVo.getSubject()).setAnswer(problemVo.getAnswer()).setPointId(Joiner.on(",").join(problemVo.getKnowledgePoints())).setSteam(problemVo.getProblemText());
            saveStatus = blankService.saveOrUpdate(blank);
        }else if(type.equals("judgement")){
           Judgement judgement = new Judgement();
           judgement.setId(problemVo.getProblemId()).setScore(problemVo.getScore()).setDifficulty(problemVo.getDifficultyLevel()).setSubjectId(problemVo.getSubject()).setAnswer(problemVo.getAnswer()).setPointId(Joiner.on(",").join(problemVo.getKnowledgePoints())).setSteam(problemVo.getProblemText());
           saveStatus = judgementService.saveOrUpdate(judgement);
        }else if(type.equals("shortAnswer")){
            ShortAnswer shortAnswer = new ShortAnswer();
            shortAnswer.setId(problemVo.getProblemId()).setScore(problemVo.getScore()).setDifficulty(problemVo.getDifficultyLevel()).setSubjectId(problemVo.getSubject()).setAnswer(problemVo.getAnswer()).setPointId(Joiner.on(",").join(problemVo.getKnowledgePoints())).setSteam(problemVo.getProblemText());
            saveStatus = shortAnswerService.saveOrUpdate(shortAnswer);
        }else if(type.equals("multiple")){
            Multiple multiple = new Multiple();
            multiple.setId(problemVo.getProblemId()).setScore(problemVo.getScore()).setDifficulty(problemVo.getDifficultyLevel()).setSubjectId(problemVo.getSubject()).setAnswer(problemVo.getAnswer()).setPointId(Joiner.on(",").join(problemVo.getKnowledgePoints())).setSteam(problemVo.getProblemText()).setOptionA(problemVo.getMultipleOptionA()).setOptionB(problemVo.getMultipleOptionB()).setOptionC(problemVo.getMultipleOptionC()).setOptionD(problemVo.getMultipleOptionD()).setOptionE(problemVo.getMultipleOptionE()).setOptionF(problemVo.getMultipleOptionF());
            saveStatus = multipleService.saveOrUpdate(multiple);
        }
        if(saveStatus){
            return ResponseResult.success();
        }
        return ResponseResult.fail("修改题目失败");
    }
    /**
     * 删除题目
     * @param problemVo
     * @return
     */
    @PostMapping(value = "/dropProblem")
    public ResponseResult dropProblem(@RequestBody ProblemVo problemVo){
        Integer problemId = problemVo.getProblemId();
        String type = problemVo.getProblemType();
        if(null == problemId){
            return ResponseResult.fail("=题目id不能为空");
        }
        boolean deletedStatus = false;
        if(type.equals("choice")){
            UpdateWrapper<Single> queryWrapper = new  UpdateWrapper<Single>();
            queryWrapper.set("status",1);
            queryWrapper.eq("id",problemId);
            deletedStatus = singleService.update(queryWrapper);
        } else if(type.equals("blank")){
            UpdateWrapper<Blank> queryWrapper = new  UpdateWrapper<Blank>();
            queryWrapper.set("status",1);
            queryWrapper.eq("id",problemId);
            deletedStatus = blankService.update(queryWrapper);
        }else if(type.equals("judgement")){
            UpdateWrapper<Judgement> queryWrapper = new  UpdateWrapper<Judgement>();
            queryWrapper.set("status",1);
            queryWrapper.eq("id",problemId);
            deletedStatus = judgementService.update(queryWrapper);
        }else if(type.equals("shortAnswer")){
            UpdateWrapper<ShortAnswer> queryWrapper = new  UpdateWrapper<ShortAnswer>();
            queryWrapper.set("status",1);
            queryWrapper.eq("id",problemId);
            deletedStatus = shortAnswerService.update(queryWrapper);
        }else if(type.equals("multiple")){
            UpdateWrapper<Multiple> queryWrapper = new  UpdateWrapper<Multiple>();
            queryWrapper.set("status",1);
            queryWrapper.eq("id",problemId);
            deletedStatus = multipleService.update(queryWrapper);
        }
        if(deletedStatus){
            return ResponseResult.success();
        }
        return ResponseResult.fail("删除知识点失败");
    }

    /**
     * 校验
     * @param problemVo
     * @return
     */
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
