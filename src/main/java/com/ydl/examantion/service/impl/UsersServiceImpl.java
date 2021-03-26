package com.ydl.examantion.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ydl.examantion.model.Users;
import com.ydl.examantion.dao.UsersMapper;
import com.ydl.examantion.response.CommonCode;
import com.ydl.examantion.response.ResponseResult;
import com.ydl.examantion.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydl.examantion.vo.UserAdminPageVo;
import com.ydl.examantion.vo.UserRegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YDL
 * @since 2021-01-09
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {


    @Autowired
    UsersMapper usersMapper;

    @Override
    public Page selectUserLoginList(UserAdminPageVo userAdminPageVo) {
        Page page = new Page(userAdminPageVo.getCurrentPage(), userAdminPageVo.getPageSize());
        List<Users> usersList = usersMapper.selectUserLoginList(page);
        page.setRecords(usersList);
        return page;
    }

    @Override
    public ResponseResult registerUser(Users users) {
        List<Users> usersList = usersMapper.selectUserIfExists(users.getUserName());
        if(null != usersList && usersList.size() > 0){
            return ResponseResult.fail("用户名"+users.getUserName()+"，已经存在");
        }
        Integer registerUser = usersMapper.registerUser(users);
        if(null != registerUser && registerUser > 0){
            return ResponseResult.code(CommonCode.REGISTER_SUCCESS);
        }
        return ResponseResult.fail("注册失败");
    }

    @Override
    public ResponseResult selectUserLogin(UserRegisterVo registerVo) {
        Users userLogin = usersMapper.selectUserLogin(registerVo);
        if(userLogin == null){
            return ResponseResult.fail("用户名或者密码错误！");
        }
        Integer status = userLogin.getStatus();
        if(status == 1){
            return ResponseResult.fail("当前账号被禁用");
        }
        return ResponseResult.data(userLogin);
    }
}
