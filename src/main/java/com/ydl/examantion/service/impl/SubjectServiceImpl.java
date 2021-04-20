package com.ydl.examantion.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydl.examantion.dao.SubjectMapper;
import com.ydl.examantion.model.Subject;
import com.ydl.examantion.service.SubjectService;
import com.ydl.examantion.vo.SubjectResponseVo;
import com.ydl.examantion.vo.SubjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YDL
 * @since 2021-02-01
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Autowired
    SubjectMapper subjectMapper;

    @Override
    public Page selectSubjectInfo(SubjectVo subjectVo) {
        Page page = new Page(subjectVo.getCurrentPage(),subjectVo.getPageSize());
        List<SubjectResponseVo> responseVos = subjectMapper.selectSubjectInfo(subjectVo,page);
        page.setRecords(responseVos);
        return page;
    }

    @Override
    public Subject getBySubjectId(Integer subjectId) {
        return subjectMapper.viewById(subjectId);
    }
}
