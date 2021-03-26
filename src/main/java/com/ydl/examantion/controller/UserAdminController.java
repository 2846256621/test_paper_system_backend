package com.ydl.examantion.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ydl.examantion.model.Users;
import com.ydl.examantion.response.ResponseResult;
import com.ydl.examantion.service.UsersService;
import com.ydl.examantion.util.AesUtils;
import com.ydl.examantion.vo.UserAddVo;
import com.ydl.examantion.vo.UserAdminPageVo;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"管理端用户接口"})
@RestController
@RequestMapping("/userAdmin")
public class UserAdminController {


    @Autowired
    UsersService usersService;

    @GetMapping(value = "/getUserList")
    public ResponseResult getAdminUserList(UserAdminPageVo userAdminPageVo){
        Page page = usersService.selectUserLoginList(userAdminPageVo);
        return ResponseResult.page(page);
    }

    @PostMapping(value = "/adminAddUser")
    public ResponseResult addUser(@RequestBody UserAddVo userAddVo){
        String userName = userAddVo.getUserName();
        if(StringUtils.isEmpty(userName)){
            return ResponseResult.fail("用戶名不能为空");
        }

        String passWord = userAddVo.getPassWord();
        if(StringUtils.isEmpty(passWord)){
            return ResponseResult.fail("密码不能为空");
        }
        Users users = new Users();
        users.setType(0);
        users.setUserName(userAddVo.getUserName());
        users.setPassWord(AesUtils.aesCbcNoPaddingEncrypt(userAddVo.getPassWord().getBytes(), AesUtils.sKey));
        return usersService.registerUser(users);
    }

    @PostMapping(value = "/forbidUser")
    public ResponseResult forbidUser(@RequestBody Users users){
        Integer userId = users.getId();
        if(null == userId){
            return ResponseResult.fail("用户id不能为空");
        }
        UpdateWrapper<Users> queryWrapper = new  UpdateWrapper<Users>();
        queryWrapper.set("status",users.getStatus());
        queryWrapper.eq("id",userId);
        boolean deletedStatus = usersService.update(queryWrapper);
        if(deletedStatus){
            return ResponseResult.success();
        }
        return ResponseResult.fail("禁用/启用用户失败");
    }

}
