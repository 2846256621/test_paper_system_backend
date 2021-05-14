package com.ydl.examantion.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ydl.examantion.model.Point;
import com.ydl.examantion.response.ResponseResult;
import com.ydl.examantion.service.PointService;
import com.ydl.examantion.vo.PointVo;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  接口
 * </p>
 *
 * @author YDL
 * @since 2021-02-01
 */
@Api(tags = {"知识点接口"})
@RestController
@RequestMapping("/point")
public class PointController {

    @Autowired
    PointService pointService;

    @PostMapping(value = "/addPoint")
    public ResponseResult addPoint(@RequestBody PointVo pointVo){
        String pointName = pointVo.getPointName();
        if(StringUtils.isEmpty(pointName)){
            return ResponseResult.fail("知识点名称不能为空");
        }
        String chapter = pointVo.getChapter();
        if(StringUtils.isEmpty(chapter)){
            return ResponseResult.fail("知识点所属章节不能为空");
        }
        Long subjectId = pointVo.getSubjectId();
        if(null == subjectId){
            return ResponseResult.fail("学科id不能为空");
        }
        Point po = new Point();
        po.setSubjectId(pointVo.getSubjectId().intValue());
        po.setName(pointVo.getPointName());
        po.setUserId(pointVo.getUserId());
        po.setChapter(pointVo.getChapter());

        boolean saveStatus = pointService.save(po);
        if(saveStatus){
            return ResponseResult.success();
        }
        return ResponseResult.fail("添加知识点失败");
    }

    @PostMapping(value = "/updatePoint")
    public ResponseResult updatePoint(@RequestBody PointVo pointVo){
        String pointName = pointVo.getPointName();
        if(StringUtils.isEmpty(pointName)){
            return ResponseResult.fail("知识点名称不能为空");
        }
        String chapter = pointVo.getChapter();
        if(StringUtils.isEmpty(chapter)){
            return ResponseResult.fail("知识点所属章节不能为空");
        }
        Long subjectId = pointVo.getSubjectId();
        if(null == subjectId){
            return ResponseResult.fail("学科id不能为空");
        }
        Point po = new Point();
        po.setId(pointVo.getPointId());
        po.setSubjectId(pointVo.getSubjectId().intValue());
        po.setName(pointVo.getPointName());
        po.setUserId(pointVo.getUserId());
        po.setChapter(pointVo.getChapter());
        boolean saveStatus = pointService.saveOrUpdate(po);
        if(saveStatus){
            return ResponseResult.success();
        }
        return ResponseResult.fail("修改知识点失败");
    }

    @PostMapping(value = "/dropPoint")
    public ResponseResult deletedPoint(@RequestBody PointVo pointVo){
        Integer pointId = pointVo.getPointId();
        if(null == pointId){
            return ResponseResult.fail("知识点id不能为空");
        }
        UpdateWrapper<Point> queryWrapper = new  UpdateWrapper<Point>();
        queryWrapper.set("status",1);
        queryWrapper.eq("id",pointId);
        boolean deletedStatus = pointService.update(queryWrapper);
        if(deletedStatus){
            return ResponseResult.success();
        }
        return ResponseResult.fail("删除知识点失败");
    }


    @PostMapping(value = "/selectPoint")
    public ResponseResult selectPoint(@RequestBody PointVo pointVo){
        Page page = pointService.selectPoint(pointVo);
        return ResponseResult.page(page);
    }
}

