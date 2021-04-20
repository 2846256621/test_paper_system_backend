package com.ydl.examantion.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ydl.examantion.model.Subject;
import com.ydl.examantion.vo.SubjectResponseVo;
import com.ydl.examantion.vo.SubjectVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YDL
 * @since 2021-02-01
 */
public interface SubjectService extends IService<Subject> {

    Page selectSubjectInfo(SubjectVo subjectVo);

    Subject getBySubjectId(Integer subjectId);
}
