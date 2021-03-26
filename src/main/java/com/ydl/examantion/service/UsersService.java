package com.ydl.examantion.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ydl.examantion.model.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ydl.examantion.response.ResponseResult;
import com.ydl.examantion.vo.UserAdminPageVo;
import com.ydl.examantion.vo.UserRegisterVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YDL
 * @since 2021-01-09
 */
public interface UsersService extends IService<Users> {

    public Page selectUserLoginList(UserAdminPageVo userAdminPageVo);

    public ResponseResult registerUser(Users users);

    public ResponseResult selectUserLogin(UserRegisterVo registerVo);
}
