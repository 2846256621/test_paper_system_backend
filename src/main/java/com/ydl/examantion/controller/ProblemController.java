package com.ydl.examantion.controller;


import com.google.common.base.Joiner;
import com.ydl.examantion.model.Single;
import com.ydl.examantion.vo.ProblemVo;
import org.apache.commons.lang3.StringUtils;
import com.ydl.examantion.response.ResponseResult;
import com.ydl.examantion.service.BlankService;
import com.ydl.examantion.service.MultipleService;
import com.ydl.examantion.service.ShortAnswerService;
import com.ydl.examantion.service.SingleService;
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
            single.setAnswer(problemVo.getAnswer()).setPointId(Joiner.on(",").join(problemVo.getKnowledgePoints())).setSubjectId(problemVo.getSubject()).setDifficulty(problemVo.getDifficultyLevel()).setOptionA(problemVo.getChoiceOptionA()).setOptionB(problemVo.getChoiceOptionB()).setOptionC(problemVo.getChoiceOptionC()).setOptionD(problemVo.getChoiceOptionD()).setScore(problemVo.getScore()).setUserId(problemVo.getUserId());
            singleService.save(single);
        } else if(type.equals("blank")){

        }else if(type.equals("judgement")){

        }else if(type.equals("showAnswer")){

        }else if(type.equals("multiple")){

        }
        return ResponseResult.success();
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
