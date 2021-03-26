package com.ydl.examantion.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ydl.examantion.model.Subject;
import com.ydl.examantion.response.ResponseResult;
import com.ydl.examantion.service.SubjectService;
import com.ydl.examantion.vo.SubjectVo;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  接口
 * </p>
 *
 * @author YDL
 * @since 2021-02-01
 */
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    /***
     * 添加学科
     */
    @ResponseBody
    @PostMapping(value = "/addSubject")
    public ResponseResult addSubject(@RequestBody Subject subject){
        String subjectVoName = subject.getName();
        System.out.println(subjectVoName);
        if(StringUtils.isEmpty(subjectVoName)){
            return ResponseResult.fail("学科姓名不能为空");
        }
        QueryWrapper<Subject> querySupplierBrand=new QueryWrapper<Subject>();
        querySupplierBrand.lambda().eq(Subject::getName,subject.getName()).eq(Subject::getStatus,0);
        Subject serviceOne = subjectService.getOne(querySupplierBrand);
        if(serviceOne != null){
            return ResponseResult.fail("该学科已经被添加过");
        }
        subjectService.save(subject);
        return ResponseResult.success();
    }

    /**
     * 修改学科
     * @return
     */
    @PostMapping(value = "/updateSubject")
   public ResponseResult updateSubject(@RequestBody Subject subject){
        Integer subjectId = subject.getId();
        String name = subject.getName();
        if(null == subjectId){
            return ResponseResult.fail("学科id不能为空");
        }
        if(StringUtils.isEmpty(name)){
            return ResponseResult.fail("学科姓名不能为空");
        }
        boolean isStatus = subjectService.saveOrUpdate(subject);
        if(isStatus){
            return ResponseResult.success();
        }
        return ResponseResult.fail("修改失败");
    }

    /***
     * 查询学科
     * @param subjectVo
     * @return
     */
    @GetMapping(value = "/selectSubjectList")
    public ResponseResult selectSubject(SubjectVo subjectVo){
        Page subjectInfo = subjectService.selectSubjectInfo(subjectVo);
        return ResponseResult.page(subjectInfo);
    }

    /***
     * 删除学科
     * @param subject
     * @return
     */
    @GetMapping(value = "/dropSubject")
    public ResponseResult deleteSubject(Subject subject){
        Integer subjectId = subject.getId();
        if(null == subjectId){
            return ResponseResult.fail("学科id不能为空");
        }

        UpdateWrapper<Subject> querySupplierBrand = new UpdateWrapper<>();
        querySupplierBrand.set("status",1).eq("id",subject.getId());
        boolean isStatus = subjectService.update(querySupplierBrand);
        if(isStatus){
            return ResponseResult.success();
        }
        return ResponseResult.fail();
    }
}
