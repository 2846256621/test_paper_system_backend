package com.ydl.examantion.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ydl.examantion.model.Subject;
import com.ydl.examantion.model.Users;
import com.ydl.examantion.vo.SubjectResponseVo;
import com.ydl.examantion.vo.SubjectVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SubjectMapper extends BaseMapper<Subject> {

    int addSubject(Subject subject);

    List<SubjectResponseVo> selectSubjectInfo(@Param("subjectVo") SubjectVo subjectVo, Page page);
}
